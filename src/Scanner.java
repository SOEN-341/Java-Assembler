import java.io.*;
import java.util.ArrayList;

public class Scanner{
    private Reader r1;
    private int line = 1;
    private int column = 1;
    private int pointer;


    public Scanner() {
        r1 = new Reader(new File("TestImmediate.asm"));
        try {
            this.pointer = r1.readChar();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Token scanToken()  {
        String mnemonic = "";
        String comment="";
        String operand="";
        Token t = null;

        try {
            do{
                if(pointer == 59){
                    while(pointer != 10){ // if first char is a ; then we will read until new line and output a comment
                        comment += (char)pointer;
                        pointer = r1.readChar();
                    }
                    comment = comment.trim();
                    t = new Token(new Position(line, column),comment,TokenType.Comment);
                    column++;
                    break;
                }

                if(Character.isLetter(pointer)){
                    while(pointer!= 32){
                        mnemonic += (char)pointer;
                        pointer = r1.readChar();
                    }
                    t = new Token(new Position(line, column),mnemonic,TokenType.Mnemonic);
                    column++;
                    break;
                }

                if(Character.isDigit(pointer)){
                    while(pointer!= 32){
                        operand += (char)pointer;
                        pointer = r1.readChar();
                    }
                    t = new Token(new Position(line, column),operand,TokenType.Operand);
                    column++;
                    break;
                }

                if(pointer == 10){ // end-of-line
                    t = new Token(new Position(line, column),"EOL",TokenType.EOL);
                    column = 1;
                    line++;
                    this.pointer = r1.readChar();
                    break;
                }
                pointer = r1.readChar();

                if(pointer == -1){
                    t = new Token(new Position(line, column),"EOF",TokenType.EOF);
                }

            }while(pointer != -1);

        }

        catch (IOException e){
            e.printStackTrace();
        }

        return t;
    }



  /*  public static void main(String[] args) {
        Scanner s1 = new Scanner();
        System.out.println(s1.scanToken());
        System.out.println(s1.scanToken());
        System.out.println(s1.scanToken());
        System.out.println(s1.scanToken());
        System.out.println(s1.scanToken());
        System.out.println(s1.scanToken());
        System.out.println(s1.scanToken());





    }*/



}
