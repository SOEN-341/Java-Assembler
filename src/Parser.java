import java.io.IOException;
import java.util.ArrayList;

// receives Arraylist<Token>

public class Parser {
    private static ArrayList<String> Mnemonic= new ArrayList<String>(10);
    private static int Count_Mnemonic=1;



    public static ArrayList<String> getMnemonic() throws IOException {
        int size= Scanner.scanToken().size();
        for (int i=0; i<size; i++){
            int LineNumber=Scanner.scanToken().get(i).getPosition().getLineNumber();

            int columnNumber= Scanner.scanToken().get(i).getPosition().getColumnNumber();
            if (LineNumber==Count_Mnemonic && columnNumber==0 ){
                String MmeName= Scanner.scanToken().get(i).getName();
                System.out.println(MmeName);
                Mnemonic.add(MmeName);
                Count_Mnemonic++;
                continue;
            }
            if ( LineNumber== Count_Mnemonic+1){
                Mnemonic.add("");
                Count_Mnemonic++;
            }
        }
        Mnemonic.trimToSize();
        return Mnemonic;
    }



}
