
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

    public boolean getLabel(String label){
        if(Table.get(label)==null){
            return false;
        }
        return true;
    }

    public void addlabel(String label, int opcode){
        Table.put(label, new Mnemonic(label,opcode));
    }

    public void addMnemonic(String mne){
        if(mne.equals("halt"))
            Table.put("halt", new Mnemonic("halt", 0x00));
        if(mne.equals("halt"))
            Table.put("pop", new Mnemonic("pop", 0x01));
        if(mne.equals("dup"))
            Table.put("dup", new Mnemonic("dup", 0x02));
        if(mne.equals("exit"))
            Table.put("exit", new Mnemonic("exit", 0x03));
        if(mne.equals("ret"))
            Table.put("ret", new Mnemonic("ret", 0x04));
        if(mne.equals("not"))
            Table.put("not", new Mnemonic("not", 0x0C));
        if(mne.equals("and"))
            Table.put("and", new Mnemonic("and", 0x0D));
        if(mne.equals("or"))
            Table.put("or", new Mnemonic("or", 0x0E));
        if(mne.equals("xor"))
            Table.put("xor", new Mnemonic("xor", 0x0F));
        if(mne.equals("neg"))
            Table.put("neg", new Mnemonic("neg", 0x10));
        if(mne.equals("inc"))
            Table.put("inc", new Mnemonic("inc", 0x11));
        if(mne.equals("dec"))
            Table.put("dec", new Mnemonic("dec", 0x12));
        if(mne.equals("add"))
            Table.put("add", new Mnemonic("add", 0x13));
        if(mne.equals("sub"))
            Table.put("sub", new Mnemonic("sub", 0x14));
        if(mne.equals("mul"))
            Table.put("mul", new Mnemonic("mul", 0x15));
        if(mne.equals("div"))
            Table.put("div", new Mnemonic("div", 0x16));
        if(mne.equals("rem"))
            Table.put("rem", new Mnemonic("rem", 0x17));
        if(mne.equals("shl"))
            Table.put("shl", new Mnemonic("shl", 0x18));
        if(mne.equals("shr"))
            Table.put("shr", new Mnemonic("shr", 0x19));
        if(mne.equals("teq"))
            Table.put("teq", new Mnemonic("teq", 0x1A));
        if(mne.equals("tne"))
            Table.put("tne", new Mnemonic("tne", 0x1B));
        if(mne.equals("tlt"))
            Table.put("tlt", new Mnemonic("tlt", 0x1C));
        if(mne.equals("tgt"))
            Table.put("tgt", new Mnemonic("tgt", 0x1D));
        if(mne.equals("tle"))
            Table.put("tle", new Mnemonic("tle", 0x1E));
        if(mne.equals("tge"))
            Table.put("tge", new Mnemonic("tge", 0x1F));
        if(mne.equals("enter.u5"))
            Table.put("enter.u5", new Mnemonic("enter.u5", 0x80));
        if(mne.equals("ldc.i3"))
            Table.put("ldc.i3", new Mnemonic("ldc.i3", 0x90));
        if(mne.equals("addv.u3"))
            Table.put("addv.u3", new Mnemonic("addv.u3", 0x98));
        if(mne.equals("ldv.u3"))
            Table.put("ldv.u3", new Mnemonic("ldv.u3", 0xA0));
        if(mne.equals("ldv.u3"))
            Table.put("stv.u3", new Mnemonic("stv.u3", 0xA8));
        if(mne.equals("addv.u8"))
            Table.put("addv.u8", new Mnemonic("addv.u8", 0xB0));
        if(mne.equals("ldv.u8"))
            Table.put("ldv.u8", new Mnemonic("ldv.u8", 0xB1));
        if(mne.equals("stv.u8"))
            Table.put("stv.u8", new Mnemonic("stv.u8", 0xB2));
        if(mne.equals("incv.u8"))
            Table.put("incv.u8", new Mnemonic("incv.u8", 0xB3));
        if(mne.equals("decv.u8"))
            Table.put("decv.u8", new Mnemonic("decv.u8", 0xB4));
        if(mne.equals("enter.u8"))
            Table.put("enter.u8", new Mnemonic("enter.u8", 0xBF));
        if(mne.equals("lda.i16"))
            Table.put("lda.i16", new Mnemonic("lda.i16", 0xD5));
        if(mne.equals("ldc.i8"))
            Table.put("ldc.i8", new Mnemonic("ldc.i8", 0xD9));
        if(mne.equals("ldc.i16"))
            Table.put("ldc.i16", new Mnemonic("ldc.i16", 0xDA));
        if(mne.equals("ldc.i32"))
            Table.put("ldc.i32", new Mnemonic("ldc.i32", 0xDB));
        if(mne.equals("br.i8"))
            Table.put("br.i8", new Mnemonic("br.i8", 0xE0));
        if(mne.equals("br.i16"))
            Table.put("br.i16", new Mnemonic("br.i16", 0xE1));
        if(mne.equals("brf.i8"))
            Table.put("brf.i8", new Mnemonic("brf.i8", 0xE3));
        if(mne.equals("call.i16"))
            Table.put("call.i16", new Mnemonic("call.i16", 0xE7));
        if(mne.equals("trap"))
            Table.put("trap", new Mnemonic("trap", 0xFF));







    }

}


// String upcode = Table.getOpcode(arrylist.get(i).getMne());