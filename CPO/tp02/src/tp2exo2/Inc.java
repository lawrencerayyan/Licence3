package tp2exo2;

public class Inc implements Instruction{
    private int operand1;
    private int operand2;

    public Inc(int operand1, int operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public String toString() {
        return "Inc(" + operand1 + ", " + operand2 + ")";
    }
    @Override
    public boolean execute(Machine m) {
        if(operand1<0 || operand1>m.program.length || operand2<0 || operand2>m.program.length ) return false;
        else{
            int value = m.memory[operand1] + m.memory[operand2];
             m.setMemory(operand1, value);
             m.courante++;
        }

                /*m.getMemory(operand1) + m.getMemory(operand2);*/
        return true;
    }
}
