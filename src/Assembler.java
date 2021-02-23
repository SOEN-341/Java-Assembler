import java.io.File;
import java.io.IOException;

public class Assembler {
    public static void main(String[] args) {
        try{
            File copyFile = Reader.readFile(new File("TestInherentMnemonics.asm"));

            InterRep IR = new Parser().generates();


            CodeGenerator.generateListing(IR, new SymbolTable(), copyFile);

        }catch(IOException e){

        }


    }
}
