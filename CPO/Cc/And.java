
public class And implements BoolExp {
    private BoolExp left;
    private BoolExp right;

    public And(BoolExp left, BoolExp right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean eval() {
        return left.eval() && right.eval();
    }

    @Override
    public String toString() {
        return "(" + left + " and " + right + ")";
    }
}