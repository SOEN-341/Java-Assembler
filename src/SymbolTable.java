import java.util.Hashtable;
public class SymbolTable implements ISymbolTable{
    private Hashtable Table;
    // This is going to be the hardcode for the 25 mne
    public SymbolTable(){
        this.Table = new Hashtable();
        Table.put("hlt", 0x00);
        Table.put("pop", 0x01);
        Table.put("exit", 0x03);
        Table.put("ret", 0x04);
        Table.put("not", 0x0C);
        Table.put("and", 0x0D);
        Table.put("or", 0x0E);
        Table.put("xor", 0x0F);
        Table.put("neg", 0x10);
        Table.put("inc", 0x11);
        Table.put("dec", 0x12);
        Table.put("add", 0x13);
        Table.put("sub", 0x14);
        Table.put("mul", 0x15);
        Table.put("div", 0x16);
        Table.put("rem", 0x17);
        Table.put("shl", 0x18);
        Table.put("shr", 0x19);
        Table.put("teq", 0x1A);
        Table.put("tne", 0x1B);
        Table.put("tlt", 0x1C);
        Table.put("tgt", 0x1D);
        Table.put("tle", 0x1E);
        Table.put("tge", 0x1F);






    }

    public int getOpcode(String mnemonic){
        return (int)Table.get(mnemonic);
    }

}


// String upcode = Table.getOpcode(arrylist.get(i).getMne());