public class LineStatement {
    private String label;
    private String mne;
    private String operand;
    private String Comments;

    public LineStatement(String label, String mne, String operand, String comments) {
        this.label = label;
        this.mne = mne;
        this.operand = operand;
        Comments = comments;
    }

    public String getMne() {
        return mne;
    }

    public String getOperand() {
        return operand;
    }

    public String getComments() {
        return Comments;
    }

    public String getLabel() {
        return label;
    }
}
