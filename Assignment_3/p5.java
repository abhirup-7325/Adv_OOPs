package Assignment_3;

import java.util.Scanner;

class Data {
    int value = 0;

    synchronized void increment(int amount) {
        value += amount;
    }

    synchronized void decrement(int amount) {
        value -= amount;
    }
}

class IncrementThread extends Thread {
    Data data;
    int amount;

    IncrementThread(Data data) {
        this.data = data;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a number to increment: ");
        amount = sc.nextInt();

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

public class p5 {
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

        System.out.println("Final value with synchronization: " + data.value);
    }
}
