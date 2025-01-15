/*
Input n and consider an array of 1 to n natural numbers. Skip every second value and print the
resulting series. Then select every third value from the remaining numbers. Print the resulting series.
Repeat this process till the skip count becomes greater than the length of the list.
{1,2,3,4,5,6,7,8,9,10}→{1,3,5,7,9}→{1,7}
*/

import java.util.*;

class Solution {
    private int getInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the value of n: ");
        int n = scanner.nextInt();
        scanner.close();

        return n;
    }

    private void printJumpedSeries(int n) {
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();
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