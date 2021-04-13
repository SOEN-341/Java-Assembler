import java.util.ArrayList;

public class Validate implements IValidate {

    public boolean validate(String[] arg) {

        ArrayList<String> args = new ArrayList<>();
        for(int i = 0; i < arg.length; i++){
            args.add(arg[i]);
        }

        for (String ar: args){
            if(ar.equals("-h") || ar.equals("-help") || ar.equals("-v") || ar.equals("-verbose")
                    || ar.equals("-b") || ar.equals("-banner") || ar.equals("-l") || ar.equals("-listing") || ar.contains(".asm")){

            }else{
                System.out.println( ar + " is an unknown input");
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
