import java.io.File;
public interface ICodeGenerator {
    public void generateListing();
    public void generateExecutable();
    public String lineStatetolst(int lineNum, int addr, LineStatement lS, SymbolTable Table);
}
