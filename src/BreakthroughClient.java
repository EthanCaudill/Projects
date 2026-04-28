import java.io.*;
import java.net.*;
import java.util.*;

public class BreakthroughClient {

    private static final String SERVER = "localhost";
    private static final int PORT = 5000;

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket(SERVER, PORT);

        BufferedReader input = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(
                socket.getOutputStream(), true);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String response = input.readLine();

            if (response == null) break;

            if (response.equals("BOARD")) {
                printBoard(input);
                continue;
            }

            System.out.println(response);

            if (response.equals("YOUR_TURN")) {
                System.out.print("Enter move (r1 c1 r2 c2): ");
                String move = scanner.nextLine();
                output.println("MOVE " + move);
            }

            if (response.equals("WIN") || response.equals("LOSE")) {
                break;
            }
        }

        socket.close();
    }

    private static void printBoard(BufferedReader input) throws IOException {
        System.out.println("\n  0 1 2 3 4 5 6 7");

        for (int r = 0; r < 8; r++) {
            String row = input.readLine();
            System.out.print(r + " ");
            for (char c : row.toCharArray()) {
                System.out.print(c + " ");
            }
            System.out.println();
        }

        input.readLine(); // consume END
        System.out.println();
    }
}