package tp2exo2;

public class Set implements Instruction{
    private int operand1;
    private int operand2;

    public Set(int operand1, int operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public String toString() {
        return "Set(" + operand1 + ", " + operand2 + ")";
    }
    @Override
    public boolean execute(Machine m) {
        if( operand1>m.program.length ||  operand2>m.program.length ) return false;
        else{
            m.memory[operand1] = operand2;
            m.courante++;
        }
        //m.setMemory(operand1, operand2);
        return true;
    }
}
