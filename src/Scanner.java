import java.io.*;
import java.util.ArrayList;

public class Scanner {


    public static ArrayList<Token> scanToken() throws IOException {
        File f = new File("D:\\Projects\\Intellij Projects\\Java-Assembler\\copiedTestInherentMnemonics.asm");
        ArrayList<Token> token = new ArrayList<Token>();

        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String s= br.readLine();
        int i =0;
       while (s != null) {

          if (s.charAt(0) == ' ') {
              Token t = new Token(new Position(i, 0), s, TokenType.Mnemonic);
              token.add(t);
              i++;

          }
          s = br.readLine();

          }
       System.out.println(token);
          return token;
    }

}