public interface ICodeGenerator {
    public void generateListing(IntermediateRepresentation IR, SymbolTable Table);
    public void generateExecutable();
}
