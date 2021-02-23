import java.io.IOException;
import java.util.ArrayList;

public interface IParser {
    ArrayList<String> getMnemonic() throws IOException;
    InterRep generates () throws IOException;
}
