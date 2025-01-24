import javax.swing.*;
import java.awt.*;

public class BackTracking extends JFrame {
    private int size;
    private int[][] board;
    private JPanel boardPanel;
    private JButton solveButton;

    // Constructor
    public BackTracking(int size) {
        this.size = size;
        this.board = new int[size][size];
        initializeGUI();
    }

    // Initialize the GUI components
    private void initializeGUI() {
        setTitle("N-Queens Problem Solver");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Board Panel
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(size, size));
        add(boardPanel, BorderLayout.CENTER);

        // Solve Button
        solveButton = new JButton("Solve");
        solveButton.addActionListener(e -> solveNQueens());
        add(solveButton, BorderLayout.SOUTH);

        drawBoard(); // Draw the initial empty board
    }

    // Solve the N-Queens problem and update the board
    private void solveNQueens() {
        if (solve(0)) {
            drawBoard(); // Update the board with the solution
            JOptionPane.showMessageDialog(this, "Solution found!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "No solution exists!", "Failure", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Recursive method to solve N-Queens
    private boolean solve(int row) {
        if (row == size)
            return true; // All queens placed

        for (int col = 0; col < size; col++) {
            if (isSafe(row, col)) {
                board[row][col] = 1; // Place the queen
                if (solve(row + 1)) {
                    return true; // Proceed to the next row
                }
                board[row][col] = 0; // Backtrack
            }
        }
        return false;
    }

    // Check if it's safe to place a queen at board[row][col]
    private boolean isSafe(int row, int col) {
        // Check the column
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 1)
                return false;
        }

        // Check upper-left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1)
                return false;
        }

        // Check upper-right diagonal
        for (int i = row, j = col; i >= 0 && j < size; i--, j++) {
            if (board[i][j] == 1)
                return false;
        }

        return true;
    }

    // Draw the chessboard and place queens
    private void drawBoard() {
        boardPanel.removeAll(); // Clear the board
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                JPanel cell = new JPanel();
                if ((i + j) % 2 == 0) {
                    cell.setBackground(Color.WHITE); // White cell
                } else {
                    cell.setBackground(Color.GRAY); // Gray cell
                }
                if (board[i][j] == 1) {
                    JLabel queenLabel = new JLabel("Q", SwingConstants.CENTER);
                    queenLabel.setFont(new Font("Arial", Font.BOLD, 24));
                    queenLabel.setForeground(Color.RED);
                    cell.add(queenLabel); // Add a queen label
                }
                boardPanel.add(cell);
            }
        }
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int n = 8; // Size of the chessboard
            BackTracking gui = new BackTracking(n);
            gui.setVisible(true);
        });
    }
}
