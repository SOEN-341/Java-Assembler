
import java.util.Hashtable;
import java.util.Set;

public class SymbolTable implements ISymbolTable{
    private Hashtable<String, Mnemonic> Table;
    // This is going to be the hardcode for the 25 mne
    public SymbolTable(){
        this.Table = new Hashtable<String, Mnemonic>();
        Table.put("halt", new Mnemonic("halt", 0x00));
        Table.put("pop", new Mnemonic("pop", 0x01));
        Table.put("dup", new Mnemonic("dup", 0x02));
        Table.put("exit", new Mnemonic("exit", 0x03));
        Table.put("ret", new Mnemonic("ret", 0x04));
        Table.put("not", new Mnemonic("not", 0x0C));
        Table.put("and", new Mnemonic("and", 0x0D));
        Table.put("or", new Mnemonic("or", 0x0E));
        Table.put("xor", new Mnemonic("xor", 0x0F));
        Table.put("neg", new Mnemonic("neg", 0x10));
        Table.put("inc", new Mnemonic("inc", 0x11));
        Table.put("dec", new Mnemonic("dec", 0x12));
        Table.put("add", new Mnemonic("add", 0x13));
        Table.put("sub", new Mnemonic("sub", 0x14));
        Table.put("mul", new Mnemonic("mul", 0x15));
        Table.put("div", new Mnemonic("div", 0x16));
        Table.put("rem", new Mnemonic("rem", 0x17));
        Table.put("shl", new Mnemonic("shl", 0x18));
        Table.put("shr", new Mnemonic("shr", 0x19));
        Table.put("teq", new Mnemonic("teq", 0x1A));
        Table.put("tne", new Mnemonic("tne", 0x1B));
        Table.put("tlt", new Mnemonic("tlt", 0x1C));
        Table.put("tgt", new Mnemonic("tgt", 0x1D));
        Table.put("tle", new Mnemonic("tle", 0x1E));
        Table.put("tge", new Mnemonic("tge", 0x1F));
        Table.put("enter.u5", new Mnemonic("enter.u5", 0x80));
        Table.put("ldc.i3", new Mnemonic("ldc.i3", 0x90));
        Table.put("addv.u3", new Mnemonic("addv.u3", 0x98));
        Table.put("ldv.u3", new Mnemonic("ldv.u3", 0xA0));
        Table.put("stv.u3", new Mnemonic("stv.u3", 0xA8));





    }
    public int size(){
        return Table.size();
    }
//    public Set getKeys(){
//        Set<String> keys = Table.keySet();
//        return keys;
//    }

    public int getOpcode(String mnemonic){
        return (Table.get(mnemonic)).getOpcode();
    }

}


// String upcode = Table.getOpcode(arrylist.get(i).getMne());