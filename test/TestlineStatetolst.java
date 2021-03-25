import java.io.File;
public class TestlineStatetolst {
    public static void main(String[] args){
        InterRep IR = new Parser().generates();
        CodeGenerator c = new CodeGenerator(IR, new SymbolTable(), new File("x"));
        String header = "Line Addr Code          Label         Mne   Operand       Comments";
        System.out.println(header);
        for(int i = 0; i < IR.getSize(); i++){
            System.out.println(c.lineStatetolst(i, IR.getLS(i), new SymbolTable()));
        }
    }
}
