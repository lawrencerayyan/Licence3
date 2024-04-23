

class Val implements Operand {
      private int value;

    public Val(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    
    }
    @Override
    public int readValue(Machine m) {
        
        return m.getMemoryElmt(m.getCurrent());
    }
}