public class TestParserGenerates {
    public static void main(String[] args) {
        Parser Par = new Parser();
        InterRep IR= Par.generates();

        System.out.println("Test Parser for generates");
        System.out.println("Intermediate representation: First Line [LineStatement{label=, Instruction=[Mnemonic=halt, Operand=], Comments=}]"
                + ". Second Line [LineStatement{label=, Instruction=[Mnemonic=pop, Operand=], Comments=}]");

        System.out.print("Intermediate representation: First Line ["+IR.getLS(0)+"]. ");
        System.out.print("Second Line ["+IR.getLS(1)+"]");
        System.out.println();
    }
}
