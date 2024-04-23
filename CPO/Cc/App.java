public class App {
    public static void main(String[] args) {
        // Exercice 1
        System.out.println("Exercice 1");
        //TODO
        AbsC myClasmds = new AbsC() {
            @Override
            public int m1(int i) {
                return i + i;
            }

            @Override
            public void m2(int i) {
                System.out.println(i + i);
            }
        };
        // Exercice 2
        System.out.println("Exercice 2");
        A a1 = A.A;
        int instances = 5;
        A.makeA(instances);
        for (int i = 0; i < instances; i++) {
            System.out.println(A.getInstance(i));
        }

        // Exercice 3
        System.out.println("Exercice 3");
        BoolExp t = new Value(true);
        System.out.println("t" + " = " + t.eval());
        BoolExp f = new Value(false);
        System.out.println("f" + " = " + f.eval());
        BoolExp e1 = new Or(t, f);
        System.out.println("e1" + " = " + e1.eval());
        BoolExp e2 = new And(t, e1);
        System.out.println("e2 = " + e2 + " = " + e2.eval());
        BoolExp e3 = new Or(e1, e2);
        System.out.println("e3" + " = " + e3.eval());
        BoolExp e4 = new And(e3, e2);
        System.out.println("e4" + " = " + e4.eval());
        BoolExp e5 = new And(e3, e4);
        System.out.println("e5" + " = " + e5.eval());
    }
}
