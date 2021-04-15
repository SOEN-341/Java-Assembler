import java.io.File;
import java.io.IOException;

public class Assembler {
    public static void main(String[] args) {
        try{
            Validate v = new Validate();
            boolean validated = v.validate(args);
            Options options = new Options(args);
            if(v.validate(args) && options.getHelp()){
                System.out.println();
            }
            if(v.validate(args) && options.getBanner()){
                System.out.println();
            }
            if(true && !options.getHelp() && !options.getBanner()){
                SymbolTable symbolTable = new SymbolTable();
                ErrorReporter reporter = new ErrorReporter();
                InterRep IR = new Parser(new Scanner("TestImmediate.asm", reporter, symbolTable)).generates();
                System.out.println(IR);
                new CodeGenerator(IR, symbolTable, new File("TestImmediate.asm")).generateListing();
                reporter.report();
            }
        }catch(Exception e){
        }

    }
}
