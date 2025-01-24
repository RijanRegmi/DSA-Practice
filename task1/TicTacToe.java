import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {
    private JButton[][] buttons; // Grid buttons
    private boolean isPlayerXTurn; // True if it's Player X's turn, false for Player O
    private int movesCount; // Number of moves made

    // Constructor
    public TicTacToe() {
        initializeGUI();
    }

    // Initialize the GUI components
    private void initializeGUI() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3)); // 3x3 grid for the game

        buttons = new JButton[3][3];
        isPlayerXTurn = true; // Player X starts first
        movesCount = 0;

        // Create and add buttons to the grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 50));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].addActionListener(new ButtonClickListener(i, j));
                add(buttons[i][j]);
            }
        }
    }

    // Listener for button clicks
    private class ButtonClickListener implements ActionListener {
        private int row, col;

        public ButtonClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // If the button is already clicked, do nothing
            if (!buttons[row][col].getText().equals("")) {
                return;
            }

            // Set the button text based on the current player's turn
            buttons[row][col].setText(isPlayerXTurn ? "X" : "O");
            movesCount++;

            // Check for a winner or a draw
            if (checkWinner(row, col)) {
                JOptionPane.showMessageDialog(TicTacToe.this,
                        "Player " + (isPlayerXTurn ? "X" : "O") + " wins!",
                        "Game Over", JOptionPane.INFORMATION_MESSAGE);
                resetGame();
            } else if (movesCount == 9) {
                JOptionPane.showMessageDialog(TicTacToe.this,
                        "It's a draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                resetGame();
            } else {
                isPlayerXTurn = !isPlayerXTurn; // Switch turns
            }
        }
    }

    // Check if the current player wins
    private boolean checkWinner(int row, int col) {
        String playerSymbol = isPlayerXTurn ? "X" : "O";

        // Check row
        if (buttons[row][0].getText().equals(playerSymbol) &&
                buttons[row][1].getText().equals(playerSymbol) &&
                buttons[row][2].getText().equals(playerSymbol)) {
            return true;
        }

        // Check column
        if (buttons[0][col].getText().equals(playerSymbol) &&
                buttons[1][col].getText().equals(playerSymbol) &&
                buttons[2][col].getText().equals(playerSymbol)) {
            return true;
        }

        // Check diagonal (top-left to bottom-right)
        if (buttons[0][0].getText().equals(playerSymbol) &&
                buttons[1][1].getText().equals(playerSymbol) &&
                buttons[2][2].getText().equals(playerSymbol)) {
            return true;
        }

        // Check diagonal (top-right to bottom-left)
        if (buttons[0][2].getText().equals(playerSymbol) &&
                buttons[1][1].getText().equals(playerSymbol) &&
                buttons[2][0].getText().equals(playerSymbol)) {
            return true;
        }

        return false;
    }

    // Reset the game
    private void resetGame() {
        isPlayerXTurn = true;
        movesCount = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe game = new TicTacToe();
            game.setVisible(true);
        });
    }
}
