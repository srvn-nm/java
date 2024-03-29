package com.company;

public class MaxHeap {

    // Returns true if arr[i..n-1]
    // represents a max-heap
    static boolean isHeap(int[] arr, int i, int n) {
        // If (2 * i) + 1 >= n, then leaf node, so return true
        if (i >= (n - 1) / 2) {
            return true;
        }
        // If an internal node and
        // is greater than its
        // children, and same is
        // recursively true for the
        // children
        if (arr[i] >= arr[2 * i + 1] && arr[i] >= arr[2 * i + 2] && isHeap(arr, 2 * i + 1, n) && isHeap(arr, 2 * i + 2, n)) {
            return true;
        }

        return false;
    }

    // Driver program
    public static void main(String[] args) {
        int[] arr = { 90, 15, 10, 7, 12, 2, 7, 3 };
        int n = arr.length - 1;
        if (isHeap(arr, 0, n)) {
            System.out.println("Yes");
        }
        else {
            System.out.println("No");
        }
    }
}
