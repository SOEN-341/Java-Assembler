import java.io.File;
import java.io.IOException;

public class Assembler {
    public static void main(String[] args) {
        try{
            SymbolTable symbolTable = new SymbolTable();
            ErrorReporter reporter= new ErrorReporter();
            InterRep IR = new Parser(new Scanner("TestImmediate.asm",reporter,symbolTable)).generates();
            new CodeGenerator(IR,symbolTable, new File("TestImmediate.asm")).generateListing();
            reporter.report();
        }catch(Exception e){

        }

    }
}
