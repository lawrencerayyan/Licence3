
import java.util.ArrayList;
import java.util.List;

public class Machine {

	private List<Instruction> instructions;
	private int[] memory;
	private int current;

	public Machine(int memorySize) {
		memory = new int[memorySize];
		instructions = new ArrayList<>();
		reset();
	}

	public void setProgram(Instruction... args) {
		for (int i = 0; i < args.length; i++)
			instructions.add(args[i]);
	}

	public void printProgram() {
		System.out.println(current + ". " + instructions.get(current));
		while (nextInstruction()) {
			System.out.println(current + ". " + instructions.get(current));
		}
		reset();
	}

	public void printMemory() {
		for (Integer i : memory) {
			System.out.println(i);
		}
	}

	public boolean step() {
		return instructions.get(current).execute(this);
	}

	boolean nextInstruction() {
		if (current < instructions.size() - 1) {
			current++;
			return true;
		} else
			return false;
	}

	boolean setCurrent(int index) {
		if (index < instructions.size()) {
			current = index;
			return true;
		}
		return false;
	}
	
	 int getCurrent() {
		return current;
	}

	 int getMemorySize() {
		return memory.length;
	}

	 int getMemoryElmt(int index) {
		return memory[index];
	}

	boolean setMemory(int index, int value) {
		
			memory[index] = value;
			return true;
	}

	void reset() {
		current = 0;
	}
}
