public class TestInstruction {
    public static void main(String[] args) {
        Instruction Instruct1 = new Instruction("halt", "i3");
        Instruction Instruct2 = new Instruction("stv", "u3");
        Instruction Instruct3 = new Instruction("dup","");

        System.out.println("Test Instruction");
        System.out.println("Instruct1[halt,i3]\tInstruct2[stv,u3]\tInstruct3[dup,]");

        System.out.print("Instruct1["+ Instruct1.getMnemonic()+","+ Instruct1.getOperand()+"]");
        System.out.print("\tInstruct2["+ Instruct2.getMnemonic()+","+ Instruct2.getOperand()+"]");
        System.out.print("\tInstruct3["+ Instruct3.getMnemonic()+","+ Instruct3.getOperand()+"]");
    }
}
