package daa_Practical;

import java.util.Scanner;

public class Knapsack {
    public static int knapsack(int[] values, int[] weights, int capacity) {
        int n = values.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][capacity];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();
        
        int[] values = new int[n];
        int[] weights = new int[n];
        
        System.out.println("Enter the values of items:");
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
        }

        System.out.println("Enter the weights of items:");
        for (int i = 0; i < n; i++) {
            weights[i] = scanner.nextInt();
        }

        System.out.print("Enter the knapsack capacity: ");
        int capacity = scanner.nextInt();
        scanner.close();

        long startTime = System.nanoTime(); // Start timing

        int maxValue = knapsack(values, weights, capacity);
        System.out.println("Maximum value that can be obtained = " + maxValue);

        long endTime = System.nanoTime(); // Stop timing
        long executionTime = endTime - startTime;

        System.out.println("Execution Time: " + executionTime + " nanoseconds");
        System.out.println("Space Complexity: O(" + n * capacity + ")"); // O(n * capacity)
    }
}