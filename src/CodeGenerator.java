import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

// receive
public class CodeGenerator implements ICodeGenerator{
    private InterRep IR;
    private SymbolTable Table;
    private File f;
    private boolean isVerbose;
    public CodeGenerator(InterRep IR, SymbolTable Table, File f, boolean isVerbose){
        this.IR = IR;
        this.Table = Table;
        this.f = f;
        this.isVerbose = isVerbose;
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
                currentLine = RelativeString(i,addr, this.IR.getLS(i), false);

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
            foS = new FileOutputStream(new File(this.f.getName().replace(".asm", ".exe").replace("copied", "")));
            this.firstPass();
            //System.out.println(this.Table);
            if(isVerbose)
                System.out.println("***  Listing after second pass ***");
            for(int i = 0, addr=0; i < this.IR.getSize(); i++,addr+=2){

                if(isVerbose){
                    String currentLine = RelativeString(i,addr, this.IR.getLS(i), false);
                    System.out.println(currentLine);
                }

                String mnemonic = this.IR.getLS(i).getInstruction().getMnemonic();
                if(mnemonic == ""){
                    addr-=2;
                }



                String code = generateRelativeCode(addr ,this.IR.getLS(i), true).toUpperCase() + " ";
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
        ArrayList<String> labelsFound = new ArrayList<>();
        ArrayList<String> firstPassOutPut = new ArrayList<>();

        for(int i = 0, addr=0; i < this.IR.getSize(); i++,addr+=2){


            //System.out.println(mnemonic + "X" + this.IR.getLS(i).getLabel());
            if((this.IR.getLS(i).getLabel() != "" || this.IR.getLS(i).getLabel() != null) && !this.Table.contains(this.IR.getLS(i).getLabel())){
                //System.out.println(this.IR.getLS(i).getLabel() + " " + Integer.toHexString(addr-2));
                this.Table.addlabel(this.IR.getLS(i).getLabel(), addr);
                if (isVerbose){
                    if(this.IR.getLS(i).getLabel() != "")
                        labelsFound.add("label: "+ this.IR.getLS(i).getLabel() + " found at address " + Integer.toHexString(addr));
                }
            }


            if(addr < 0)
                addr = 0;
            firstPassOutPut.add(RelativeString(i,addr, this.IR.getLS(i), true));
            String mnemonic = this.IR.getLS(i).getInstruction().getMnemonic();
            if(mnemonic == ""){
                addr-=2;
            }
        }

        if(this.isVerbose)
        {
            System.out.println("***  Labels found in first pass  ***");
            for (String word : labelsFound) {
                System.out.println(word);
            }
            System.out.println("***  Listing after first pass  ***");
            for (String word : firstPassOutPut) {
                System.out.println(word);
            }
        }


    }
    public String RelativeString(int lineNum, int addr, LineStatement lS, boolean forVerbose){
        String MachineCode = "";
        if(lS.getInstruction().getMnemonic() != "")
            MachineCode = generateRelativeCode(addr,lS, false);

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
        if(forVerbose){
            if(MachineCode.contains(" ??") && lS.getInstruction().getMnemonic().equals("br.i8"))
                comment += " <--- offset unresolved in first path due to forward branching";
            else if(!MachineCode.contains(" ??") && !lS.getLabel().equals(operand) && lS.getInstruction().getMnemonic().equals("br.i8"))
                comment += " <--- offset resolved in first path due to backward branching";
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
    public String generateRelativeCode(int addr, LineStatement lS, boolean test){
        int opcode = -1;

        if(lS.getInstruction().getMnemonic() != null && lS.getInstruction().getMnemonic() != ".cstring")
            opcode = Table.getOpcode(lS.getInstruction().getMnemonic());
        String MachineCode = "";
        if(opcode != -1)
            MachineCode = Integer.toHexString(opcode).toUpperCase();
        int offset = -1;
        if(lS.getInstruction().getMnemonic().equals("br.i8")){//branching

            if(false){
                System.out.print(lS.getInstruction().getOperand() + " " + Integer.toHexString(addr) + " " + Integer.toHexString(Table.getOpcode(lS.getInstruction().getOperand())));
                System.out.println(" offset: " + new Helper().offset255(addr, Table.getOpcode(lS.getInstruction().getOperand())));
            }
            offset = new Helper().offset255(addr, Table.getOpcode(lS.getInstruction().getOperand()));

            if(Table.getOpcode(lS.getInstruction().getOperand()) == -1)
                MachineCode += " ??";
            else
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
