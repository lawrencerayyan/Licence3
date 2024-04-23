package tp2exo2;

public class Machine {
    private int memorySize;
    public int [] memory ;
    public int courante ;
    public Instruction[] program;

    public Machine(int memorySize) {
        this.memorySize = memorySize;
        memory = new int[memorySize];
        courante = 0;
    }

    public void setMemory(int index, int value) {
        if (index >= 0 && index < memorySize) {
            memory[index] = value;
        }
    }

    public int getMemory(int index) {
        if (index >= 0 && index < memorySize) {
            return memory[index];
        }
        return 0;
    }

    public void setInstructionPointer(int index) {
        if (index >= 0 && index < program.length) {
            courante = index;
        }
    }

    public boolean step() {

        if (courante < 0 || courante >= program.length) return false ;
        else return program[courante].execute(this);
    }

    public void setProgram(Instruction[] program) {
        this.program = program;
    }

    public void printProgram() {
        for (int i = 0; i < program.length; i++) {
            System.out.println(i + ": " + program[i]);
        }
    }
    public void printMemory(){
        for(int i = 0 ; i< memorySize; i++) {
            System.out.println("Memory[" + i + "] = " + memory[i]);
        }
    }

    public static void main(String[] args) {
        Machine m = new Machine(4);
        Instruction[] program = {
                new Set(0, 3),
                new Set(1, 1),
                new Set(2, -1),
                new IfZero(0, 7),
                new Inc(0, 2),
                new Inc(1, 1),
                new IfZero(3, 3)
               
        };
        m.setProgram(program);
        m.printProgram();
        do {
            m.printMemory();
            System.out.println();
        }	while (m.step());

    }
}
