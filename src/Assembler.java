import java.io.File;
import java.io.IOException;

public class Assembler {
    public static void main(String[] args) {
        try{
            ErrorReporter reporter=new ErrorReporter();
            InterRep IR = new Parser(new Scanner("TestImmediate.asm",reporter)).generates();
            new CodeGenerator(IR, new SymbolTable(), new File("TestImmediate.asm")).generateListing();
            reporter.report();
        }catch(Exception e){

        }

    }
}
