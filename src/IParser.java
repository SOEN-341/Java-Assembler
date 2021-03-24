
public interface IParser {
    InterRep generates ();
    boolean FindErrors(String Mne, String Operand,Position position);
    void CreateLS (String Mne, String label, String comment, String Operand);
    void getToken();
}
