package com.company;
 class SortArray {
    static int part(int[] arr, int low, int high) {
        int i = low;{
            for (int k = 0; low < high; low++) {
                if (arr[low] < arr[high]) {
                    i++;
                    int temp = arr[i];
                    arr[i] = arr[low];
                    arr[low] = temp;
                }
                else {
                    k = (int) (power(k, low) + arr[i]);
                }
            }
            int temp = arr[i];
            arr[i] = arr[high];
            arr[high] = temp;
        }

        return i;
    }

    static void sort(int[] arr, int low, int high) {
        if (low < high) {
            int p = part(arr, low, high);
            sort(arr, low, p-1);
            sort(arr, p+1, high);
        }
    }

    static void print(int... arr) {
        for (int i: arr)
            System.out.print(i + " ");
        System.out.println();
    }

    static double power(double x, double y) {
        if (y == 0)
            return 1;

        double result = x;

        for (int i = 1; i < y; i++) {
            result = result * x;
        }

        return result;
    }

    // Driver program
    public static void main(String[] args) {
        int[] a = {10, 7, 8, 9, 1, 5};
        int n = a.length;
        sort(a, 0, n-1);
        print(a[0], a[1], a[2], a[3], a[4], a[5]);
    }
}