import java.io.*;
import java.util.ArrayList;

public class Scanner{
    private Reader r1;

    public Scanner() {
        r1 = new Reader(new File("TestInherentMnemonics.asm"));
    }

    public Token scanToken()  {
        String name = "";
        Token t = null;
        try {
            int c = r1.readChar();
            if (c == 32) {
                c = r1.readChar();
                while (c!= 10) {
                    name += (char) c;
                    c = r1.readChar();
                }
            }
            t = new Token(new Position(1, 1),name,TokenType.Mnemonic);
            return t;
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return t;
    }

}
