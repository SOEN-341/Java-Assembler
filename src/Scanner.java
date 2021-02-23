import java.io.*;
import java.util.ArrayList;

public class Scanner {


    public static ArrayList<Token> scanToken()  {

        File f = null;
        File copy = null;
        FileInputStream input = null;
        int c = 0;
        int i=0;
        ArrayList<Token> token = null;
        String str = "";
        String [] mnemoic = new String[26];
        try {
            token = new ArrayList<Token>();
            f = new File("TestInherentMnemonics.asm");
            copy = Reader.readFile(f); // filed copy here
            input = new FileInputStream(copy); // opening the new stream on copied file.

            while (c != -1)
            {
                String name = "";
                c= input.read();

                if (c == 32) {
                    while (c != 10) {
                        c = input.read();
                        if (c != 10 && c != 32) {
                            name += (char)c;
                        }
                    }
                    mnemoic[i] = name;
                    i++;
                }

            }
            for (int k =0; k < mnemoic.length;k++) {
                Token t = new Token(new Position(k+1, 1), mnemoic[k], TokenType.Mnemonic);
                token.add(t);


            }
            //for(int h = 0; h < token.size(); h++)
                //System.out.println(token.get(h).getName());
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
