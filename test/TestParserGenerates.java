public class TestParserGenerates {
    public static void main(String[] args) {
        Parser Par = new Parser(new Scanner("TestImmediate.asm"));
        InterRep IR= Par.generates();

        System.out.println("Test Parser for generates");
        System.out.println("Intermediate representation: First Line [LineStatement{label=, Instruction=[Mnemonic=, Operand=], "
                +"Comments=TestImmediate.asm - Test immediate instructions.}]"
                + ". Second Line [LineStatement{label=, Instruction=[Mnemonic=enter.u5, Operand=0], Comments=OK, number <u5> [0..31].}]"
                + ". Third Line [LineStatement{label=, Instruction=[Mnemonic=enter.u5, Operand=1], Comments=OK, number <u5> [0..31].}]");

        System.out.print("Intermediate representation: First Line ["+IR.getLS(0)+"]. ");
        System.out.print("Second Line ["+IR.getLS(1)+"]. ");
        System.out.print("Third Line ["+IR.getLS(2)+"]");
        System.out.println();
    }
}
