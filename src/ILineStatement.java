public interface ILineStatement {
    Instruction getInstruction();
    String getComments();
    String getLabel();
}
