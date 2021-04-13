import java.util.ArrayList;
import java.util.Set;

public class Parser implements IParser{

    private  InterRep IR = new InterRep();
    private Scanner S;



    private String[] inherent = {"halt","pop","dup","exit","ret","not","and","or","xor","neg","inc","dec","add",
              "sub","mul","div","rem","shl","shr", "teq","tne","tlt","tgt","tle","tge"};

    private String[] AllMnemonics = {"halt","pop","dup","exit","ret","not","and","or","xor","neg","inc","dec","add",
            "sub","mul","div","rem","shl","shr", "teq","tne","tlt","tgt","tle","tge","addv","br","brf","call","decv",
            "enter","incv","ldc","ldv","ret","stb","trap","stv"};
    private String[] relative={"br","lda","brf","ldc","ldv","stv","addv", "incv", "decv","enter","call","trap"};

    private ArrayList<String> labelList= new ArrayList<>();

    Parser(Scanner s){
        this.S = s;
    }

    public void getToken(){
        Token t = S.scanToken();
        String Mne="";
        String label="";
        String comment="";
        String Operand="";
        Position positionMNe = null;
        Position positionLabel = null;

        while(!t.getName().equals("EOF")){
            if (t.getCode().toString().equals("Mnemonic")){
                Mne=t.getName();
                positionMNe = t.getPosition();
            }else if(t.getCode().toString().equals("Label")){
                label=t.getName();
                positionLabel = t.getPosition();
            }else if (t.getCode().toString().equals("Comment")){
                comment=t.getName().substring(2);
            }else if (t.getCode().toString().equals("Operand")){
                Operand=t.getName();
            }
            if (t.getName().equals("EOL")){
                if(FindMnemonicErrors(Mne,Operand, positionMNe) && FindLabelErrors(label,positionLabel )) {
                    labelList.add(label);
                    CreateLS(Mne, label, comment, Operand);
                }
                Mne="";
                label="";
                comment="";
                Operand="";

            }
            t=S.scanToken();
        }
    }


    public void CreateLS (String Mne, String label, String comment, String Operand) {
        String directive = "";
        LineStatement Line = null;
        if (Mne.contains(".cstring")){
            directive="CString";
             Line = new LineStatement(label, directive,Operand,comment );
        } else{
             Line = new LineStatement(label, Mne,Operand,comment );
        }
        IR.add(Line);
    }


   private boolean FindLabelErrors(String label,Position position ) {
        for(int i=0; i<labelList.size(); i++) {
            if(labelList.get(i).equals(label)) {
                ErrorMsg message = new ErrorMsg(label+" label already defined", position);
                S.getReporter().record(message);
                return false;
            }
        }
        return true;
    }


