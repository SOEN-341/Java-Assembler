public class Mnemonic implements IMnemonic{
    String name;
    int Opcode;

    Mnemonic(String name, int opcode) {
        this.name = name;
        Opcode = opcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOpcode() {
        return Opcode;
    }

    public void setOpcode(int opcode) {
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
