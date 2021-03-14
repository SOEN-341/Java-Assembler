import java.io.File;

public interface IReader {

    public File copy(File f);

    public int readChar ();

    public void closeInputStream();

}
