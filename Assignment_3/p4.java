package Assignment_3;

import java.util.Scanner;

public class p4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements in the array: ");
        int n = scanner.nextInt();

        long[] arr = new long[n];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        long[] ans = new long[n];
        long ref = 1;
        for (int i = 0; i < n; i++) {
            ans[i] = ref;
            ref *= arr[i];
        }

        ref = 1;
        for (int i = n - 1; i >= 0; i--) {
            ans[i] *= ref;
            ref *= arr[i];
        }

        System.out.println("The product array is:");
        for (long value : ans) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
}
