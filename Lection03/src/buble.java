/**
 * Created by forger on 28.03.2018.
 */
public class buble {
    static class DivByZeroException extends Exception {
        Operation e;

        DivByZeroException(Operation e) {
            this.e = e;
        }

        Operation GetOperation() {
            return e;
        }


    }

    static abstract class Operation {
        //класс абстрактный, наследуемся от него
        double l;
        double r;

        Operation(double l, double r) {
            this.l = l;
            this.r = r;
        }

        abstract double calculate() throws DivByZeroException;
        //методы тоже могут быть абстрактными
    }

    static public class add extends Operation {
        add(double l, double r) {
            super(l, r);
        }

        double calculate() {
            return l + r;
        }
    }

    static class minus extends Operation {
        minus(double l, double r) {
            super(l, r);
        }

        double calculate() {
            return l - r;
        }
    }

    static class multiply extends Operation {
        multiply(double l, double r) {
            super(l, r);
        }

        double calculate() {
            return l * r;
        }
    }

    static class div extends Operation {
        div(double l, double r) {
            super(l, r);
        }

        double calculate()
                throws DivByZeroException {
            if (r == 0) {
                throw new DivByZeroException(this);
            }
            return(double)l / r;

        }
    }

    static class step extends Operation {
        step(double l, double r) {
            super(l, r);
        }

        double calculate() {
            for (double i = 1; i < r; i++) {
                l = l + l;
            }
            return l;
        }
    }

    public static void main(String[] args) {
        double x;
        x = 5.0;
        // x = (int) System.in.read();
        try {
            Operation operation = new minus(new add(new multiply(new div(5, x).calculate(), 4).calculate(), x).calculate(), new step(2, x).calculate());
            System.out.println(operation.calculate());
            //testing
        /*System.out.println(new div(5,x).calculate());
        System.out.println(new multiply(new div(5,x).calculate(),4).calculate());
        System.out.println(new add(new multiply(new div(5,x).calculate(),4).calculate(),x).calculate());
        System.out.println(new step(2,x).calculate());*/
        } catch (DivByZeroException ex) {
            System.out.println("все плохо, поделили на ноль");
        }
    }
}
