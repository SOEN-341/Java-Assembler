import java.io.File;
public interface ICodeGenerator {
    public void generateListing();
    public void generateExecutable();
    public String lineStatetolst(int lineNum, LineStatement lS, SymbolTable Table);
}
