import java.io.*;

public class Scanner{
    private Reader reader;
    private int line = 1;
    private int column = 1;
    private int character;
    private int index=0;
    private Token token;


    public Scanner() {
        reader = new Reader(new File("TestImmediate.asm"));
        token = new Token(new Position(line, column), "", null);

        try {
            this.character = reader.readChar();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Token scanToken()  {
        String mnemonic = "";
        String comment="";
        String operand="";
        String label ="";

        try {
            do{


                if(index == 0 && Character.isLetter(character)){
                    while(character!= 32 && character != 10){
                        label += (char)character;
                        character = reader.readChar();
                        index++;
                    }
                    label = label.trim();
                    token = new Token(new Position(line, column),label,TokenType.Label);
                    column++;
                    break;
                }


                if(character == 59){
                    while(character != 10){ // if first char is a ; then we will read until new line and output a comment
                        comment += (char)character;
                        character = reader.readChar();
                        index++;
                    }
                    comment = comment.trim();
                    token = new Token(new Position(line, column),comment,TokenType.Comment);
                    column++;
                    break;
                }

                if(Character.isLetter(character) && index > 0){
                    while(character!= 32 && character != 10){
                        mnemonic += (char)character;
                        character = reader.readChar();
                        index++;
                    }
                    mnemonic = mnemonic.trim();
                    token = new Token(new Position(line, column),mnemonic,TokenType.Mnemonic);
                    column++;
                    break;
                }

                if(Character.isDigit(character)){
                    while(character!= 32){
                        operand += (char)character;
                        character = reader.readChar();
                        index++;
                    }
                    token = new Token(new Position(line, column),operand,TokenType.Operand);
                    column++;
                    break;
                }

                if(character == 10){ // end-of-line
                    token = new Token(new Position(line, column),"EOL",TokenType.EOL);
                    column = 1;
                    line++;
                    this.character = reader.readChar();
                    index=0;
                    break;
                }
                character = reader.readChar();
                index++;

                if(character == -1){
                    token = new Token(new Position(line, column),"EOF",TokenType.EOF);
                }

            }while(character != -1);

        }

        catch (IOException e){
            e.printStackTrace();
        }
        return token;
    }



    public static void main(String[] args) {
        Scanner s1 = new Scanner();
        System.out.println(s1.scanToken());
        System.out.println(s1.scanToken());
        System.out.println(s1.scanToken());
        System.out.println(s1.scanToken());
        System.out.println(s1.scanToken());
        System.out.println(s1.scanToken());
        System.out.println(s1.scanToken());
        System.out.println(s1.scanToken());
        System.out.println(s1.scanToken());





    }



}
