
public class TestErrorReporter {
    public static void main(String[] args){
        ErrorReporter r = new ErrorReporter();
        ErrorMsg err1 = new ErrorMsg("Testing 1", new Position(-1,-1));
        System.out.println("Test ErrorReporter");
        System.out.println("Error{message='Testing 1', p=(-1,-1)}");
        r.record(err1);
        r.report();
    }

}
