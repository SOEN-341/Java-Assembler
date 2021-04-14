import java.io.*;

public class Reader implements IReader{
    // reader will read one char and pass it to scanner to create a token
    private FileInputStream f;
    private File copied;

    public Reader(File file) {

        try{
            copied =  copy(file);
            f = new FileInputStream(copied);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }

    public File copy(File f) throws IOException {
        FileInputStream input = null;
        FileOutputStream output = null;
        int c = 0;
        File out = null;

        try { // create a copie of the original file

            out = new File("copied" + f.getName());
            input = new FileInputStream(f);
            output = new FileOutputStream(out);

            while ((c = input.read()) != -1) {
                output.write(c);
            }
            input.close();
            output.close();

        }

        catch(IOException e) {
            e.printStackTrace();
        }
        return out;
    }


    public int readChar () throws IOException{
        int c;
        c =  f.read();
        return c;

    }

    public void closeInputStream() throws IOException{
        f.close();
    }
    public void openInputStream() throws IOException{
        f = new FileInputStream(copied);
    }
}

