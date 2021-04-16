import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

// receive
public class CodeGenerator implements ICodeGenerator{
    private InterRep IR;
    private SymbolTable Table;
    private File f;
    public CodeGenerator(InterRep IR, SymbolTable Table, File f){
        this.IR = IR;
        this.Table = Table;
        this.f = f;
    }

    public void generateListing(){
        String header = "Line Addr Machine Code  Label         Assembly Code        Comments";
        FileOutputStream foS = null;
        try{
            foS = new FileOutputStream(new File(this.f.getName().replace(".asm", ".lst").replace("copied", "")));
            String currentLine = "";

            for(int i = 0; i < header.length();i++){
                foS.write(header.charAt(i));
            }
            foS.write('\n');

            this.firstPass();
            //System.out.println(this.Table);
            for(int i = 0, addr=0; i < this.IR.getSize(); i++,addr+=2){
                currentLine = RelativeString(i,addr, this.IR.getLS(i));

                String mnemonic = this.IR.getLS(i).getInstruction().getMnemonic();
                if(mnemonic == ""){
                    addr-=2;
                }
                for(int j = 0;j < currentLine.length(); j++){
                    foS.write(currentLine.charAt(j));
                }
                foS.write('\n');
            }
            foS.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }finally {
            try{
                foS.close();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }


    }
    public void generateExecutable(){
        FileOutputStream foS = null;
        try{
            foS = new FileOutputStream(new File(this.f.getName().replace(".asm", ".txt").replace("copied", "")));
            this.firstPass();
            System.out.println(this.Table);
            for(int i = 0, addr=0; i < this.IR.getSize(); i++,addr+=2){
                String mnemonic = this.IR.getLS(i).getInstruction().getMnemonic();
                if(mnemonic == ""){
                    addr-=2;
                }

                String code = generateRelativeCode(addr ,this.IR.getLS(i)).toUpperCase() + " ";
                code = code.replace("\n", "");

                for(int j = 0;j < code.length(); j++){
                    foS.write(code.charAt(j));
                }
            }

            foS.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }


    }

    public String ImmediateString(int lineNum, int addr, LineStatement lS, SymbolTable Table){

        String hex = Integer.toHexString(addr).toUpperCase();

        String mnemonic = lS.getInstruction().getMnemonic();

        int code = generateImediateCode(lS);

        String opCode = "";
        if(mnemonic != "")
            opCode = Integer.toHexString(code).toUpperCase();
        else
            opCode = "";
        String comment = "";
        if(lS.getComments() != "" && lS.getComments() != null)
            comment = ";" +lS.getComments();
        String label = "";
        //if(lS.getLabel() != "" || mnemonic != "")
        label = String.format("%1$26s", "");
        //if(mnemonic != "")
        mnemonic = String.format("%1$-9s" ,lS.getInstruction().getMnemonic())  + String.format("%1$2s", "") + String.format("%1$-2s", lS.getInstruction().getOperand());

        return String.format("%1$-4s", lineNum + 1) + " " + String.format("%1$4s", hex).replace(" ", "0") +
                " " +String.format("%1$2s", opCode).replace("", "") + label + mnemonic + String.format("%1$12s", "") + comment;
    }

    public void firstPass(){
        for(int i = 0, addr=0; i < this.IR.getSize(); i++,addr+=2){
            String mnemonic = this.IR.getLS(i).getInstruction().getMnemonic();
            if(mnemonic == ""){
                addr-=2;
            }
            System.out.println(mnemonic + "X" + this.IR.getLS(i).getLabel());
            if(this.IR.getLS(i).getLabel() != "" || this.IR.getLS(i).getLabel() != null){
                this.Table.addlabel(this.IR.getLS(i).getLabel(), addr);
            }
        }
    }
    public String RelativeString(int lineNum, int addr, LineStatement lS){
        String MachineCode = "";
        if(lS.getInstruction().getMnemonic() != "")
            MachineCode = generateRelativeCode(addr,lS);

        String operand = lS.getInstruction().getOperand();
        if(lS.getInstruction().getMnemonic().equals(".cstring")){
            operand = operand;
        }
        String label = "";
        if(lS.getLabel() != null){
            label = lS.getLabel();
        }
        String comment = "";
        if(lS.getComments() != null && lS.getComments() != ""){
            comment = ";"+lS.getComments();
        }
        return String.format("%1$4s", lineNum).replace(' ', '0') +" " +String.format("%1$4s", addr).replace(' ', '0')
                + " " + String.format("%1$-12s", MachineCode) + "  " + String.format("%1$-14s", label) +
                String.format("%1$-9s", lS.getInstruction().getMnemonic()) + String.format("%1$5s", operand) +
                String.format("%1$6s", "") + comment;
    }

    public int generateImediateCode(LineStatement lS){
        String mnemonic = lS.getInstruction().getMnemonic();
        int code = 0;
        int number = 0;
        if(mnemonic != ""){
            code = Table.getOpcode(mnemonic);
            number = Integer.parseInt(lS.getInstruction().getOperand());
        }
        //
        if(number < 0){
            number += 8;
        }
        if(code == 0x80)
            code = (number > 15) ? 0x70 : 0x80;
        code = code | number;

        return code;
    }
    public String generateRelativeCode(int addr, LineStatement lS){
        int opcode = -1;

        if(lS.getInstruction().getMnemonic() != null && lS.getInstruction().getMnemonic() != ".cstring")
            opcode = Table.getOpcode(lS.getInstruction().getMnemonic());
        String MachineCode = "";
        if(opcode != -1)
            MachineCode = Integer.toHexString(opcode).toUpperCase();
        int offset = -1;
        if(lS.getInstruction().getMnemonic().equals("br.i8")){//branching
            offset = new Helper().offset255(addr, Table.getOpcode(lS.getInstruction().getOperand()));
            MachineCode += (" " + String.format("%1$2s" ,Integer.toHexString(offset)).replace(' ', '0')).toUpperCase();

        }else if(lS.getInstruction().getMnemonic().equals("ldv.u8") || lS.getInstruction().getMnemonic().equals("stv.u8") || lS.getInstruction().getMnemonic().equals("ldc.i8")){
            MachineCode += ( " " + String.format("%1$2s" ,Integer.toHexString(Integer.parseInt(lS.getInstruction().getOperand()))).replace(' ', '0')).toUpperCase();

        }else if(lS.getInstruction().getMnemonic().equals(".cstring")){
            String s = lS.getInstruction().getOperand().replace("\"", "");
            for(int i = 0; i < s.length(); i++){
                MachineCode += (String.format("%1$2s" ,Integer.toHexString(s.charAt(i))).replace(' ', '0') +" ").toUpperCase();
            }
            MachineCode += "00";
        }

        return MachineCode;
    }
}

// code = resolvedCode(number, code)[0];
// number = resolvedCode(number, code)[1];
