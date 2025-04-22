package Assignment_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class p2 extends JFrame {
    private int[] heights;
    private int maxWaterLeft = -1;
    private int maxWaterRight = -1;
    
    private JTextField inputField;
    private JPanel drawPanel;
    
    public p2() {
        setTitle("Water Container");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel inputPanel = new JPanel();
        inputField = new JTextField(20);
        JButton calcButton = new JButton("Calculate");
        
        inputPanel.add(new JLabel("Heights:"));
        inputPanel.add(inputField);
        inputPanel.add(calcButton);
        
        drawPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawWaterContainer(g);
            }
        };
        
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);
        
        calcButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                processInput();
            }
        });
    }
    
    private void processInput() {
        try {
            String[] values = inputField.getText().trim().split("\\s+");
            heights = new int[values.length];
            
            for (int i = 0; i < values.length; i++) {
                heights[i] = Integer.parseInt(values[i]);
            }
            
            findMaxWaterContainer();
            drawPanel.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Enter integers separated by spaces.");
        }
    }
    
    private void findMaxWaterContainer() {
        if (heights == null || heights.length < 2) return;
        
        int maxArea = 0;
        int left = 0;
        int right = heights.length - 1;
        
        while (left < right) {
            int area = (right - left) * Math.min(heights[left], heights[right]);
            
            if (area > maxArea) {
                maxArea = area;
                maxWaterLeft = left;
                maxWaterRight = right;
            }
            
            if (heights[left] <= heights[right]) {
                left++;
            } else {
                right--;
            }
        }
    }
    
    private void drawWaterContainer(Graphics g) {
        if (heights == null || heights.length < 2) return;
        
        int width = drawPanel.getWidth();
        int height = drawPanel.getHeight();
        int barWidth = width / heights.length;
        
        int maxHeight = 0;
        for (int h : heights) {
            maxHeight = Math.max(maxHeight, h);
        }
        
        if (maxWaterLeft >= 0 && maxWaterRight >= 0) {
            int waterHeight = Math.min(heights[maxWaterLeft], heights[maxWaterRight]);
            int scaledWaterHeight = (int)((double)waterHeight / maxHeight * (height - 20));
            int waterY = height - scaledWaterHeight;
            
            int waterX = maxWaterLeft * barWidth + barWidth;
            int waterWidth = (maxWaterRight - maxWaterLeft - 1) * barWidth;
            
            g.setColor(new Color(0, 0, 255, 128));
            g.fillRect(waterX, waterY, waterWidth, scaledWaterHeight);
            
            g.setColor(Color.BLACK);
            g.drawString("Max Water: " + ((maxWaterRight - maxWaterLeft) * waterHeight), 10, 20);
        }
        
        for (int i = 0; i < heights.length; i++) {
            int x = i * barWidth;
            int barHeight = (int)((double)heights[i] / maxHeight * (height - 20));
            int y = height - barHeight;
            
            g.setColor(Color.BLACK);
            g.drawRect(x, y, barWidth - 1, barHeight);
            
            boolean isContainerWall = (i == maxWaterLeft || i == maxWaterRight);
            if (isContainerWall) {
                g.setColor(new Color(100, 100, 100));
            } else {
                g.setColor(new Color(200, 200, 200));
            }
            g.fillRect(x + 1, y + 1, barWidth - 3, barHeight - 1);
            
            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(i), x + barWidth/2 - 4, height - 2);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new p2().setVisible(true);
            }
        });
    }
}