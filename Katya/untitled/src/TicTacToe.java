import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TicTacToe extends JFrame {
    private char[][] board = new char[3][3];
    private char currentPlayer = 'X';
    private boolean gameEnded = false;
    private JLabel statusLabel;

    public TicTacToe() {
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JButton restartButton = new JButton("Перезапустить");
        restartButton.addActionListener(e -> restartGame());
        restartButton.setFont(new Font("Arial", Font.PLAIN, 14));
        restartButton.setFocusPainted(false);
        restartButton.setBackground(Color.WHITE);
        restartButton.setForeground(Color.BLACK);
        restartButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        restartButton.setMargin(new Insets(8, 16, 8, 16));
        restartButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        restartButton.addActionListener(e -> restartGame());
        add(restartButton, BorderLayout.NORTH);

        JPanel gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, getWidth(), getHeight());

                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.BOLD, 40));
                FontMetrics fontMetrics = g.getFontMetrics();

                int cellWidth = getWidth() / 3;
                int cellHeight = getHeight() / 3;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        g.drawRect(j * cellWidth, i * cellHeight, cellWidth, cellHeight);
                        if (board[i][j] != '\u0000') {
                            char symbol = board[i][j];
                            int symbolWidth = fontMetrics.stringWidth(String.valueOf(symbol));
                            int symbolHeight = fontMetrics.getAscent();

                            int x = j * cellWidth + (cellWidth - symbolWidth) / 2;
                            int y = i * cellHeight + (cellHeight + symbolHeight) / 2;

                            g.drawString(String.valueOf(symbol), x, y);
                        }
                    }
                }
            }
        };

        gamePanel.setPreferredSize(new Dimension(300, 300));
        gamePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!gameEnded) {
                    int cellWidth = gamePanel.getWidth() / 3;
                    int cellHeight = gamePanel.getHeight() / 3;

                    int x = e.getX() / cellWidth;
                    int y = e.getY() / cellHeight;

                    if (board[y][x] == '\u0000') {
                        board[y][x] = currentPlayer;
                        gamePanel.repaint();

                        if (checkForWin() || checkForDraw()) {
                            gameEnded = true;
                            showResult();
                        }
                        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                        statusLabel.setText("Ход игрока: " + currentPlayer);
                    }
                }
            }
        });

        add(gamePanel, BorderLayout.CENTER);

        statusLabel = new JLabel("Ход игрока: " + currentPlayer, SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        add(statusLabel, BorderLayout.SOUTH);

        initializeBoard();
        pack();
        setLocationRelativeTo(null);
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '\u0000';
            }
        }
    }

    private boolean checkForWin() {
        return (checkRowsForWin() || checkColumnsForWin() || checkDiagonalsForWin());
    }

    private boolean checkRowsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[i][0], board[i][1], board[i][2])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumnsForWin() {
        for (int i = 0; i < 3; i++) {
            if (checkRowCol(board[0][i], board[1][i], board[2][i])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonalsForWin() {
        return ((checkRowCol(board[0][0], board[1][1], board[2][2])) ||
                (checkRowCol(board[0][2], board[1][1], board[2][0])));
    }

    private boolean checkRowCol(char c1, char c2, char c3) {
        return ((c1 != '\u0000') && (c1 == c2) && (c2 == c3));
    }

    private boolean checkForDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '\u0000') {
                    return false;
                }
            }
        }
        return true;
    }

    private void showResult() {
        String resultText;
        if (checkForWin()) {
            resultText = "Игрок " + currentPlayer + " выиграл!";
        } else {
            resultText = "Ничья!";
        }
        JOptionPane.showMessageDialog(this, resultText);
    }

    private void restartGame() {
        gameEnded = false;
        currentPlayer = 'X';
        initializeBoard();
        statusLabel.setText("Ход игрока: " + currentPlayer);
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TicTacToe ticTacToe = new TicTacToe();
            ticTacToe.setVisible(true);
        });
    }
}
