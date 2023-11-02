package daa_Practical;

import java.util.Scanner;

public class Fibonacci {
    public static long recursiveFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        return recursiveFibonacci(n - 1) + recursiveFibonacci(n - 2);
    }

    public static long iterativeFibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        long a = 0, b = 1, fib = 0;
        for (int i = 2; i <= n; i++) {
            fib = a + b;
            a = b;
            b = fib;
        }
        return fib;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the value of n to calculate the nth Fibonacci number: ");
        int n = scanner.nextInt();
        scanner.close();

        long startTime, endTime, result;
        long recursiveTime, iterativeTime;

        // Calculate Fibonacci using the recursive method
        startTime = System.nanoTime();
        result = recursiveFibonacci(n);
        endTime = System.nanoTime();
        recursiveTime = endTime - startTime;

        System.out.println("Using Recursive Algorithm:");
        System.out.println("Fibonacci(" + n + ") = " + result);
        System.out.println("Time taken: " + recursiveTime + " nanoseconds");

        // Calculate Fibonacci using the iterative method
        startTime = System.nanoTime();
        result = iterativeFibonacci(n);
        endTime = System.nanoTime();
        iterativeTime = endTime - startTime;

        System.out.println("\nUsing Iterative Algorithm:");
        System.out.println("Fibonacci(" + n + ") = " + result);
        System.out.println("Time taken: " + iterativeTime + " nanoseconds");

        // Space complexity analysis
        long[] recursiveMemory = new long[n + 1];
        long[] iterativeMemory = new long[n + 1];
        recursiveMemory[0] = 1;
        recursiveMemory[1] = 1;

        for (int i = 2; i <= n; i++) {
            recursiveMemory[i] = recursiveMemory[i - 1] + recursiveMemory[i - 2];
        }

        iterativeMemory[0] = 1;
        iterativeMemory[1] = 1;

        for (int i = 2; i <= n; i++) {
        	iterativeMemory[i] = iterativeMemory[i - 1] + iterativeMemory[i - 2];
        }
        
        System.out.println("\nSpace Complexity Analysis:");
        System.out.println("Space used by Recursive Algorithm: " + (8 * (recursiveMemory.length)) + " bytes");
        System.out.println("Space used by Iterative Algorithm: " + (8 * (n+1)) + " bytes");

        // Display the Fibonacci series
        System.out.println("\nFibonacci Series:");
        for (int i = 0; i <= n; i++) {
            System.out.print(recursiveMemory[i] + " ");
        }
        
        System.out.println("\nFibonacci Series:");
        for (int i = 0; i <= n; i++) {
            System.out.print(iterativeMemory[i] + " ");
        }

    }
}