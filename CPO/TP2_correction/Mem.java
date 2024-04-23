
class Mem implements Operand {
    private int address;

    public Mem(int address) {
        this.address = address;
    }

    public int getAddress() {
        return address;
    }

     @Override
    public int readValue(Machine m) {

        return m.getMemoryElmt(m.getCurrent());
    }  
    void setValue(Machine	m,	int v){
        
    }
}