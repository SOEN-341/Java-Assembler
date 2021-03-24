public class TestLineStatement {
    public static void main(String[] args) {
        LineStatement Line1 = new LineStatement("Done", "enter.u5", "28", "Nothing");
        LineStatement Line2 = new LineStatement("loop", "xor", "", "");
        LineStatement Line3 = new LineStatement("continue", "addv.i3", "-1", "");
        System.out.println("Test LineStatement");
        System.out.println("LineStatement{label=Done, Instruction=[Mnemonic=enter.u5, Operand=28], Comments=Nothing}"
        +"\tLineStatement{label=loop, Instruction=[Mnemonic=xor, Operand=], Comments=}"
        +"\tLineStatement{label=continue, Instruction=[Mnemonic=addv.i3, Operand=-1], Comments=}");


        System.out.print(Line1);
        System.out.print("\t"+Line2);
        System.out.print("\t"+Line3);
        System.out.println();

    }
}
