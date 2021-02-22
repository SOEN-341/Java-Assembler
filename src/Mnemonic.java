public class Mnemonic {
    String name;
    String Opcode;

    public Mnemonic(String name, String opcode) {
        this.name = name;
        Opcode = opcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpcode() {
        return Opcode;
    }

    public void setOpcode(String opcode) {
        Opcode = opcode;
    }

    @Override
    public String toString() {
        return "Mnemonic{" +
                "name='" + name + '\'' +
                ", Opcode='" + Opcode + '\'' +
                '}';
    }
}
