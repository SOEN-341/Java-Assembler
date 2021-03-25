import java.io.File;
import java.io.IOException;

public class Assembler {
    public static void main(String[] args) {
        try{

            InterRep IR = new Parser().generates();
            new CodeGenerator(IR, new SymbolTable(), new File("TestImmediate.asm")).generateListing();

        }catch(Exception e){

        }

    }
}
