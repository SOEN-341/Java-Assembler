import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Scanner {
    private ArrayList<String> Labels;
    private ArrayList<Integer> Offsets;
    private ArrayList<String> Mnemonic;
    private ArrayList<String> Comments;
    private ArrayList<String> Operand;
    private int count;

    public Scanner(File f) throws IOException {

        this.Labels = new ArrayList<String>();
        this.Mnemonic = new ArrayList<String>();
        this.Offsets = new ArrayList<Integer>();
        this.Comments = new ArrayList<String>();
        this.Operand = new ArrayList<String>();
        this.count = 0;

        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String str = null;
        String s= br.readLine();

        while (s != null) {
            for (int j= 0; j<=s.length();j++) {
                // finding a comment
                if (s.charAt(j) == ';') {
                    //      Comments.add(s.replaceAll(";", ""));

                }
                // finding a label
                else if (Character.isLetter(s.charAt(0))) {
                    //     Labels.add(s);
                }
                else if (Character.isLetter(s.charAt(j+1))) {
                    this.Mnemonic.add(count,s);
                }

            }

            count++;
            s=br.readLine();

        }
        System.out.print(this.Mnemonic);
    }

    public ArrayList<String> getLabels() {
        return Labels;
    }

    public ArrayList<Integer> getOffsets() {
        return Offsets;
    }

    public ArrayList<String> getMnemonic() {
        return Mnemonic;
    }

    public ArrayList<String> getComments() {
        return Comments;
    }

    public ArrayList<String> getOperand() {
        return Operand;
    }

    public int getCount() {
        return count;
    }





    public static void main(String[] args) throws IOException {
        File f = new File("TestInherentMnemonics.asm");



    }
}
