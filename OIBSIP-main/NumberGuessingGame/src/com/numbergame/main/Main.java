package com.numbergame.main;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.LineBorder;

public class Main extends JFrame {
    private static final long serialVersionUID = 1L;
	private int targetNumber;
    private int attempts;
    private int maxAttempts;
    private int totalRounds;
    private int currentRound;
    private int totalScore;
    private JButton guessButton; 

    private JTextField guessField;
    private JTextArea resultArea;
    private JPanel inputPanel; 
    private JLabel leftAttempts;

    public Main() {
        super("Number Guessing Game");
        setFont(new Font("DejaVu Sans Light", Font.BOLD, 20));
        setModalExclusionType(ModalExclusionType.TOOLKIT_EXCLUDE);
        getContentPane().setForeground(new Color(51, 204, 255));
        getContentPane().setBackground(new Color(0, 255, 153));
       // setOpacity(0.5f);
        setBackground(new Color(0, 204, 255));
        setForeground(new Color(0, 0, 0));
        setResizable(false);

        maxAttempts = 10;
        totalRounds = 3;

        getContentPane().setLayout(new BorderLayout());

        guessField = new JTextField();
        guessField.setBorder(new LineBorder(new Color(32, 178, 170), 5, true));
        guessField.setBackground(new Color(192, 192, 192));
        guessField.setColumns(2);
        guessButton = new JButton("Guess");
        guessButton.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(0, 0, 0), new Color(0, 255, 0), new Color(0, 191, 255), new Color(255, 228, 225)));
        guessButton.setBackground(new Color(238, 232, 170));
        resultArea = new JTextArea();
        resultArea.setBackground(new Color(153, 255, 102));
        resultArea.setEditable(false);
        leftAttempts=new JLabel("Attempts left:"+maxAttempts);
        inputPanel= new JPanel(new FlowLayout());
        inputPanel.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 204, 255), new Color(0, 255, 0), new Color(0, 255, 102), new Color(0, 204, 255)));
        inputPanel.setBackground(new Color(51, 204, 102));
        JLabel label = new JLabel("Enter your guess: ");
        inputPanel.add(label);
        inputPanel.add(guessField);
        inputPanel.add(guessButton);
        inputPanel.add(leftAttempts);
        

        getContentPane().add(inputPanel, BorderLayout.NORTH);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{inputPanel, label, guessField, guessButton, leftAttempts, scrollPane, resultArea}));
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processGuess();
            }
        });
        
        guessField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    processGuess();
                }
            }
        });

        newGame();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void newGame() {
        currentRound = 1;
        totalScore = 0;
        startNewRound();
    }

    private void startNewRound() {
        resultArea.setText("Round " + currentRound + ":			Score:"+totalScore+"\n");
        targetNumber = generateRandomNumber(1, 100);
        attempts = 0;
    }

    private void processGuess() {
        if (attempts < maxAttempts) {
        	leftAttempts.setText("Attempts Left:"+(maxAttempts-attempts-1));
        	//resultArea.append(totalScore+"\n");
            try {
                int userGuess = Integer.parseInt(guessField.getText());
                attempts++;
                
                

                if (userGuess == targetNumber) {
                	   JOptionPane.showMessageDialog(resultArea,"Congratulations! You guessed the correct number in " + attempts + " attempts.\n");
                    int roundScore = maxAttempts - attempts + 1; // More points for fewer attempts
                    totalScore += roundScore;

                    if (currentRound < totalRounds) {
                        currentRound++;
                        startNewRound();
                    } else {
                    	startNewRound();
                        resultArea.append("\nGame Over! Your total score is: " + totalScore);
                        guessField.setEnabled(false);
                        guessButton.setEnabled(false);
                    }
                } else if (userGuess < targetNumber) {
                    resultArea.append("Too low! Try again.\n");
                } else {
                    resultArea.append("Too high! Try again.\n");
                }
            } catch (NumberFormatException ex) {
                resultArea.append("Invalid input. Please enter a valid number.\n");
            }
        } else {
            resultArea.append("Sorry, you've reached the maximum number of attempts. The correct number was: " + targetNumber + "\n");

            if (currentRound < totalRounds) {
                currentRound++;
                startNewRound();
            } else {
            	startNewRound();
                resultArea.append("\nGame Over! Your total score is: " + totalScore);
                guessField.setEnabled(false);
                guessButton.setEnabled(false);
            }
        }

        guessField.setText(""); // Clear the text field
        guessField.requestFocus(); // Set focus back to the text field
    }

    private int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main());
    }
}

