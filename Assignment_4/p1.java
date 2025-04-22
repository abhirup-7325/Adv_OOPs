package Assignment_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class p1 {
    private static boolean[] bulbs;
    private static int n;
    private static int currentRound = 0;
    
    private static JFrame frame;
    private static JPanel bulbPanel;
    private static JLabel roundLabel;
    private static JButton nextButton;
    
    public static void main(String[] args) {
        String input = JOptionPane.showInputDialog("Enter number of bulbs (n < 10):");
        try {
            n = Integer.parseInt(input);
            if (n < 1 || n >= 10) {
                JOptionPane.showMessageDialog(null, "Please enter a value between 1 and 9.");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number.");
            return;
        }
        
        bulbs = new boolean[n];
        
        frame = new JFrame("Bulb Toggling");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);
        frame.setLayout(new BorderLayout());
        
        JPanel controlPanel = new JPanel();
        roundLabel = new JLabel("Round: 0");
        nextButton = new JButton("Next Round");
        controlPanel.add(roundLabel);
        controlPanel.add(nextButton);
        
        bulbPanel = new JPanel();
        updateBulbDisplay();
        
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                advanceRound();
            }
        });
        
        frame.add(controlPanel, BorderLayout.NORTH);
        frame.add(bulbPanel, BorderLayout.CENTER);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private static void advanceRound() {
        currentRound = (currentRound % n) + 1;
        
        if (currentRound == n) {
            bulbs[n-1] = !bulbs[n-1];
        } else {
            for (int j = currentRound-1; j < n; j += currentRound) {
                bulbs[j] = !bulbs[j];
            }
        }
        
        roundLabel.setText("Round: " + currentRound);
        updateBulbDisplay();
    }
    
    private static void updateBulbDisplay() {
        bulbPanel.removeAll();
        
        for (int i = 0; i < bulbs.length; i++) {
            final int bulbIndex = i;
            JPanel bulbContainer = new JPanel();
            bulbContainer.setLayout(new BorderLayout());
            
            JLabel numLabel = new JLabel(String.valueOf(bulbIndex + 1), JLabel.CENTER);
            
            JPanel bulb = new JPanel() {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    if (bulbs[bulbIndex]) {
                        g.setColor(Color.YELLOW);
                        g.fillOval(5, 5, 30, 30);
                    }
                    g.setColor(Color.BLACK);
                    g.drawOval(5, 5, 30, 30);
                }
                
                @Override
                public Dimension getPreferredSize() {
                    return new Dimension(40, 40);
                }
            };
            
            bulbContainer.add(bulb, BorderLayout.CENTER);
            bulbContainer.add(numLabel, BorderLayout.SOUTH);
            bulbPanel.add(bulbContainer);
        }
        
        bulbPanel.revalidate();
        bulbPanel.repaint();
    }
}