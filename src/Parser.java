import java.io.IOException;
import java.util.ArrayList;

// receives Arraylist<Token>

public class Parser implements IParser{
      private  InterRep IR = new InterRep();
      private String[] inherent={"halt","pop","dup","exit","ret","not","and","or","xor","neg","inc","dec","add",
              "sub","mul","div","rem","shl","shr", "teq","tne","tlt","tgt","tle","tge"};

    Parser(){

    }

    public void getToken(){
        Scanner S =new Scanner();
        Token t=S.scanToken();
        String Mne="";
        String label="";
        String comment="";
        String Operand="";

        while(!t.getName().equals("EOF")){
            if (t.getCode().equals("Mnemonic")){
                Mne=t.getName();
            }else if(t.getCode().equals("Label")){
                label=t.getName();
                }else if (t.getCode().equals("Comment")){
                comment=t.getName();
            }else if (t.getCode().equals("Operand")){
                Operand=t.getName();
            }
            if (t.getName().equals("EOL")){
               if(FindErrors(Mne,Operand)) {
                   addLS(Mne, label, comment, Operand);
               }
            }
            t=S.scanToken();
        }

    }
    public void addLS (String Mne, String label, String comment, String Operand) {

        String directive = "";
        LineStatement Line = null;
        if (Mne.contains(".cstring")){
            directive="CString";
             Line = new LineStatement(label, directive, comment );
        } else{
             Line = new LineStatement(label, Mne,Operand,comment );
        }
        IR.add(Line);
    }

    public boolean FindErrors(String Mne, String Operand){

        for (String s:inherent) {
            if (Mne.equals(s)){
                if (!Operand.equals("")){
                    System.out.println(Mne+" should not have an Operand field");
                    return false;
                }
            }
        }
        if (Mne.contains(".") && !Mne.equals(".cstring")){
            String Opcode=Mne.substring(Mne.indexOf(".")+1);
            String sign=Opcode.substring(0,1);
            int value= Integer.parseInt(Opcode.substring(1));
            if (sign.equals("u") ||sign.equals("i")){
                if (Operand.equals("")){
                    System.out.println(Mne+" should have an operand field");
                    return false;
                }else {
                    int Op=Integer.parseInt(Operand);
                    if (sign.equals("u")&& (Op<0 || Op>(Math.pow(2,value)-1))){
                        System.out.println(Mne +" the operand field out range");
                        return false;
                    }else if (sign.equals("i") && (Op<-1*Math.pow(2,value-1) || Op>Math.pow(2,value-1)-1)){
                        System.out.println(Mne +" the operand field out range");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public InterRep generates() {
        getToken();
        return IR;
    }






    //    public ArrayList<String> getMnemonic() {
//        ArrayList<String> Mnemonic= new ArrayList<String>();
//        ArrayList<Token> Tokens = Scanner.scanToken();
//        int size = Tokens.size();
//        for (int i=0; i<size; i++){
//            int LineNumber = Tokens.get(i).getPosition().getLineNumber();
//
//            int columnNumber =Tokens.get(i).getPosition().getColumnNumber();
//            if (LineNumber == Count_Mnemonic && columnNumber == 1 ){
//                String MmeName = Tokens.get(i).getName();
//                Mnemonic.add(MmeName);
//                Count_Mnemonic++;
//                continue;
//            }
//            // not required for sprint 2
//            if ( LineNumber == Count_Mnemonic+1){
//                Mnemonic.add("");
//                Count_Mnemonic++;
//            }
//        }
//        Mnemonic.trimToSize();
//
//        return Mnemonic;
//    }

//    public InterRep generates ()  {
//        InterRep IR = new InterRep();
//        ArrayList<String> Mme = getMnemonic();
//        for ( int i=0; i<Mme.size(); i++){
//            LineStatement Line = new LineStatement("", Mme.get(i).trim(),"","" );
//            IR.add(Line);
//        }
//        return  IR;
//    }
}
