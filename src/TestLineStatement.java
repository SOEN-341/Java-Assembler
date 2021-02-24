public class TestLineStatement {
    public static void main(String[] args) {
        LineStatement Line1 = new LineStatement("Done", "halt", "i3", "Nothing");
        LineStatement Line2 = new LineStatement("loop", "xor", "i3", "");
        LineStatement Line3 = new LineStatement("continue", "rem", "", "");
        System.out.println("Test LineStatement");
        System.out.println("LineStatement{label=Done, Instruction=[Mnemonic=halt, Operand=i3], Comments=Nothing}"
        +"\tLineStatement{label=loop, Instruction=[Mnemonic=xor, Operand=i3], Comments=}"
        +"\tLineStatement{label=continue, Instruction=[Mnemonic=rem, Operand=], Comments=}");


        System.out.print(Line1);
        System.out.print("\t"+Line2);
        System.out.print("\t"+Line3);
        System.out.println();

    }
}
