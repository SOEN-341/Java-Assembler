import java.util.ArrayList;

public class IntermediateRepresentation implements IIntermediateRepresentation{

    private ArrayList<LineStatement>IR= new ArrayList<LineStatement>(10);

    public ArrayList<LineStatement> generates(){
        // write code
        return IR;
    }
}

