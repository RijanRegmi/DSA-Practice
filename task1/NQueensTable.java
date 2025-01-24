import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class NQueensTable extends JFrame {
    private int size; // Size of the chessboard (N)
    private int[][] board; // 2D array for the chessboard
    private JTable chessTable;
    private JButton solveButton;

    // Constructor
    public NQueensTable(int size) {
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

        // Initialize the JTable to represent the chessboard
        chessTable = new JTable(size, size);
        chessTable.setRowHeight(50); // Set the row height
        chessTable.setDefaultRenderer(Object.class, new ChessboardRenderer());
        add(new JScrollPane(chessTable), BorderLayout.CENTER);

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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == 1) {
                    chessTable.setValueAt("Q", i, j); // Place the queen
                } else {
                    chessTable.setValueAt("", i, j); // Empty cell
                }
            }
        }
    }

    // Custom renderer for the chessboard cells
    private static class ChessboardRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int col) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            if ((row + col) % 2 == 0) {
                cell.setBackground(Color.WHITE); // White cell
            } else {
                cell.setBackground(Color.GRAY); // Gray cell
            }
            if ("Q".equals(value)) {
                ((JLabel) cell).setHorizontalAlignment(SwingConstants.CENTER);
                cell.setFont(new Font("Arial", Font.BOLD, 20));
                cell.setForeground(Color.RED); // Highlight queens in red
            } else {
                cell.setForeground(Color.BLACK);
            }
            return cell;
        }
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            int n = 8; // Size of the chessboard
            NQueensTable gui = new NQueensTable(n);
            gui.setVisible(true);
        });
    }
}
