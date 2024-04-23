
public class Set extends Instruction {


	private Operand index;
	private Operand value;

	Set(Operand n, Operand v) {
		index = n;
		value = v;
	}

	@Override
	public String toString() {
		return "Set (" + index + ", " + value + ")";
	}

	@Override
	boolean execute(Machine m) {
		System.out.println(this);
		if (m.setMemory(index, value)){
			return m.nextInstruction();
		}
		else
			return false;
	}

}
