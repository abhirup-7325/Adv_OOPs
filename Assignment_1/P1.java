/*
 * Write a program to accept two short integers from user and display the sum. Check what happens
 * when the sum exceeds the maximum range of short.
 */


import java.util.*;

class P1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the first number: ");
        short num1 = scanner.nextShort();

        System.out.print("Enter the second number: ");
        short num2 = scanner.nextShort();

        short summ = (short) (num1 + num2);
        System.out.println("Sum of " + num1 + " and " + num2 + " is " + summ);

        scanner.close();
    }
}