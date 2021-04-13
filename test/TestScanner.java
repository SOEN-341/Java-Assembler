import java.util.ArrayList;

public class TestScanner {
    public static void main(String[] args) {
        ErrorReporter reporter = new ErrorReporter();
        Scanner s1 = new Scanner("TestImmediate.asm",reporter, new SymbolTable());
        System.out.println("Test Scanner");
        System.out.println("[[halt(1,1)=Mnemonic]");
        System.out.println(s1.scanToken());
    }
}
