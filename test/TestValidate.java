import java.util.ArrayList;
import java.util.Arrays;

public class TestValidate {
    public static void main(String[] args){
        IValidate v = new Validate();
        String[] a = {"-v"};
        ArrayList<String> s = new ArrayList<>();
        for(int i = 0; i < a.length; i++){
            s.add(a[i]);
        }
        System.out.println(v.validate(s));
    }
}
