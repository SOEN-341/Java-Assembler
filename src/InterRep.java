import java.util.ArrayList;

public class InterRep implements IInterRep {

    private ArrayList<LineStatement>IR;
    public InterRep(){
        this.IR = new ArrayList<LineStatement>();
    }
    public LineStatement getLS(int index){
        return IR.get(index);
    }
    public int getSize(){
        return this.IR.size();
    }
    public void add(LineStatement ls){
        this.IR.add(ls);
    }

    @Override
    public String toString() {
        return "InterRep{" +
                "IR=" + IR +
                '}';
    }
}

