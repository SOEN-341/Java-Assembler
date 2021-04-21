import java.io.File;

public class Assembler {
    public static void main(String[] args) {
        try{
            if(args.length == 0){
                String[] temp = {"-l","rela01.asm"};
                args = temp;
            }
            Validate v = new Validate();
            boolean validated = v.validate(args);
            Options options = new Options(args);
            if(validated && options.getHelp()){
                System.out.println("Usage: cma [ Options ] <file>.asm");
            }
            if(validated && options.getBanner()){
                System.out.println("Cm Cross-Assembler Version 1.4 - Developed by Team 11.");
            }
            if(validated && !options.getHelp() && !options.getBanner()){
                SymbolTable symbolTable = new SymbolTable();
                ErrorReporter reporter = new ErrorReporter();
                InterRep IR = new Parser(new Scanner(args[args.length - 1], reporter, symbolTable)).generates();
                //System.out.println(IR);
                if(options.getVerbose()){
                    System.out.println("Program running in verbose mode...");
                }
                CodeGenerator cG  = new CodeGenerator(IR, symbolTable, new File(args[args.length - 1]), options.getVerbose());
                if(options.getListing())
                    cG.generateListing();
                cG.generateExecutable();
                reporter.report();
            }

        }catch(Exception e){
            e.printStackTrace();
            //System.out.println(e.getMessage());
        }

    }
}
