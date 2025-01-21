/*
 * Write a program where class contains void show(int) to display the argument passed. Call the
function once with short as actual parameter and again double as actual parameter. Add another
function as void show(double) . Repeat the calls. Observe the outcomes in each case.
 */



class Runner {
    public void show(int value) {
        System.out.println("int: " + value);
    }

    public void show(double value) {
        System.out.println("double: " + value);
    }
}

class P5 {
    public static void main(String[] args) {
        short num1 = 1;
        double num2 = 2.0;

        Runner runner = new Runner();
        runner.show(num1);
        runner.show(num2);
    }
}