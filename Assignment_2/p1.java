package Assignment_2;

/*
 * An integer array representing height n is given as input. There are n vertical lines drawn
 * from the array such that the tw endpoints of the ith line are (i, 0) and (i, height[i]). 
 * Any 2 lines along with the x-axis form a container that can hold water. Fine the largest container that can hold
 * maximum water. Collection classes are NOT allowed.
 */

import java.util.*;


class Solution {
    int getMaxWater(int[] arr) {
        int maxm = 0;

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                maxm = Math.max(maxm, (i - j) * Math.min(arr[i], arr[j]));
            }
        }

        return maxm;
    }


    double getMaxWater2(double[] arr) {
        int i = 0, j = arr.length - 1;
        double maxm = 0;

        while (i < j) {
            maxm = Math.max(maxm, (j - i) * Math.min(arr[i], arr[j]));

            if (arr[i] <= arr[j]) {
                i++;
            } else {
                j--;
            }
        }

        return maxm;
    }

    int getMaxWater2(int[] arr) {
        int i = 0, j = arr.length - 1;
        int maxm = 0;

        while (i < j) {
            maxm = Math.max(maxm, (j - i) * Math.min(arr[i], arr[j]));

            if (arr[i] <= arr[j]) {
                i++;
            } else {
                j--;
            }
        }

        return maxm;
    }
} 

class p1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        double[] arr = new double[n];

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextDouble();
        }

        int angle = scanner.nextInt();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.sin(Math.toRadians(angle)) * arr[i];
        }

        Solution solution = new Solution();
        System.out.println(solution.getMaxWater2(arr));

        scanner.close();
    }
}

