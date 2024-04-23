package cc1;

import java.util.ArrayList;
import java.util.List;

public class Rendu {
    public static void main(String[] args) {
        // Exercice 1
        System.out.println("Exercice 1");
        //TODO
        AbsC myClass = new AbsC() {
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

// Exercice 1
interface  AbsC {
    int m1(int i);
    void m2(int i);
}



// Exercice 2
class A {
    static final A A = new A();
    private int ordinal;
    private A(){}
    private static final List<A> instances = new ArrayList<>();
    static void makeA(int n){
        for(int i = 0 ; i<n;i++){
            instances.add(new A());
        }
    }
    static A getInstance(int n){
       if(n>= 0 && n<instances.size()){
            return instances.get(n);
        }else {
            return null;
        }
    }
    
    @Override
     String toString() {
        return "Je suis A " + ordinal;
    }
}

// Exercice 3
interface BoolExp {
    boolean eval();
}

 class Value implements BoolExp {
    private boolean value;

     Value(boolean value) {
        this.value = value;
    }

    @Override
     boolean eval() {
        return value;
    }

    @Override
     String toString() {
        return Boolean.toString(value);
    }
}

 class Or implements BoolExp {
    private BoolExp left;
    private BoolExp right;

     Or(BoolExp left, BoolExp right) {
        this.left = left;
        this.right = right;
    }

    @Override
     boolean eval() {
        return left.eval() || right.eval();
    }

    @Override
     String toString() {
        return "(" + left + " or " + right + ")";
    }
}

 class And implements BoolExp {
    private BoolExp left;
    private BoolExp right;

     And(BoolExp left, BoolExp right) {
        this.left = left;
        this.right = right;
    }

    @Override
     boolean eval() {
        return left.eval() && right.eval();
    }

    @Override
     String toString() {
        return "(" + left + " and " + right + ")";
    }
}