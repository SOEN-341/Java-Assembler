import java.util.ArrayList;

public class Validate implements IValidate {

    public boolean validate(ArrayList<String> args) {

        for (String arg: args){
            if(arg.equals("-h") || arg.equals("-help") || arg.equals("-v") || arg.equals("-verbose")
                    || arg.equals("-b") || arg.equals("-banner") || arg.equals("-l") || arg.equals("-listing") || arg.contains(".asm")){

            }else{
                System.out.println(arg + "is an unknown option");
                return false;
            }
        }

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
            }

        }
        return true;
    }



}
