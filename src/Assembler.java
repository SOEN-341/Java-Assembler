import java.io.File;
import java.io.IOException;

public class Assembler {
    public static void main(String[] args) {
        try{
            ErrorReporter err = new ErrorReporter();
            InterRep IR = new Parser().generates();
            System.out.println(IR);
            //new CodeGenerator(IR, new SymbolTable(), new File("TestInherentMnemonics.asm")).generateListing();

        }catch(Exception e){

        }

    }
}
