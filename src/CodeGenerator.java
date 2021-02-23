import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;

// recieve
public class CodeGenerator implements ICodeGenerator{

    public void generateListing(InterRep IR, SymbolTable Table){
        String header = "Line Addr Code          Label         Mne   Operand       Comments";

        try{
            FileOutputStream foS = new FileOutputStream(new File("test.lst"));

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
    public void generateExecutable(){

    }

    public static String lineStatetolst(int lineNum, LineStatement lS){
        String hex = "";
        return String.format("%1$-5s", lineNum) + String.format("%1$4s", lineNum).replace(" ", "0");
    }

}
