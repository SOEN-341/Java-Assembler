import java.util.ArrayList;

public interface IParser {

    ArrayList<Token> getLabel();
    ArrayList<Token>  getMnemonic();
    ArrayList<Token>  getComments();

}
