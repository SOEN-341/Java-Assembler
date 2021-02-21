public class TestSymbolTable {
    public static void main(String[] args){
        SymbolTable Table = new SymbolTable();
        System.out.println("Test SymbolTable");
        System.out.println(0x1A);
        System.out.println(Table.getOpcode("teq"));

    }
}
