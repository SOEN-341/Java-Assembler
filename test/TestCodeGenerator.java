import java.io.FileInputStream;
import java.io.InputStream;
import java.io.File;
import java.io.IOException;
public class TestCodeGenerator {
    public static void main(String[] args) throws IOException{
        System.out.println("Test lineStatetolst");
        System.out.println("6    0005 00                          halt");
        System.out.println(CodeGenerator.lineStatetolst(5, new LineStatement(null, "halt",null, null),new SymbolTable()));
        System.out.println("Test generateLst");
        InterRep IR = new Parser().generates();

        CodeGenerator.generateListing(IR, new SymbolTable(), new File("copiedTestInherentMnemonics.asm"));

        InputStream is = new FileInputStream(new File ("TestInherentMnemonics.lst"));

        int i = 0;
        int count = 0;
        String actual = "";
        while(i != -1){ // end of file
            i = is.read();
            actual += (char)i;
            if(i == '\n'){ // counting lines
                count++;
            }
            if(count == 2){ // break when two lines are read
                break;
            }
        }
        is.close();
        System.out.println("Line Addr Code          Label         Mne   Operand       Comments1    0000 00                          halt");
        System.out.println(actual.replace("\n", ""));
    }


}
