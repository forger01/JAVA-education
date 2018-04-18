/**
 * Created by forger on 06.04.2018.
 */


public class HW {

    interface Expression {
        boolean evaluate(int value);
    }
    class Result
    {

        public double acc;
        public String rest;

        public Result(double v, String r)
        {
            this.acc = v;
            this.rest = r;
        }
    }

    // Здесь вы дальше описываете свой примитивы: Variable, Multiply, и так далее

    class Const implements Expression {

        private final int value;

        public Const(int value) {
            this.value = value;
        }

        @Override
        //собственно именно здесь происходит вся магия вычислений
        public boolean evaluate(int value) {
            return false;
        }
    }

    /**
     * Функция, принимающая на вход арифметическое выражение и возвращающая объект типа
     * {@link Expression}.
     * @param input входное арифметическое выражение
     * @return распаршенное выражение в виде {@link Expression}
     */
    //здесь будет лежать остаток от строки
    Expression parse(String input) {
        Expression result = null;
        // парсим строку input в result
        return result;
    }

    void run() {
        Const x = new Const(5);
        Expression test1 = parse("5 ** (x)");
        System.out.println(test1.evaluate(5)); // Output: "Некорректная запись"

        Expression test2 = parse("-3 * x + 4");
        System.out.println(test1.evaluate(5)); // Output: -11

        Expression test3 = parse("5 *      ( x - 2 ) +      3");
        System.out.println(test1.evaluate(5)); // Output: 18
    }


    public static void main(String[] args) {
        new HW().run();
    }

}