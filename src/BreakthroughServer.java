import java.io.*;
import java.net.*;

public class BreakthroughServer {

    private static final int PORT = 5000;
    private static char[][] board = new char[8][8];
    private static Player whitePlayer;
    private static Player blackPlayer;
    private static boolean whiteTurn = true;

    public static void main(String[] args) throws Exception {
        initializeBoard();
        ServerSocket listener = new ServerSocket(PORT);
        System.out.println("Server running...");

        whitePlayer = new Player(listener.accept(), 'W');
        whitePlayer.send("WELCOME WHITE");

        blackPlayer = new Player(listener.accept(), 'B');
        blackPlayer.send("WELCOME BLACK");

        sendBoard();
        whitePlayer.send("YOUR_TURN");

        while (true) {
            Player current = whiteTurn ? whitePlayer : blackPlayer;
            Player opponent = whiteTurn ? blackPlayer : whitePlayer;

            String command = current.receive();

            if (processMove(command, current.piece)) {
                current.send("VALID");
                sendBoard();

                if (checkWin(current.piece)) {
                    current.send("WIN");
                    opponent.send("LOSE");
                    break;
                }

                whiteTurn = !whiteTurn;
                opponent.send("YOUR_TURN");

            } else {
                current.send("INVALID");
                current.send("YOUR_TURN");
            }
        }

        listener.close();
    }

    private static void initializeBoard() {
        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++) {
                board[r][c] = '.';
            }
        }

        for (int c = 0; c < 8; c++) {
            board[0][c] = 'W';
            board[1][c] = 'W';
            board[6][c] = 'B';
            board[7][c] = 'B';
        }
    }

    private static void sendBoard() {
        whitePlayer.send("BOARD");
        blackPlayer.send("BOARD");

        for (int r = 0; r < 8; r++) {
            String row = new String(board[r]);
            whitePlayer.send(row);
            blackPlayer.send(row);
        }

        whitePlayer.send("END");
        blackPlayer.send("END");
    }

    private static boolean processMove(String command, char piece) {
        try {
            String[] parts = command.split(" ");
            int r1 = Integer.parseInt(parts[1]);
            int c1 = Integer.parseInt(parts[2]);
            int r2 = Integer.parseInt(parts[3]);
            int c2 = Integer.parseInt(parts[4]);

            if (!isValidMove(r1, c1, r2, c2, piece)) return false;

            board[r2][c2] = piece;
            board[r1][c1] = '.';
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isValidMove(int r1, int c1, int r2, int c2, char piece) {

        if (r2 < 0 || r2 > 7 || c2 < 0 || c2 > 7) return false;
        if (board[r1][c1] != piece) return false;

        int direction = (piece == 'W') ? 1 : -1;

        if (r2 == r1 + direction) {

            // Forward move
            if (c2 == c1 && board[r2][c2] == '.') return true;

            // Diagonal move
            if (Math.abs(c2 - c1) == 1) {
                if (board[r2][c2] == '.') return true;  // diagonal empty
                if (board[r2][c2] != piece) return true; // capture
            }
        }

        return false;
    }

    private static boolean checkWin(char piece) {
        if (piece == 'W') {
            for (int c = 0; c < 8; c++)
                if (board[7][c] == 'W') return true;
        } else {
            for (int c = 0; c < 8; c++)
                if (board[0][c] == 'B') return true;
        }
        return false;
    }

    static class Player {
        Socket socket;
        BufferedReader input;
        PrintWriter output;
        char piece;

        Player(Socket socket, char piece) throws IOException {
            this.socket = socket;
            this.piece = piece;
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
        }

        void send(String msg) {
            output.println(msg);
        }

        String receive() throws IOException {
            return input.readLine();
        }
    }
}