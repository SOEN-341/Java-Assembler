import java.io.File;
import java.util.ArrayList;

public class Validate implements IValidate {

    public boolean validate(String[] arg) {
        //System.out.println("validating");
        // args from array to ArrayList
        ArrayList<String> args = new ArrayList<>();
        for(int i = 0; i < arg.length; i++){
            args.add(arg[i]);
        }
        //System.out.println(args);
        //check for valide input (option or asm file)
        for (String ar: args){
            if(ar.equals("-h") || ar.equals("-help") || ar.equals("-v") || ar.equals("-verbose")
                    || ar.equals("-b") || ar.equals("-banner") || ar.equals("-l") || ar.equals("-listing") || ar.contains(".asm")){

            }else{
                System.out.println( ar + " is an unknown input");
                return false;
            }
        }
        // ctr for options
        int optionsCtr = 0;

        if(args.contains("-h") || args.contains("-help")){
            if(optionsCtr > 1){
                System.out.println("Invalid input: more than one option is enabled");
                return false;
            }
            optionsCtr++;
            if(args.get(args.size() - 1).contains(".asm")){
                System.out.println("Invalid input: -help option does not take a input file");
                return false;
            }
        }
        if(args.contains("-v") || args.contains("-verbose")){
            if(optionsCtr > 1){
                System.out.println("Invalid input: more than one option is enabled");
                return false;
            }
            optionsCtr++;
            if(!args.get(args.size() - 1).contains(".asm")){
                System.out.println("-verbose option requires an input file");
                return false;
            }
        }
        if(args.contains("-b") || args.contains("-banner")){
            if(optionsCtr > 1){
                System.out.println("Invalid input: more than one option is enabled");
                return false;
            }
            optionsCtr++;
            if(args.get(args.size() - 1).contains(".asm")){
                System.out.println("Invalid input: -banner option does not take a input file");
                return false;
            }

        }
        if(args.contains("-l") || args.contains("-listing")){
            if(optionsCtr > 1){
                System.out.println("Invalid input: more than one option is enabled");
                return false;
            }
            optionsCtr++;
            if(!args.get(args.size() - 1).contains(".asm")){
                System.out.println("-listing option requires an input file");
                return false;
            }

        }

        if(args.size() == 0){
            System.out.println("no file or options passed to the program");
            return false;
        }

        // check if .asm file exists
        if(args.get(args.size() - 1).contains(".asm")){
            File temp = new File(args.get(args.size() - 1));
            if(!temp.exists()){
                System.out.println("Input file does not exist");
                return false;
            }
        }


        return true;
    }



}
