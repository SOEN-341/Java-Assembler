import java.util.ArrayList;

public class ErrorReporter  implements IErrorReporter{
    ArrayList<ErrorMsg> errorMsgs;
    public ErrorReporter(){
        this.errorMsgs = new ArrayList<ErrorMsg>();
    }
    public void record(ErrorMsg e){
        this.errorMsgs.add(e);
    }
    public void report(){
        for(ErrorMsg e: errorMsgs){
            System.out.println(e);
        }
    }
}
