import java.io.IOException;
import java.util.ArrayList;

// receives Arraylist<Token>

public class Parser implements IParser{
    private ArrayList<String> Mnemonic= new ArrayList<String>(10);
    private static int Count_Mnemonic=1;

    Parser(){
        Mnemonic=new ArrayList<String>();
    }

    public ArrayList<String> getMnemonic() throws IOException {
        int size = Scanner.scanToken().size();
        for (int i=0; i<size; i++){
            int LineNumber = Scanner.scanToken().get(i).getPosition().getLineNumber();

            int columnNumber = Scanner.scanToken().get(i).getPosition().getColumnNumber();
            if (LineNumber == Count_Mnemonic && columnNumber == 0 ){
                String MmeName = Scanner.scanToken().get(i).getName();
                System.out.println(MmeName);
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

    public InterRep generates () throws IOException {
        InterRep IR = new InterRep();
        for ( int i=0; i<getMnemonic().size(); i++){
            LineStatement Line = new LineStatement("", getMnemonic().get(i),"","" );
            IR.add(Line);
        }
        return  IR;
    }


}
