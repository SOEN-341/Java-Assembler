import java.io.File;
import java.io.IOException;

public interface IReader {

    public File copy(File f)throws IOException;

    public int readChar ()throws IOException;

    public void closeInputStream()throws IOException;

}
