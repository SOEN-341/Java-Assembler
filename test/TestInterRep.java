public class TestInterRep {
    public static void main(String[] args) {
        InterRep IR1 = new InterRep();

        LineStatement Line1 = new LineStatement("Done", "halt", "i3", "Nothing");
        LineStatement Line2 = new LineStatement("loop", "xor", "i3", "");

        IR1.add(Line1);
        IR1.add(Line2);

        System.out.println("Test Intermediate Representation");
        System.out.println("Intermediate representation: First Line [LineStatement{label=Done, Instruction=[Mnemonic=halt, Operand=i3], Comments=Nothing}]"
                + ". Second Line [LineStatement{label=loop, Instruction=[Mnemonic=xor, Operand=i3], Comments=}]"
                + ". The size of the Intermediate representation is 2.");

        System.out.print("Intermediate representation: First Line ["+IR1.getLS(0)+"]. Second Line ["+IR1.getLS(1)+"]. "
        + "The size of the Intermediate representation is "+IR1.getSize()+".");
        System.out.println();
    }
}
