package tp2exo2;

public class IfZero implements Instruction{
    private int operand1;
    private int operand2;

    public IfZero(int operand1, int operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public String toString() {
        return "IfZero(" + operand1 + ", " + operand2 + ")";
    }
    @Override
    public boolean execute(Machine m) {
      /*  int value = m.getMemory(operand1);
        if (value == 0) {
           m.setInstructionPointer(operand2);

        }
        return true;
    }*/
        if(operand1<0 || operand1>m.program.length || operand2<0 || operand2>m.program.length ) return false;
        else {
            if(m.memory[operand1]== 0){
                m.courante = operand2;
            }else{m.courante++ ;}
        }
        return true;
    }
}
