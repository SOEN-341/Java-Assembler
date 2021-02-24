public class TestParserMnemonics {
    public static void main(String[] args) {
        Parser Par1= new Parser();

        System.out.println("Test Parser");


        System.out.println("The first Mnemonic is \'halt\'. The second mnemonic is \'pop\'. The third mnemonic is \'dup\'.");

        String one=Par1.getMnemonic().get(0);
       String two=Par1.getMnemonic().get(1);
       String three=Par1.getMnemonic().get(2);
        System.out.print("The first Mnemonic is"+"\""+ two);
       System.out.print(three);

//        System.out.print("The first Mnemonic is "+ Par1.getMnemonic().get(0)+". ");
//        System.out.println("hello");
//        System.out.print("The second Mnemonic is \'"+ Par1.getMnemonic().get(1)+"\'. ");
//        System.out.print("The third Mnemonic is \'"+ Par1.getMnemonic().get(2)+"\'.");
//        System.out.println();



    }
}
