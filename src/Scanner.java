import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Scanner {


    public static ArrayList<Token> scanToken()  {

        File f = null;
        File copy = null;
        FileInputStream input = null;
        int c = 0;
        int i=1;
        ArrayList<Token> token = null;
        String str = "";

        try {
            token = new ArrayList<Token>();
            f = new File("D:\\Projects\\Intellij Projects\\Java-Assembler\\TestInherentMnemonics.asm");
            copy = Reader.readFile(f);
            input = new FileInputStream(copy);
            String [] array = new String[27];
            while (c != -1)
            {
                c= input.read();
                if (c == 32) {

                    while (c != 10) {
                        c = input.read();
                        if (c != 10) {
                            array[i] += (char)c;
                        }
                    }
                    i++;
                }
             /*   Token t = new Token(new Position(i,1), name, TokenType.Mnemonic);
                token.add(t);
                i++; */

            }
            System.out.println(array[0]);

            //   System.out.println(token);
            input.close();
            return token;
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return token;
    }
    public static void main(String[] args) {

        scanToken();
    }

}
