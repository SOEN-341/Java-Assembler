import java.io.*;

public class Reader {

    public static File readFile(File f) throws IOException {
        FileReader fr = null;
        BufferedReader bf = null;
        String line = null;
        File out = null;
        FileWriter fw = null;
        PrintWriter pw = null;

        try {
            fr = new FileReader(f);
             bf = new BufferedReader(fr);

             out = new File("D:\\Projects\\Intellij Projects\\Java-Assembler\\copiedTestInherentMnemonics.asm");
             fw = new FileWriter(out);
             pw = new PrintWriter(fw);

            while ((line = bf.readLine()) != null) {
                pw.println(line);
            }
            pw.close();
            return out;
        }

        catch(IOException e) {
            e.printStackTrace();
        }
     return out;
    }



}

