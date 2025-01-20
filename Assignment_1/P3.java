/*
Input n and consider an array of 1 to n natural numbers. Skip every second value and print the
resulting series. Then select every third value from the remaining numbers. Print the resulting series.
Repeat this process till the skip count becomes greater than the length of the list.
{1,2,3,4,5,6,7,8,9,10}→{1,3,5,7,9}→{1,7}
*/

import java.util.*;

class Vector {
    private int[] arr;
    private int size;
    private int capacity;

    public Vector() {
        arr = new int[1];
        size = 0;
        capacity = 1;
    }

    public void add(int value) {
        if (size == capacity) {
            int[] temp = new int[2 * capacity];
            for (int i = 0; i < capacity; i++) {
                temp[i] = arr[i];
            }
            capacity *= 2;
            arr = temp;
        }
        arr[size++] = value;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        return arr[index];
    }

    public void set(int index, int value) {
        if (index < 0 || index >= size) {
            return;
        }
        arr[index] = value;
    }

    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}

class Solution {
    private int getInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        scanner.close();

        return n;
    }

    private void printJumpedSeries(int n) {
        Vector arr1 = new Vector();
        Vector arr2 = new Vector();
        for (int i = 1; i <= n; i++) {
            arr1.add(i);
        }

        int stepCount = 1;

        while (stepCount <= arr1.size()) {
            arr2.clear();

            for (int i = 0; i < arr1.size(); i += stepCount) {
                arr2.add(arr1.get(i));
            }

            arr1.clear();
            for (int i = 0; i < arr2.size(); i++) {
                arr1.add(arr2.get(i));
            }

            stepCount++;

            for (int i = 0; i < arr1.size(); i++) {
                System.out.print(arr1.get(i) + " ");
            }
            System.out.println();
        }
    }

    public void run() {
        int n = getInput();
        printJumpedSeries(n);
    }
}

class P3 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.run();
    }
}