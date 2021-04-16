import java.util.ArrayList;

public class Helper {
    public boolean isInherent(String mne){
        ArrayList<String> Inherent = new ArrayList<>();
        Inherent.add("halt");Inherent.add("pop");
        Inherent.add("dup");Inherent.add("exit");
        Inherent.add("ret");Inherent.add("not");
        Inherent.add("and");Inherent.add("or");
        Inherent.add("xor");Inherent.add("neg");
        Inherent.add("inc");Inherent.add("dec");
        Inherent.add("add");Inherent.add("sub");
        Inherent.add("mul");Inherent.add("div");
        Inherent.add("rem");Inherent.add("shl");
        Inherent.add("shr");Inherent.add("teq");
        Inherent.add("tne");Inherent.add("tlt");
        Inherent.add("tgt");Inherent.add("tle");
        Inherent.add("tge");

        return Inherent.contains(mne);
    }

    public boolean isImmediate(String mne) {
        ArrayList<String> Immediate = new ArrayList<>();
        Immediate.add("br.i5");Immediate.add("brf.15");Immediate.add("enter.u5");
        Immediate.add("ldc.i3");Immediate.add("addv.u3");Immediate.add("ldv.u3");
        Immediate.add("stv.u3");

        return Immediate.contains(mne);
    }
    public boolean isRelative(String mne){
        ArrayList<String> Relative = new ArrayList<>();
        Relative.add("addv.u8");Relative.add("ldv.u8");Relative.add("stv.u8");
        Relative.add("incv.u8");Relative.add("decv.u8");Relative.add("enter.u8");
        Relative.add("lda.i16");Relative.add("ldc.i8");Relative.add("ldc.i16");
        Relative.add("ldc.i32");Relative.add("br.i8");Relative.add("br.i16");
        Relative.add("brf.i8");Relative.add("call.i16");Relative.add("trap");

        return Relative.contains(mne);
    }

    public int offset255(int i, int j){
        int temp = j - i;
        if(temp < 0){
            temp = 256 + temp;
        }

        return temp;
    }



}
