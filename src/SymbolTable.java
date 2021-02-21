import java.util.Hashtable;
public class SymbolTable {
    private Hashtable Table;
    // This is going to be the hardcode for the 25 mne
    public SymbolTable(){
        this.Table = new Hashtable();
        Table.put("hlt", "00");
        Table.put("pop", "01");



    }

    public String getOpcode(String mnemonic){
        return (String)Table.get(mnemonic);
    }

}


// String upcode = Table.getOpcode(arrylist.get(i).getMne());