import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class TicTacToeGame {
    int bordWidth = 600;
    int boardHeight = 650;
    JFrame frame = new JFrame("Tic Tac Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel restartPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton [][] boardButton = new JButton[3][3];
    JButton restart = new JButton("Restart");
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    boolean gameOver = false;
    int turns = 0;
    TicTacToeGame(){
        frame.setVisible(true);
        frame.setSize(bordWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.gray);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic Tac Toe");
        textLabel.setOpaque(true);

        textLabel.setLayout(new BorderLayout());
        textPanel.setBackground(Color.darkGray);
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.BLACK);
        frame.add((boardPanel));

        restart.setBackground(Color.BLACK);
        restart.setForeground(Color.white);
        restart.setFont(new Font("Arial", Font.BOLD, 30));
        restart.setHorizontalAlignment(JButton.CENTER);
        restart.setOpaque(true);

        restartPanel.setLayout(new BorderLayout());
        restartPanel.add(restart);
        frame.add(restart,BorderLayout.SOUTH);

        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                JButton tile = new JButton();
                boardButton[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.DARK_GRAY);
                tile.setForeground(Color.WHITE);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                //tile.setText(currentPlayer);
                tile.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        if(gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if(tile.getText() == ""){
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!gameOver){
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s Turn Now");
                            }
                        }
                    }
                });
            }
        }
        //Restart Button
        restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < 3; i++){
                    for(int r = 0; r < 3; r++){
                        boardButton[i][r].setText("");
                        boardButton[i][r].setForeground(Color.white);
                        boardButton[i][r].setBackground(Color.DARK_GRAY);
                    }
                }
                gameOver = false;
                turns = 0;
                currentPlayer = playerX;
                textLabel.setText("Tic Tac Toe");
            }
        });
    }
    void checkWinner(){
        for(int r =0; r < 3; r++){
            if(boardButton[r][0].getText() == "") continue;

            if(boardButton[r][0].getText() == boardButton[r][1].getText() &&
                    boardButton[r][1].getText() == boardButton[r][2].getText()){
                for(int i = 0; i < 3; i++){
                    setWinner(boardButton[r][i]);
                }
                // Set the game over state and return
                gameOver = true;
                return;
            }
        }
        //Vertical
        for (int v = 0; v < 3; v++){
            if(boardButton[0][v].getText() == "") continue;
            if(boardButton[0][v].getText() == boardButton[1][v].getText() &&
                    boardButton[1][v].getText() == boardButton[2][v].getText()){
                for(int i = 0; i <3; i++){
                    setWinner(boardButton[i][v]);
                }
                // Set the game over state and return
                gameOver = true;
                return;
            }
        }
        //Diagonally
        if(boardButton[0][0].getText() == boardButton[1][1].getText() &&
                boardButton[1][1].getText() == boardButton[2][2].getText() &&
                boardButton[0][0].getText() != ""){
            for(int i = 0; i < 3; i++){
                setWinner(boardButton[i][i]);
            }
            // Set the game over state and return
            gameOver = true;
            return;
        }
        //anti-diagonally
        if(boardButton[0][2].getText() == boardButton[1][1].getText() &&
                boardButton[1][1].getText() == boardButton[2][0].getText() &&
                boardButton[0][2].getText() != ""){
            setWinner(boardButton[0][2]);
            setWinner(boardButton[1][1]);
            setWinner(boardButton[2][0]);

            // Set the game over state and return
            gameOver = true;
            return;
        }
        if(turns == 9){
            for(int r = 0; r < 3; r++){
                for (int c = 0; c < 3; c++){
                    setTie(boardButton[r][c]);
                }
            }
            // Set the game over state and return
            gameOver = true;
        }
    }
    void setWinner(JButton tile){
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText(currentPlayer + " is the Winner!");
    }
    void setTie(JButton tile){
        tile.setForeground(Color.BLUE);
        tile.setBackground(Color.DARK_GRAY);
        textLabel.setText("Tie");
    }
}
