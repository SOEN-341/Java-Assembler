import java.io.*;

public class Reader{

    public static File readFile(File f) throws IOException {
        FileInputStream input = null;
        FileOutputStream output = null;
        int c = 0;
        File out = null;

        try {

            out = new File("copied" + f.getName());
            input = new FileInputStream(f);
            output = new FileOutputStream(out);

            while ((c = input.read()) != -1) {
                output.write(c);
            }
            input.close();
            output.close();

            return out;
        }

        catch(IOException e) {
            e.printStackTrace();
        }
        return out;
    }


}

