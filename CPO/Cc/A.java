import java.util.ArrayList;
import java.util.List;

class A {
    private static final A A = new A();
    private int ordinal;
    private A() {}

    private static List<A> instances = new ArrayList<>();

    public static void makeA(int n) {
        for (int i = 0; i < n; i++) {
            instances.add(new A());
        }
    }

    public static A getInstance(int i) {
        if (i >= 0 && i <= instances.size()) {
            if (i == 0) {
                return A;
            } else {
                return instances.get(i - 1);
            }
        }
        return null;
    }
}
