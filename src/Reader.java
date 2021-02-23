import java.io.*;

public class Reader {

    public static File readFile(File f)  {

        File out = null;
        int i=0;
        FileInputStream input = null;
        FileOutputStream output = null;

        try {
            out = new File("D:\\Projects\\Intellij Projects\\Java-Assembler\\copiedTestInherentMnemonics.asm");
            input = new FileInputStream(f);
            output = new FileOutputStream(out);

            while ((i = input.read()) != -1) {
                output.write(i);
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

public static void main(String [] args) {
        File f = new File("D:\\Projects\\Intellij Projects\\Java-Assembler\\TestInherentMnemonics.asm");
        readFile(f);
}

}

