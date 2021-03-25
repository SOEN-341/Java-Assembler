import java.util.ArrayList;

public class TestScanner {
    public static void main(String[] args) {
        Scanner s1 = new Scanner("TestImmediate.asm");
        System.out.println("Test Scanner");
        System.out.println("[[halt(1,1)=Mnemonic]");
        System.out.println(s1.scanToken());
    }
}
