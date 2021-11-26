package com.company;

import java.util.Scanner;

class Node {

    int data;
    Node left, right;

    Node(int d) {
        data = d;
        left = right = null;
    }
}

class BinaryTree {

    static Node root;

    public BinaryTree(){root = null;}

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

    void levelOrder() {
        int h = height(root);
        int i;
        for (i=1; i<=h; i++)
        {
            printCurrentLevel(root, i);
        }
    }

    int height(Node root) {
        if (root == null)
            return 0;
        else {
            int lheight = height(root.left);
            int rheight = height(root.right);
            if (lheight > rheight)
                return (lheight + 1);
            else
                return (rheight + 1);
        }
    }

    void printCurrentLevel(Node root, int level) {
        if (root == null) {
            System.out.print("null ");
            return;
        }
        if (level == 1)
            System.out.print(root.data + " ");
        else if (level > 1)
        {
            printCurrentLevel(root.left, level-1);
            printCurrentLevel(root.right, level-1);
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Scanner scan = new Scanner(System.in);
        String data = scan.nextLine();
        String[] tempDataArray = data.split(" ");
        int[] arr = new int[tempDataArray.length];
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            arr[i] = Integer.parseInt(tempDataArray[i]);
        }
        root = tree.sortedArrayToBST(arr, 0, n - 1);
        tree.levelOrder();
        System.out.println();
    }
}
