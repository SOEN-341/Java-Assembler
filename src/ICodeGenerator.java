import java.io.File;
public interface ICodeGenerator {
    public void generateListing();
    public void generateExecutable();
    public String ImmediateString(int lineNum, int addr, LineStatement lS, SymbolTable Table);
    public int generateImediateCode(LineStatement ls);
}
