import java.io.File;
import java.io.IOException;

public class Assembler {
    public static void main(String[] args) {
        try{
            InterRep IR = new Parser().generates();
            CodeGenerator.generateListing(IR, new SymbolTable(), new File("TestInherentMnemonics.asm"));

        }catch(Exception e){

        }


    }
}
