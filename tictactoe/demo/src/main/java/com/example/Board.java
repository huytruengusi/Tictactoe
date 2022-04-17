package com.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Board extends JPanel {
    private static final int N = 3;
    private static final int M = 3;

    private Image imgX;
    private Image imgO;

    public Board(){
        addMouseListener(new MouseInputAdapter() {
            public void mousePressed (MouseEvent e){
                super.mousePressed(e);
                int x = e.getX();
                int y = e.getY();
                System.out.println(e.getX());
                System.out.println(e.getY());
            }
        });
        try{
            imgX = ImageIO.read(getClass().getResource("x.png"));
            imgO = ImageIO.read(getClass().getResource("o.png"));
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void paint(Graphics g){
        int w = getWidth()/3;
        int h = getHeight()/3;
        Graphics2D graphics2d = (Graphics2D) g;
        
        int k = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                int x = i*w;
                int y = j*h;
                Color color = k % 2 == 0 ? Color.BLUE : Color.RED;
                graphics2d.setColor(color); 
                graphics2d.fillRect(x, y, w, h);
                Image img  = k%2 == 0 ? imgX : imgO;
                graphics2d.drawImage(img, x, y, w, h, this);

                k++;
            }
        }
    }
    
}
