import java.io.*;
import java.util.ArrayList;

public class Scanner {


    public static ArrayList<Token> scanToken() throws IOException {

        File f = null;
        File copy = null;
        FileReader fr = null;
        BufferedReader br = null;
        String s = null;
        ArrayList<Token> token = null;

        try {
            token = new ArrayList<Token>();
            f = new File("D:\\Projects\\Intellij Projects\\Java-Assembler\\copiedTestInherentMnemonics.asm");
            copy = Reader.readFile(f);
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            s= br.readLine();
            int i =0;

            while (s != null) {

                if (s.charAt(0) == ' ') {
                    ++i;
                    Token t = new Token(new Position(i, 0), s, TokenType.Mnemonic);
                    token.add(t);


                }
                s = br.readLine();

            }
            System.out.println(token);
            return token;
        }
        catch(IOException e) {
            e.printStackTrace();
        }
      return token;
    }


}
