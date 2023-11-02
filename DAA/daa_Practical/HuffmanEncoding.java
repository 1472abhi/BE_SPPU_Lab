package daa_Practical;

import java.util.ArrayList;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

class Node {
    Node left;
    Node right;
    String value;
    int frequency;

    public Node(String value, int frequency) {
        this.value = value;
        this.frequency = frequency;
    }

    public List<Node> children() {
        List<Node> children = new ArrayList<>();
        children.add(left);
        children.add(right);
        return children;
    }
}

public class HuffmanEncoding {
    private List<Node> q;
    private String inputString;
    private Map<String, String> encoding;

    public HuffmanEncoding(String string) {
        q = new ArrayList<>();
        inputString = string;
        encoding = new HashMap<>();
    }

    private void charFrequency() {
        Map<String, Integer> count = new HashMap<>();
        for (int i = 0; i < inputString.length(); i++) {
            String charString = String.valueOf(inputString.charAt(i));
            count.put(charString, count.getOrDefault(charString, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : count.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            q.add(node);
        }

        Collections.sort(q, (n1, n2) -> n1.frequency - n2.frequency);
    }

    private void buildTree() {
        while (q.size() > 1) {
            Node n1 = q.remove(0);
            Node n2 = q.remove(0);
            Node node = new Node(null, n1.frequency + n2.frequency);
            node.left = n1;
            node.right = n2;
            q.add(node);
            Collections.sort(q, (n3, n4) -> n3.frequency - n4.frequency);
        }
    }

    private void helper(Node node, String binaryStr) {
        if (node.value != null) {
            encoding.put(node.value, binaryStr);
            return;
        }

        List<Node> children = node.children();
        helper(children.get(0), binaryStr + "0");
        helper(children.get(1), binaryStr + "1");
    }

    private void huffmanEncoding() {
        Node root = q.get(0);
        helper(root, "");
    }

    private void printEncoding() {
        System.out.println(" Char | Huffman code ");
        for (Map.Entry<String, String> entry : encoding.entrySet()) {
            System.out.printf(" %-4s |%12s%n", entry.getKey(), entry.getValue());
        }
    }

    public void encode() {
        charFrequency();
        buildTree();
        huffmanEncoding();
        printEncoding();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the string to be encoded: ");
        String inputString = scanner.nextLine();
        scanner.close();

        long startTime = System.nanoTime(); // Start timing

        HuffmanEncoding encoder = new HuffmanEncoding(inputString);
        encoder.encode();

        long endTime = System.nanoTime(); // Stop timing
        long executionTime = endTime - startTime;

        System.out.println("Execution Time: " + executionTime + " nanoseconds");
        System.out.println("Space Complexity: O(n)"); // O(n) where n is the number of unique characters
    }
}