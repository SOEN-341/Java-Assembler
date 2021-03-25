
public class Parser implements IParser{

    private  InterRep IR = new InterRep();
    private ErrorReporter error = new ErrorReporter();
    private Scanner S;


    private String[] inherent = {"halt","pop","dup","exit","ret","not","and","or","xor","neg","inc","dec","add",
              "sub","mul","div","rem","shl","shr", "teq","tne","tlt","tgt","tle","tge"};

    private String[] AllMnemonics = {"halt","pop","dup","exit","ret","not","and","or","xor","neg","inc","dec","add",
            "sub","mul","div","rem","shl","shr", "teq","tne","tlt","tgt","tle","tge","addv","br","brf","call","decv",
            "enter","incv","ldc","ldv","ret","stb","trap","stv"};



    Parser(Scanner s){
        this.S = s;
    }

    public void getToken(){
        //Scanner S = new Scanner("TestImmediate.asm");
        Token t = S.scanToken();
        String Mne="";
        String label="";
        String comment="";
        String Operand="";
        Position positionMNe = null;

        while(!t.getName().equals("EOF")){
            if (t.getCode().toString().equals("Mnemonic")){
                Mne=t.getName();
                positionMNe = t.getPosition();
            }else if(t.getCode().toString().equals("Label")){
                label=t.getName();
                }else if (t.getCode().toString().equals("Comment")){
                comment=t.getName().substring(2);
            }else if (t.getCode().toString().equals("Operand")){
                Operand=t.getName();
            }
            if (t.getName().equals("EOL")){
                if(FindErrors(Mne,Operand, positionMNe)) {
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


    public boolean FindErrors(String Mne, String Operand,Position position){
        if (!Mne.equals("")) {
            String m = "";
            if (!Mne.equals(".cstring")) {
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
                        error.record(message);
                        return false;
                    }
                }
            }

            for (String s : inherent) {
                if (Mne.equals(s)) {
                    if (!Operand.equals("")) {
                        ErrorMsg message = new ErrorMsg("An inherent instruction has no operand.", position);
                        error.record(message);
                        return false;
                    }
                }
            }

            if (Mne.contains(".") && !Mne.equals(".cstring")) {
                String Opcode = Mne.substring(Mne.indexOf(".") + 1);
                String sign = Opcode.substring(0, 1);
                int value = Integer.parseInt(Opcode.substring(1));
                if (sign.equals("u") || sign.equals("i")) {
                    if (Operand.equals("")) {
                        if (value == 3 || value == 5) {
                            ErrorMsg message = new ErrorMsg("An immediate instruction always requires an operand.", position);
                            error.record(message);
                        } else {
                            ErrorMsg message = new ErrorMsg("A relative instruction always requires an operand.", position);
                            error.record(message);
                        }
                        return false;
                    } else {
                        int Op = Integer.parseInt(Operand);
                        double max = Math.pow(2, value) - 1;
                        if (sign.equals("u") && (Op < 0 || Op > max)) {
                            if (value == 3 || value == 5) {
                                ErrorMsg message = new ErrorMsg("The immediate instruction \'" + Mne + "\' must have a " + value +
                                        "-bit unsigned operand number ranging from 0 to " + max + ".", position);
                                error.record(message);
                            } else {
                                ErrorMsg message = new ErrorMsg("The relative instruction \'" + Mne + "\' must have a " + value +
                                        "-bit unsigned operand number ranging from 0 to " + max + ".", position);
                                error.record(message);
                            }
                            return false;
                        } else {
                            double min = -1 * Math.pow(2, (value - 1));
                            max = Math.pow(2, value - 1) - 1;
                            if (sign.equals("i") && (Op < min || Op > max)) {
                                if (value == 3 || value == 5) {
                                    ErrorMsg message = new ErrorMsg("The immediate instruction \'" + Mne + "\' must have a " + value +
                                            "-bit signed operand number ranging from " + min + " to " + max + ".", position);
                                    error.record(message);
                                } else {
                                    ErrorMsg message = new ErrorMsg("The relative instruction \'" + Mne + "\' must have a " + value +
                                            "-bit signed operand number ranging from " + min + " to " + max + ".", position);
                                    error.record(message);
                                }
                                return false;
                            }

                        }
                    }
                }
            }
        }
        return true;
    }

    public InterRep generates() {
        getToken();
        error.report();
        return IR;
    }


}
