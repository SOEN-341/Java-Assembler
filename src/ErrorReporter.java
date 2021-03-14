import java.util.ArrayList;

public class ErrorReporter {
    ArrayList<Error> Errors;
    public void addError(Error e){
        this.Errors.add(e);
    }
    public void printAllErrors(){
        for(Error e: Errors){
            System.out.println(e);
        }
    }
}
