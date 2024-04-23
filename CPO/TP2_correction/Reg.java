
class Reg implements Operand {
    private String name;

    public Reg(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int readValue(Machine m) {

        return m.getMemoryElmt(m.getCurrent());
    }

    void setValue(Machine	m,	int v){
        
    }
}