import java.util.ArrayList;

public class TestScanner {
    public static void main(String[] args) {
        ArrayList<Token> token = Scanner.scanToken();
        System.out.println("Test Scanner");
        System.out.println("[[halt(1,1)=Mnemonic], [pop(2,1)=Mnemonic], [dup(3,1)=Mnemonic], [exit(4,1)=Mnemonic], [ret(5,1)=Mnemonic], [not(6,1)=Mnemonic], [and(7,1)=Mnemonic], [or(8,1)=Mnemonic], [xor(9,1)=Mnemonic], [neg(10,1)=Mnemonic], [inc(11,1)=Mnemonic], [dec(12,1)=Mnemonic], [add(13,1)=Mnemonic], [sub(14,1)=Mnemonic], [mul(15,1)=Mnemonic], [div(16,1)=Mnemonic], [rem(17,1)=Mnemonic], [shl(18,1)=Mnemonic], [shr(19,1)=Mnemonic], [teq(20,1)=Mnemonic], [tne(21,1)=Mnemonic], [tlt(22,1)=Mnemonic], [tgt(23,1)=Mnemonic], [tle(24,1)=Mnemonic], [tge(25,1)=Mnemonic], [halt(26,1)=Mnemonic]]");
        System.out.println(token);
    }
}