    private boolean FindMnemonicErrors(String Mne, String Operand,Position position){
        if (!Mne.equals("")) {
            // checks if the mnemonic exists
            if (!Mne.equals(".cstring")) {
               if (!MnemonicExists(Mne,position)) {
                   return false;
               }
            }
            // checks if it inherent mnemonic
            if (!inherentMnemonic(Mne,Operand, position)){
                return false;
            }

            if (Mne.contains(".") && !Mne.equals(".cstring")) {
                int last = Integer.parseInt(Mne.substring(Mne.length() - 1));
                if (last > 5) {
                        relativeInstruction(Mne, Operand, position);
                } else {
                    //check for immediate instructions
                    if (!NoOperandFound(Mne, Operand, position)) {
                        return false;
                    } else {
                        if (!UnsignedRange(Mne, Operand, position)) {
                            return false;
                        } else {
                            if (!signedRange(Mne, Operand, position)) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private boolean MnemonicExists(String Mne, Position position){
        String m="";
        if (Mne.contains(".")) {
            m = Mne.substring(0, Mne.indexOf("."));
        } else {
            m = Mne;
        }
        for (int i = 0; i < AllMnemonics.length; i++) {
            if (m.equals(AllMnemonics[i])) {
                break;
            } else if (!m.equals(AllMnemonics[i]) && i == AllMnemonics.length - 1) {
                ErrorMsg message = new ErrorMsg("Not a valid mnemonic or directive.", position);
                S.getReporter().record(message);
                return false;
            }
        }
        return  true;
    }

    private boolean inherentMnemonic(String Mne, String Operand, Position position){

        for (String s : inherent) {
            if (Mne.equals(s)) {
                if (!Operand.equals("")) {
                    ErrorMsg message = new ErrorMsg("An inherent instruction has no operand.", position);
                    S.getReporter().record(message);
                    return false;
                }
            }
        }
        return  true;
    }

    private boolean NoOperandFound(String Mne, String Operand, Position position){
        String Opcode = Mne.substring(Mne.indexOf(".") + 1);
        String sign = Opcode.substring(0, 1);
        int value = Integer.parseInt(Opcode.substring(1));
        if (sign.equals("u") || sign.equals("i")) {
            if (Operand.equals("")) {
                if (value == 3 || value == 5) {
                    ErrorMsg message = new ErrorMsg("An immediate instruction always requires an operand.", position);
                    S.getReporter().record(message);
                } else {
                    ErrorMsg message = new ErrorMsg("A relative instruction always requires an operand.", position);
                    S.getReporter().record(message);
                }
                return false;
            }
        }
        return  true;
    }

    private boolean UnsignedRange(String Mne, String Operand, Position position) {
        String Opcode = Mne.substring(Mne.indexOf(".") + 1);
        String sign = Opcode.substring(0, 1);
        int value = Integer.parseInt(Opcode.substring(1));
        int Op = Integer.parseInt(Operand);
        double max = Math.pow(2, value) - 1;
        if (sign.equals("u") && (Op < 0 || Op > max)) {
            if (value == 3 || value == 5) {
                ErrorMsg message = new ErrorMsg("The immediate instruction \'" + Mne + "\' must have a " + value +
                        "-bit unsigned operand number ranging from 0 to " +(int) max + ".", position);
                S.getReporter().record(message);
            } else {
                ErrorMsg message = new ErrorMsg("The relative instruction \'" + Mne + "\' must have a " + value +
                        "-bit unsigned operand number ranging from 0 to " + (int) max + ".", position);
                S.getReporter().record(message);
            }
            return false;
        }
    return  true;
    }


    private boolean signedRange(String Mne, String Operand, Position position) {
        String Opcode = Mne.substring(Mne.indexOf(".") + 1);
        String sign = Opcode.substring(0, 1);
        int value = Integer.parseInt(Opcode.substring(1));
        int Op = Integer.parseInt(Operand);
        double min = -1 * Math.pow(2, (value - 1));
        double max = Math.pow(2, value - 1) - 1;
        if (sign.equals("i") && (Op < min || Op > max)) {
            if (value == 3 || value == 5) {
                ErrorMsg message = new ErrorMsg("The immediate instruction \'" + Mne + "\' must have a " + value +
                        "-bit signed operand number ranging from " + (int)min + " to " + (int)max + ".", position);
                S.getReporter().record(message);
            } else {
                ErrorMsg message = new ErrorMsg("The relative instruction \'" + Mne + "\' must have a " + value +
                        "-bit signed operand number ranging from " + (int)min + " to " + (int)max + ".", position);
                S.getReporter().record(message);
            }
            return false;
        }
    return  true;
    }


    private boolean relativeInstruction (String Mne, String Operand, Position position){
        String Mnemonic = Mne.substring(0, Mne.indexOf("."));
        int count=0;
        for (int i=0; i<relative.length; i++){
            if (relative[i].equals(Mnemonic)){
                break;
            }
            count++;
        }
        if ( count ==relative.length){
            ErrorMsg message = new ErrorMsg("relative instruction is not found", position);
            S.getReporter().record(message);
            return false;
        }
        if (Mnemonic.equals("lda") || Mnemonic.equals("br") || Mnemonic.equals("brf")){
            try{
                int op = Integer.parseInt(Operand);
                ErrorMsg message = new ErrorMsg("Operand must refer to a label", position);
                S.getReporter().record(message);
                return false;

            }catch (NumberFormatException e){ }
//              ***************** check if the operand exist as label




//            int length=S.getSymbolTable().size();
//            Set<String> keys = S.getSymbolTable().getKeys();
//            for( String key : keys){
//                if (Operand.equals(key)){
//
//               }
//            }

        }else{
            if (!UnsignedRange(Mne, Operand, position)) {
                return false;
            } else {
                if (!signedRange(Mne, Operand, position)) {
                    return false;
                }
            }
        }
        return true;
    }

        public InterRep generates() {
        getToken();
        return IR;
    }


}
