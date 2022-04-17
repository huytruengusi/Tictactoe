package com.example;

import javax.swing.*;
import java.awt.*;

public class App 
{
    public static void main( String[] args )
    {
        //jPanel
        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        
        //Board
        Board board = new Board();
        board.setPreferredSize(new Dimension(300,300));
        
        //Flow Layout
        FlowLayout flowLayout = new FlowLayout (FlowLayout.CENTER , 0 , 0);

        //Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(flowLayout);

        //btnStart
        JButton btnStart = new JButton("Start");
        
        // lblTime
        JLabel lblTime = new JLabel("00:00");

        bottomPanel.add(lblTime);
        bottomPanel.add(btnStart);


        jPanel.add(board);
        jPanel.add(bottomPanel);

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame jFrame = new JFrame("TIC TAC TOE");
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(true);
        jFrame.add(jPanel);

        int x= (int) (dimension.getWidth()-jFrame.getWidth())/2;
        int y= (int) (dimension.getHeight()-jFrame.getHeight())/2;


        jFrame.setLocation(x, y);
        jFrame.pack();
        jFrame.setVisible(true);
    }
}
