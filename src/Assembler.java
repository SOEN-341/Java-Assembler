import java.io.File;
import java.io.IOException;

public class Assembler {
    public static void main(String[] args) {
        try{
       //     Reader.readFile(new File("TestInherentMnemonics.asm"));
            InterRep IR = new Parser().generates();
            new CodeGenerator(IR, new SymbolTable(), new File("TestInherentMnemonics.asm")).generateListing();

        }catch(Exception e){

        }

    }
}
