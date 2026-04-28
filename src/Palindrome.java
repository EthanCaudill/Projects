import java.util.Scanner;

public class Palindrome {

    // Recursive function to check if a string is a palindrome
    public static boolean isPalindrome(String str) {
        // Base case: if length is 0 or 1, it's a palindrome
        if (str.length() <= 1)
            return true;

        // Compare first and last characters
        if (str.charAt(0) != str.charAt(str.length() - 1))
            return false;

        // Recursive case: check substring without first and last chars
        return isPalindrome(str.substring(1, str.length() - 1));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a word: ");
        String word = input.nextLine().toLowerCase();

        if (isPalindrome(word))
            System.out.println(word + " is a palindrome.");
        else
            System.out.println(word + " is NOT a palindrome.");

        input.close();
    }
}
