import java.util.Scanner;

public class AddNumbers {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Asks user how many numbers they want to add
        System.out.print("How many numbers do you want to add? ");
        int count = scanner.nextInt();

        int sum = 0;
        // Adds numbers that the user inputs
        for (int i = 1; i <= count; i++) {
            System.out.print("Enter number " + i + ": ");
            sum += scanner.nextInt();
        }
        // Outputs users sum of numbers
        System.out.println("The sum of the numbers is: " + sum);

        scanner.close();
    }
    // allows test cases to be used (JUnit)
    public static Object sum(int[] ints) {


        return null;
    }
}