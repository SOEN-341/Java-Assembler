import java.io.*;

public class Reader {

    public static void main(String [] args) throws IOException {



        File f = new File("D:\\Projects\\Intellij Projects\\Java-Assembler\\TestInherentMnemonics.asm");
        FileReader fr = new FileReader(f);
        BufferedReader bf = new BufferedReader(fr);
        String line;
        File out = new File("D:\\Projects\\Intellij Projects\\Java-Assembler\\copiedTestInherentMnemonics.asm");
        FileWriter fw = new FileWriter(out);
        PrintWriter pw = new PrintWriter(fw);

        while ((line = bf.readLine()) != null) {
            pw.println(line);
        }
        pw.close();

    }

    }

