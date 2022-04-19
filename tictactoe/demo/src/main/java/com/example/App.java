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
        board.setEndgameListener(new EndgameListener(){
            public void end(String player, int st){
                if (st == Board.ST_WIN){
                    JOptionPane.showMessageDialog(null, "Người chơi " + player + " thắng!");
                    stopGame();
                }else if (st == Board.ST_DRAW){
                    JOptionPane.showMessageDialog(null, "Hòa rồi");
                    stopGame();
                }
            }
        });
        board.setPreferredSize(new Dimension(300,300));
        
        //Flow Layout
        FlowLayout flowLayout = new FlowLayout (FlowLayout.CENTER , 0 , 0);

        //btnStart
        btnStart = new JButton("Start");
        
        // lblTime
        lblTime = new JLabel("00:00");
        
        //Bottom panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(flowLayout);
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

        jPanel.add(board);
        jPanel.add(bottomPanel);
        
        
        // JFrame
        JFrame jFrame = new JFrame("TIC TAC TOE");
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(true);
        jFrame.add(jPanel);
        
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x= (int) (dimension.getWidth()-jFrame.getWidth())/2;
        int y= (int) (dimension.getHeight()-jFrame.getHeight())/2;
        
        jFrame.setLocation(x, y);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    private static void startGame(){
        board.reset();
        // Who go first?
        int choice = JOptionPane.showConfirmDialog(null, "Người chơi O đi trước", "Ai là người đi trước?", JOptionPane.YES_NO_OPTION);
        board.setCurrentPLayer(choice == 1 ? Cell.X_VALUE : Cell.O_VALUE);
        
        // BtnStart
        sec = 0;
        lblTime.setText("00:00");
        timer.cancel();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run(){
                sec++;
                String value = String.format("%02d:%02d", sec/60 , sec%60) ;
                lblTime.setText(value);
            }
        }, 1000, 1000);

        btnStart.setText("Stop");
    }
    private static void stopGame(){
        int choice = JOptionPane.showConfirmDialog(null,  "Bạn muốn bắt đầu lại?", "Reset", JOptionPane.YES_NO_OPTION);
        if (choice == 0 ){
            btnStart.setText("Start");
            sec = 0;
            lblTime.setText("00:00");
            timer.cancel();
            timer = new Timer();
            board.reset();
        }
    }
}
