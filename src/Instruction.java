public class Instruction implements IInstruction{
    private String Mnemonic;
    private String Operand;

    Instruction (String Mnemonic,String Operand){
        this.Mnemonic=Mnemonic;
        this.Operand=Operand;
    }

    public String getMnemonic(){
        return Mnemonic;
    }

    public String getOperand(){
        return Operand;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "Mnemonic='" + Mnemonic + '\'' +
                ", Operand='" + Operand + '\'' +
                '}';
    }
}
