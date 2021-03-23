public class ErrorMsg implements IErrorMsg{
    private String message;
    private Position p;
    public ErrorMsg(String m, Position p){
        this.message = m;
        this.p = p;
    }

    public String getMessage() {
        return message;
    }

    public Position getP() {
        return p;
    }

    @Override
    public String toString() {
        return "Error{" +
                "message='" + message + '\'' +
                ", p=" + p +
                '}';
    }
}
