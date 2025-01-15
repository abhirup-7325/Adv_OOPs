/*
Write a program that accepts a String and assigns it to another. Check the outcome of
comparison with == and equals() method. Take two Strings and put same input for them. Repeat the
equality checking. Observe the outcome.
*/


import java.util.*;

class P4 { 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the first String: ");
        String str1 = scanner.nextLine();

        String str2 = str1;

        System.out.print("Enter the second String: ");
        String str3 = scanner.nextLine();
        System.out.print("Repeat the second String: ");
        String str4 = scanner.nextLine();

        System.out.println("str1 == str2: " + (str1 == str2));
        System.out.println("str1.equals(str2): " + str1.equals(str2));
        System.out.println("str3 == str4: " + (str3 == str4));
        System.out.println("str3.equals(str4): " + str3.equals(str4));

        scanner.close();
    }
}