import java.io.File;
public class TestlineStatetolst {
    public static void main(String[] args){
        ErrorReporter reporter = new ErrorReporter();

        InterRep IR = new Parser(new Scanner("TestImmediate.asm",reporter, new SymbolTable())).generates();
        CodeGenerator c = new CodeGenerator(IR, new SymbolTable(), new File("x"));
        //String header = "Line Addr Code          Label         Mne   Operand       Comments";
        System.out.println("Test lineStatetolst");
        System.out.println("7    0006 84                          enter.u5   4             ;OK, number <u5> [0..31].");
        System.out.println(c.ImmediateString(6,6, IR.getLS(6), new SymbolTable()));

    }
}
