public class TestErrorMsg {
    public static void main(String[] args){
        IErrorMsg err = new ErrorMsg("Test Error", new Position(-1,-1));
        System.out.println("Test ErrorMsg");
        System.out.println("Error{message='Test Error', p=(-1,-1)}");
        System.out.println(err.toString());
    }
}
