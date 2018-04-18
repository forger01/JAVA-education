/**
 * Created by forger on 16.04.2018.
 */
public class HW {
    class Result {

        private double accumulator; //аккумулятор
        private String rest;//остаток от строки

        public Result(double v, String r) {
            this.accumulator = v;
            this.rest = r;
        }

        public double getAccumulator() {
            return accumulator;
        }

        public String getRest() {
            return rest;
        }

        public void setAccumulator(double accumulator) {
            this.accumulator = accumulator;
        }

        public void setRest(String rest) {
            this.rest = rest;
        }
    }

    public class MatchParserPlusMinus {

        public MatchParserPlusMinus() {
        }

        //точка входа на начало обработки
        public double Parse(String s) throws Exception {
            Result result = plusMinus(s);
            if (!result.getRest().isEmpty()) {
                System.err.println("Error: can't full parse");
                System.err.println("rest: " + result.rest);
            }
            return result.accumulator;//вернули то, что обработал plusMinus
        }
        //вызывается далее, читай сразу

        private Result plusMinus(String s) throws Exception {
            Result current = toNumAndRestOfString(s);//Result current = MulDiv(s);
            double acc = current.accumulator;//взяли число после toNumAndRestOfString, которое спарсили
            //в rest- то, что осталось от строки

            while (current.getRest().length() > 0) {
                if (!(current.getRest().charAt(0) == '+' || current.getRest().charAt(0) == '-')) break;
                //убедились что 0 символ плюс или минус

                char sign = current.getRest().charAt(0); //sign- знак
                String next = current.getRest().substring(1); //next- строка дальше после знака

                acc = current.accumulator;//первое число

                current = toNumAndRestOfString(next); //прочитали след число
                //current = MulDiv(next);
                if (sign == '+') {
                    //прошлое num + следующее
                    acc += current.accumulator;
                } else {
                    acc -= current.accumulator;
                }// в acc лежит результат операции
                current.accumulator = acc; //следующее равно предыдущее
                //на этом моменте в current лежит результат предыдущей операции и остаток от строки
            }
            return new Result(current.accumulator, current.getRest());
        }

        private Result MulDiv(String s) throws Exception {
            Result current = Bracket(s);

            double acc = current.getAccumulator();
            while (true) {
                if (current.getRest().length() == 0) {
                    return current;
                }
                char sign = current.getRest().charAt(0);
                if ((sign != '*' && sign != '/')) return current;

                String next = current.getRest().substring(1);
                Result right = Bracket(next);

                if (sign == '*') {
                    acc *= right.getAccumulator();
                } else {
                    acc /= right.getAccumulator();
                }

                current = new Result(acc, right.getRest());
            }
        }

        private Result Bracket(String s) throws Exception {
            char zeroChar = s.charAt(0);
            if (zeroChar == '(') {
                Result r = plusMinus(s.substring(1));
                if (!r.rest.isEmpty() && r.rest.charAt(0) == ')') {
                    r.rest = r.rest.substring(1);
                } else {
                    System.err.println("Error: not close bracket");
                }
                return r;
            }
            return toNumAndRestOfString(s);
        }

        private Result toNumAndRestOfString(String s) throws Exception {
            int i = 0;
            int dot_cnt = 0;
            boolean negative = false;
            // число также может начинаться с минуса
            if (s.charAt(0) == '-') {
                negative = true;
                s = s.substring(1);
            }
            // разрешаем только цифры и точку
            while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {
                // проверям, что в числе может быть только одна точка!
                if (s.charAt(i) == '.' && ++dot_cnt > 1) {
                    throw new Exception("not valid number '" + s.substring(0, i + 1) + "'");
                }
                i++;
            }
            if (i == 0) { // что-либо похожее на число мы не нашли
                throw new Exception("can't get valid number in '" + s + "'");
            }

            double dPart = Double.parseDouble(s.substring(0, i));
            if (negative) dPart = -dPart;
            String restPart = s.substring(i);
            //взяли число, отправили на return
            //остаток строки отправили на return
            //все через result

            return new Result(dPart, restPart);
        }
    }


    void run() {
        MatchParserPlusMinus pm = new MatchParserPlusMinus();
        String f = "10-8+2+6";
        //не забыть удалить все пробелы из строки
        try {
            System.out.println("plusMinus: " + pm.Parse(f));
        } catch (Exception e) {
            System.err.println("Error while parsing '" + f + "' with message: " + e.getMessage());
        }
    }
        /*Const x = new Const(5);
        Expression test1 = parse("5 ** (x)");
        System.out.println(test1.evaluate(5)); // Output: "Некорректная запись"

        Expression test2 = parse("-3 * x + 4");
        System.out.println(test1.evaluate(5)); // Output: -11

        Expression test3 = parse("5 *      ( x - 2 ) +      3");
        System.out.println(test1.evaluate(5)); // Output: 18
    }*/


    public static void main(String[] args) {
        new HW().run();
    }
}
