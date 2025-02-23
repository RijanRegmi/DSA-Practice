package Assigment;

import javax.swing.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class TetrisGame extends JPanel implements ActionListener {
    private final int ROWS = 20, COLS = 10, BLOCK_SIZE = 30;
    private int[][] board = new int[ROWS][COLS];
    private Queue<int[][]> blockQueue = new LinkedList<>();
    private Stack<int[][]> boardStack = new Stack<>();
    private Timer timer;
    private int[][] currentBlock;
    private int blockRow = 0, blockCol = 4;

    private final int[][][] SHAPES = {
            { { 1, 1, 1, 1 } },
            { { 1, 1 }, { 1, 1 } },
            { { 0, 1, 0 }, { 1, 1, 1 } },
            { { 1, 1, 0 }, { 0, 1, 1 } },
            { { 0, 1, 1 }, { 1, 1, 0 } }
    };

    public TetrisGame() {
        setPreferredSize(new Dimension(COLS * BLOCK_SIZE, ROWS * BLOCK_SIZE));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                handleInput(e.getKeyCode());
            }
        });

        generateBlock();
        timer = new Timer(500, this);
        timer.start();
    }

    private void generateBlock() {
        Random rand = new Random();
        currentBlock = SHAPES[rand.nextInt(SHAPES.length)];
        blockRow = 0;
        blockCol = 4;
        blockQueue.add(currentBlock.clone());
    }

    private boolean canMove(int dRow, int dCol) {
        for (int r = 0; r < currentBlock.length; r++) {
            for (int c = 0; c < currentBlock[r].length; c++) {
                if (currentBlock[r][c] == 1) {
                    int newR = blockRow + r + dRow;
                    int newC = blockCol + c + dCol;
                    if (newR >= ROWS || newC < 0 || newC >= COLS || board[newR][newC] == 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private void placeBlock() {
        for (int r = 0; r < currentBlock.length; r++) {
            for (int c = 0; c < currentBlock[r].length; c++) {
                if (currentBlock[r][c] == 1) {
                    board[blockRow + r][blockCol + c] = 1;
                }
            }
        }
        clearRows();
        generateBlock();
    }

    private void clearRows() {
        for (int r = ROWS - 1; r >= 0; r--) {
            boolean full = true;
            for (int c = 0; c < COLS; c++) {
                if (board[r][c] == 0) {
                    full = false;
                    break;
                }
            }
            if (full) {
                for (int newRow = r; newRow > 0; newRow--) {
                    board[newRow] = board[newRow - 1].clone();
                }
                board[0] = new int[COLS];
                r++;
            }
        }
    }

    private void rotateBlock() {
        int rows = currentBlock.length, cols = currentBlock[0].length;
        int[][] rotated = new int[cols][rows];

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                rotated[c][rows - 1 - r] = currentBlock[r][c];
            }
        }

        int oldBlockRow = blockRow, oldBlockCol = blockCol;
        currentBlock = rotated;
        if (!canMove(0, 0)) {
            currentBlock = SHAPES[0];
            blockRow = oldBlockRow;
            blockCol = oldBlockCol;
        }
    }

    private void handleInput(int key) {
        if (key == KeyEvent.VK_LEFT && canMove(0, -1)) {
            blockCol--;
        } else if (key == KeyEvent.VK_RIGHT && canMove(0, 1)) {
            blockCol++;
        } else if (key == KeyEvent.VK_DOWN && canMove(1, 0)) {
            blockRow++;
        } else if (key == KeyEvent.VK_UP) {
            rotateBlock();
        }
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (canMove(1, 0)) {
            blockRow++;
        } else {
            placeBlock();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GRAY);
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c] == 1) {
                    g.fillRect(c * BLOCK_SIZE, r * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }

        g.setColor(Color.CYAN);
        for (int r = 0; r < currentBlock.length; r++) {
            for (int c = 0; c < currentBlock[r].length; c++) {
                if (currentBlock[r][c] == 1) {
                    g.fillRect((blockCol + c) * BLOCK_SIZE, (blockRow + r) * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris");
        TetrisGame game = new TetrisGame();
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
