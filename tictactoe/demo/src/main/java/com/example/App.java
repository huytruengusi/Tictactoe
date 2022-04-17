package com.example;

import javax.swing.*;
import java.awt.*;

public class App 
{
    public static void main( String[] args )
    {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

        Board board = new Board();

        JFrame jFrame = new JFrame("TIC TAC TOE");
        jFrame.setSize(600, 600);
        int x= (int) (dimension.getWidth()-jFrame.getWidth())/2;
        int y= (int) (dimension.getHeight()-jFrame.getHeight())/2;
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.add(board);

        jFrame.setLocation(x, y);
        jFrame.setVisible(true);
    }
}
