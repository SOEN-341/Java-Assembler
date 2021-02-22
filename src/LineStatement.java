public class LineStatement implements ILineStatement {
    private String label;
    private Instruction instruction;
    private String Comments;

    public LineStatement(String label,String mne,String operand, String comments) {
        this.label = label;
        this.instruction= new Instruction(mne, operand);
        Comments = comments;
    }

    public Instruction getInstruction() {
        return instruction;
    }

    public String getComments() {
        return Comments;
    }

    public String getLabel() {
        return label;
    }
}
