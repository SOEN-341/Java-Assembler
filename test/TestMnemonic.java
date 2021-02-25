public class TestMnemonic {
    public static void main(String[] args) {
        Mnemonic m1 = new Mnemonic("halt",0x00);
        Mnemonic m2 = new Mnemonic("pop",0x01);
        System.out.println("Test Mnemonic");
        System.out.println("Mnemonic{name='halt', Opcode='0'}Mnemonic{name='pop', Opcode='1'}");
        System.out.print(m1);
        System.out.print(m2);
    }
}
