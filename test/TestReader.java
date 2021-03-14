import java.io.FileInputStream;
import java.io.*;


public class TestReader {
    public static void main(String[] args) throws IOException{


        System.out.println("Test Reader");
        System.out.println("equal");
        Reader r1 = new Reader(new File("TestInherentMnemonics.asm"));
        System.out.println("h");
        r1.readChar();
        System.out.println(r1.readChar());

    }
}

