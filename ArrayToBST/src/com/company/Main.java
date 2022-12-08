package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        while (true) {
            int input = sc.nextInt();
            if (input == 0) {
                break;
            }
            list.add(input);
        }
        int[] arr = list.toIntArray();
        int n = arr.length;
        sortArray(arr, n);
        BinaryTree.root = tree.sortedArrayToBST(arr, 0, n - 1);
        tree.preOrder(BinaryTree.root);
    }

    private static void sortArray(int[] array, int n) {
        for (int i = 1; i < n; i++) {
            int j = i;
            int a = array[i];
            while ((j > 0) && (array[j - 1] > a)) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = a;
        }
    }

    static class Node {

        int data;
        Node left, right;

        Node(int d) {
            data = d;
            left = right = null;
        }
    }

    static class BinaryTree {

        static Node root;

        Node sortedArrayToBST(int[] arr, int start, int end) {

            if (start > end) {
                return null;
            }

            int mid = (start + end) / 2;
            Node node = new Node(arr[mid]);

            node.left = sortedArrayToBST(arr, start, mid - 1);

            node.right = sortedArrayToBST(arr, mid + 1, end);

            return node;
        }

        void preOrder(Node node) {
            if (node == null) {
                return;
            }
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
}
