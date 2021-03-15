public class LineStatement implements ILineStatement {
    private String label;
    private Instruction instruction;
    private String Comments;
    private String directive;

    LineStatement(String label,String mne,String operand, String comments) {
        this.label = label;
        this.instruction= new Instruction(mne, operand);
        this.Comments = comments;
    }

    LineStatement(String label,String directive, String comments) {
        this.label = label;
        this.directive = directive;
        this.Comments = comments;
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

    @Override
    public String toString() {
        return "LineStatement{" +
                "label=" + getLabel() +", "+
                getInstruction() +
                ", Comments=" + getComments() +
                "}";
    }
}
