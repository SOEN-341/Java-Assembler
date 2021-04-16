import java.io.File;
import java.io.IOException;

public class Assembler {
    public static void main(String[] args) {
        try{
            if(args.length == 0){
                String[] temp = {"rela01.asm"};
                args = temp;
            }
            Validate v = new Validate();
            boolean validated = v.validate(args);
            Options options = new Options(args);
            if(validated && options.getHelp()){
                System.out.println("this is the help");
            }
            if(validated && options.getBanner()){
                System.out.println("this is the banner");
            }
            if(validated && !options.getHelp() && !options.getBanner()){
                SymbolTable symbolTable = new SymbolTable();
                ErrorReporter reporter = new ErrorReporter();
                InterRep IR = new Parser(new Scanner(args[args.length - 1], reporter, symbolTable)).generates();
                System.out.println(IR);
                new CodeGenerator(IR, symbolTable, new File(args[args.length - 1])).generateListing();
                reporter.report();
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}
