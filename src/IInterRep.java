import java.util.ArrayList;

public interface IInterRep {
    LineStatement getLS(int index);
    int getSize();
    void add(LineStatement ls);

}
