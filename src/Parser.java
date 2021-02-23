import java.io.IOException;
import java.util.ArrayList;

// receives Arraylist<Token>

public class Parser implements IParser{
    private ArrayList<String> Mnemonic= new ArrayList<String>();
    private static int Count_Mnemonic=1;

    Parser(){
        Mnemonic=new ArrayList<String>();
    }

    public ArrayList<String> getMnemonic() {
        ArrayList<Token> Tokens= Scanner.scanToken();
        int size = Tokens.size();
        for (int i=0; i<size; i++){
            int LineNumber = Tokens.get(i).getPosition().getLineNumber();

            int columnNumber =Tokens.get(i).getPosition().getColumnNumber();
            if (LineNumber == Count_Mnemonic && columnNumber == 1 ){
                String MmeName = Tokens.get(i).getName();
                Mnemonic.add(MmeName);
                Count_Mnemonic++;
                continue;
            }
            if ( LineNumber == Count_Mnemonic+1){
                Mnemonic.add("");
                Count_Mnemonic++;
            }
        }
        Mnemonic.trimToSize();
        return Mnemonic;
    }

    public InterRep generates ()  {
        InterRep IR = new InterRep();
        ArrayList<String> Mme=getMnemonic();
        for ( int i=0; i<Mme.size(); i++){
            LineStatement Line = new LineStatement("", Mme.get(i).trim(),"","" );
            IR.add(Line);
        }
        return  IR;
    }


}
