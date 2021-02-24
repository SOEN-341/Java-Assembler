import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.util.Locale;

// receive
public class CodeGenerator implements ICodeGenerator{

    public static void generateListing(InterRep IR, SymbolTable Table, File f){
        String header = "Line Addr Code          Label         Mne   Operand       Comments";
        FileOutputStream foS = null;
        try{
            foS = new FileOutputStream(new File(f.getName().replace(".asm", ".lst").replace("copied", "")));
            String currentLine = "";

            for(int i = 0; i < header.length();i++){
                foS.write(header.charAt(i));
            }
            foS.write('\n');
            for(int i = 0; i < IR.getSize(); i++){
                currentLine = lineStatetolst(i, IR.getLS(i), Table);
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

    public static String lineStatetolst(int lineNum, LineStatement lS, SymbolTable Table){
        String hex = Integer.toHexString(lineNum).toUpperCase();
        String opCode = Integer.toHexString(Table.getOpcode(lS.getInstruction().getMnemonic())).toUpperCase();
        return String.format("%1$-4s", lineNum + 1) + " " + String.format("%1$4s", hex).replace(" ", "0") +
                " " +String.format("%1$2s", opCode).replace(" ", "0") + String.format("%1$26s", "") + lS.getInstruction().getMnemonic();
    }


}
