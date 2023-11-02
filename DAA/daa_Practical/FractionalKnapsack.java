package daa_Practical;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Item {
    int value;
    int weight;
    double valuePerWeight;

    public Item(int value, int weight) {
        this.value = value;
        this.weight = weight;
        this.valuePerWeight = (double) value / weight;
    }
}

public class FractionalKnapsack {
    public static double getMaxValue(Item[] items, int capacity) {
        Arrays.sort(items, Comparator.comparingDouble((Item item) -> -item.valuePerWeight));

        double maxValue = 0.0;
        int currentWeight = 0;

        for (Item item : items) {
            if (currentWeight + item.weight <= capacity) {
                maxValue += item.value;
                currentWeight += item.weight;
            } else {
                double remainingCapacity = capacity - currentWeight;
                maxValue += item.valuePerWeight * remainingCapacity;
                break;
            }
        }

        return maxValue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int n = scanner.nextInt();
        
        Item[] items = new Item[n];

        System.out.println("Enter the values and weights of items:");
        for (int i = 0; i < n; i++) {
            int value = scanner.nextInt();
            int weight = scanner.nextInt();
            items[i] = new Item(value, weight);
        }

        System.out.print("Enter the knapsack capacity: ");
        int capacity = scanner.nextInt();
        scanner.close();

        long startTime = System.nanoTime(); // Start timing

        double maxValue = getMaxValue(items, capacity);
        System.out.println("Maximum value that can be obtained = " + maxValue);

        long endTime = System.nanoTime(); // Stop timing
        long executionTime = endTime - startTime;

        System.out.println("Execution Time: " + executionTime + " nanoseconds");
        System.out.println("Space Complexity: O(" + n + ")"); // O(n)
    }
}