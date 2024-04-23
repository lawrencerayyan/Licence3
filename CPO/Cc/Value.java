
public class Value implements BoolExp {
    private boolean value;

    public Value(boolean value) {
        this.value = value;
    }

    @Override
    public boolean eval() {
        return value;
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }
}