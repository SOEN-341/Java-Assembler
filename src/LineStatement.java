public class LineStatement {
    private String label;
    private Instruction instruction;
    private String Comments;

    public LineStatement(String label,Instruction instruction, String comments) {
        this.label = label;
        this.instruction= new Instruction(instruction.getMnemonic(), instruction.getOperand());
        Comments = comments;
    }
//
//    public String getMne() {
//        return mne;
//    }
//
//    public String getOperand() {
//        return operand;
//    }

    public String getComments() {
        return Comments;
    }

    public String getLabel() {
        return label;
    }
}
