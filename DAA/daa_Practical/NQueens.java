package daa_Practical;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NQueens {
    private int n;
    private int[][] board; // O(N^2) space complexity
    private List<int[][]> solutions; // O(k * N^2) space complexity, where k is the number of solutions

    public NQueens(int n) {
        this.n = n;
        this.board = new int[n][n]; // O(N^2) space
        this.solutions = new ArrayList<>(); // O(k * N^2) space
    }

    public List<int[][]> solve() {
        backtrack(0);
        return solutions;
    }

    private void backtrack(int col) {
        if (col == n) {
            int[][] solution = new int[n][n]; // O(N^2) space
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    solution[i][j] = board[i][j];
                }
            }
            solutions.add(solution);
            return;
        }

        for (int row = 0; row < n; row++) {
            if (isSafe(row, col)) {
                board[row][col] = 1;
                backtrack(col + 1);
                board[row][col] = 0;
            }
        }
    }

    private boolean isSafe(int row, int col) {
        // Check if there is a queen in the same row
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        // Check upper diagonal
        int i = row;
        int j = col;
        while (i >= 0 && j >= 0) {
            if (board[i][j] == 1) {
                return false;
            }
            i--;
            j--;
        }

        // Check lower diagonal
        i = row;
        j = col;
        while (i < n && j >= 0) {
            if (board[i][j] == 1) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of queens: ");
        int n = scanner.nextInt();
        scanner.close();

        long startTime = System.nanoTime(); // Start timing

        NQueens queens = new NQueens(n);
        List<int[][]> solutions = queens.solve();

        long endTime = System.nanoTime(); // Stop timing
        long executionTime = endTime - startTime;

        if (!solutions.isEmpty()) {
            System.out.println("Solution(s) found:");
            for (int[][] solution : solutions) {
                for (int[] row : solution) {
                    for (int cell : row) {
                        System.out.print(cell + " ");
                    }
                    System.out.println();
                }
                System.out.println();
            }
        } else {
            System.out.println("No solution exists.");
        }

        System.out.println("Execution Time: " + executionTime + " nanoseconds");
        System.out.println("Space Complexity: O(" + n * n + ")"); // O(N^2)
    }
}