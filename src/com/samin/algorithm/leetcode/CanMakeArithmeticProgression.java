package com.samin.algorithm.leetcode;

public class CanMakeArithmeticProgression {
    public static void main(String[] args) {
        System.out.println(
                new CanMakeArithmeticProgression()
                        .canMakeArithmeticProgression(new int[] {3, 5, 1}));
        System.out.println(
                new CanMakeArithmeticProgression()
                        .canMakeArithmeticProgression(new int[] {5, 3, 1}));
        System.out.println(
                new CanMakeArithmeticProgression()
                        .canMakeArithmeticProgression(new int[] {1, 2, 4}));
    }

    public boolean canMakeArithmeticProgression(int[] arr) {
        if (arr.length == 2) {
            return true;
        }

        quickSort(arr, 0, arr.length - 1);
        int index = arr[1] - arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (i != arr.length - 1 && arr[i + 1] - arr[i] != index) {
                return false;
            }
        }

        return true;
    }

    private void quickSort(int arr[], int left, int right) {
        if (left < right) {
            int i = left;
            int j = right;
            int index = arr[left];
            int temp;
            while (i < j) {
                while (arr[j] >= index && i < j) {
                    j--;
                }

                while (arr[i] <= index && i < j) {
                    i++;
                }

                if (i < j) {
                    temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }

            arr[left] = arr[i];
            arr[i] = index;
            quickSort(arr, left, i - 1);
            quickSort(arr, i + 1, right);
        }
    }
}
