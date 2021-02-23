import java.io.File;
public interface ICodeGenerator {
    public static void generateListing(InterRep IR, SymbolTable Table, File f){};
    public void generateExecutable();
}
