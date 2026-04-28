import java.util.Arrays;
import java.util.Scanner;

public class SelectionSort {

    public static int[] doSelectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int index = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[index]) {
                    index = j;
                }
            }

            int smallerNumber = arr[index];
            arr[index] = arr[i];
            arr[i] = smallerNumber;
        }

        return arr;
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("How many numbers do you want to sort?");
        int n = scanner.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter " + n + " numbers:");

        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int[] sortedArray = doSelectionSort(arr);

        System.out.println("Sorted Array:");
        System.out.println(Arrays.toString(sortedArray));

        scanner.close();
    }
}