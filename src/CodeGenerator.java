import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.util.Locale;

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
        String header = "Line Addr Code          Label         Mne   Operand       Comments";
        FileOutputStream foS = null;
        try{
            foS = new FileOutputStream(new File(this.f.getName().replace(".asm", ".lst").replace("copied", "")));
            String currentLine = "";

            for(int i = 0; i < header.length();i++){
                foS.write(header.charAt(i));
            }
            foS.write('\n');
            for(int i = 0, addr=0; i < this.IR.getSize(); i++,addr++){
                currentLine = lineStatetolst(i,addr, this.IR.getLS(i), Table);
                String mnemonic = this.IR.getLS(i).getInstruction().getMnemonic();
                if(mnemonic == ""){
                    addr--;
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

    }

    public String lineStatetolst(int lineNum, int addr, LineStatement lS, SymbolTable Table){

        String hex = Integer.toHexString(addr).toUpperCase();

        String mnemonic = lS.getInstruction().getMnemonic();
        int code = 0;
        int number = 0;
        if(mnemonic != ""){
            code = Table.getOpcode(mnemonic);
            number = Integer.parseInt(lS.getInstruction().getOperand());
        }
        if(number < 0){
            number += 8;
        }
        if(code == 0x80)
            code = (number > 15) ? 0x70 : 0x80;
        code = code | number;

        String opCode = "";
        if(mnemonic != "")
            opCode = Integer.toHexString(code).toUpperCase();
        else
            opCode = "";
        String comment = "";
        if(lS.getComments() != "")
            comment = ";" +lS.getComments();
        String label = "";
        //if(lS.getLabel() != "" || mnemonic != "")
        label = String.format("%1$26s", "");
        //if(mnemonic != "")
        mnemonic = String.format("%1$-9s" ,lS.getInstruction().getMnemonic())  + String.format("%1$2s", "") + String.format("%1$-2s", lS.getInstruction().getOperand()) ;

        return String.format("%1$-4s", lineNum + 1) + " " + String.format("%1$4s", hex).replace(" ", "0") +
                " " +String.format("%1$2s", opCode).replace("", "") + label + mnemonic + String.format("%1$12s", "") + comment;
    }


}
