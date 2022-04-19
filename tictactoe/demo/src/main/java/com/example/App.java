package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class App 
{
    private static int sec = 0;
    private static JLabel lblTime;
    private static Timer timer = new Timer();
    private static JButton btnStart;
    private static Board board;
    public static void main( String[] args )
    {
        //jPanel
        JPanel jPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(jPanel, BoxLayout.Y_AXIS);
        jPanel.setLayout(boxLayout);
        
        //Board
        board = new Board();
        board.setPreferredSize(new Dimension(300,300));
        
        //Flow Layout
        FlowLayout flowLayout = new FlowLayout (FlowLayout.CENTER , 0 , 0);

        //Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(flowLayout);

        //btnStart
        btnStart = new JButton("Start");
        
        // lblTime
        lblTime = new JLabel("00:00");
        
        bottomPanel.add(lblTime);
        bottomPanel.add(btnStart);
        btnStart.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
                if (btnStart.getText().equals("Start")){
                    startGame();
                }else{
                    stopGame();
                }
            }
        });
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
    private static void startGame(){
        // Yes or No Board
        int choice = JOptionPane.showConfirmDialog(null, "Người chơi O đi trước", "Ai là người đi trước?", JOptionPane.YES_NO_OPTION);
        Board board = new Board(choice == 1 ? Cell.X_VALUE : Cell.O_VALUE);
        // BtnStar

        lblTime.setText("00:00");
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run(){
                sec++;
                String value = sec/60 + " : " + sec%60 ;
                lblTime.setText(value);
            }
        }, 1000, 1000);
    }
    private static void stopGame(){

    }
}
