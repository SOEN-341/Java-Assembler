import java.io.FileInputStream;
import java.io.*;

public class TestReader {
    public static void main(String[] args) throws IOException{


        System.out.println("Test Reader");
        System.out.println("equal");
        File f = new File("/Users/alirezaziarizi/Documents/Winter 2021/COMP 472/Java-Assembler/TestInherentMnemonics.asm");
        File c = new File("/Users/alirezaziarizi/Documents/Winter 2021/COMP 472/Java-Assembler/copiedTestInherentMnemonics.asm");
        // Reader.readFile(f);
        FileInputStream original = new FileInputStream(f);
        FileInputStream copied = new FileInputStream (c);
        int i=0;
        int j=0;
        while (i!= -1 && j!=-1) {
            i=original.read();
            j=copied.read();
            if (i!=j)
                break;
        }
        if (i!=j) {
            System.out.println("not equal");
        }
        else
            System.out.println("equal");
    }
}

