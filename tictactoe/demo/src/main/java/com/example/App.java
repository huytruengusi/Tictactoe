package com.example;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
public class App 
{
    private static int sec = 0;
    public static void main( String[] args )
    {
        //Time
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run(){
                sec++;
                System.out.println(sec/60 + " : " + sec%60);
            }
        }, 1000, 1000);
        // timer.cancel();
        // Yes or No Board
        int choice = JOptionPane.showConfirmDialog(null, "Người chơi O đi trước", "Ai là người đi trước?", JOptionPane.YES_NO_OPTION);
        Board board = new Board(choice == 1 ? Cell.X_VALUE : Cell.O_VALUE);
        //jPanel
        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        
        //Board
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
