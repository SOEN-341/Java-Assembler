import java.io.*;
import java.util.ArrayList;

public class Scanner{

    public static ArrayList<Token> scanToken()  {

        File f = null;
        FileInputStream input = null;
        int c = 0;
        int i=0;
        ArrayList<Token> token = null;
        String str = "";
        String [] mnemoic = new String[26];
        ArrayList<String> mnemoic1= new ArrayList<String>();
        try {
            token = new ArrayList<Token>();
            f = new File("TestInherentMnemonics.asm");
            File copy = Reader.readFile(f);

            input = new FileInputStream(copy); // opening the new stream on copied file.

            while (c != -1)
            {
                String name = "";
                c = input.read();
                if (c == 32) {
                    while (c != 10 && c !=-1) {
                        c = input.read();
                        if (c != 10 && c != 32 && c !=-1) {
                            name += (char)c;
                        }
                    }
                    mnemoic1.add(name);
                }

            }
            for (int k =0; k < mnemoic1.size();k++) {
                Token t = new Token(new Position(k+1, 1), mnemoic1.get(k), TokenType.Mnemonic);
                token.add(t);


            }

         // System.out.println(token);
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
