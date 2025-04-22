package Assignment_3;


class Data {
    int value = 0;

    void increment(int amount) {
        value += amount;
    }

    void decrement(int amount) {
        value -= amount;
    }
}

class IncrementThread extends Thread {
    Data data;
    int amount = 1;

    IncrementThread(Data data) {
        this.data = data;
    }

    public void run() {
        data.increment(amount);
    }
}

class DecrementThread extends Thread {
    Data data;
    int fixedAmount = 1;

    DecrementThread(Data data) {
        this.data = data;
    }

    public void run() {
        data.decrement(fixedAmount);
    }
}

public class p5_2 {
    public static void main(String[] args) {
        Data data = new Data();

        Thread inc1 = new IncrementThread(data);
        Thread inc2 = new IncrementThread(data);
        Thread dec1 = new DecrementThread(data);
        Thread dec2 = new DecrementThread(data);

        inc1.start();
        inc2.start();
        dec1.start();
        dec2.start();

        try {
            inc1.join();
            inc2.join();
            dec1.join();
            dec2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final value without synchronization: " + data.value);
    }
}
