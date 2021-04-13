import java.util.ArrayList;
import java.util.Arrays;

public class TestValidate {
    public static void main(String[] args){
        IValidate v = new Validate();
        String[] a = {"-v", "file.as"};
        System.out.println(v.validate(a));
    }
}
