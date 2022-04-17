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
    private Cell[][] matrix = new Cell[N][M];

    public Board(){       
        this.initMatrix();
        int k=0;
        addMouseListener(new MouseInputAdapter() {
            public void mousePressed (MouseEvent e){
                super.mousePressed(e);
                int x = e.getX();
                int y = e.getY();
                for (int i=0; i<N; i++){
                    for (int j=0; j<M; j++){
                        Cell cell = matrix[i][j];
                        int cXStart = cell.getX();
                        int cYStart = cell.getY();
                        
                        int cXEnd = cXStart + cell.getW();
                        int cYEnd = cYStart + cell.getH();
                        if (x >= cXStart && x <= cXEnd && y >= cYStart && y <= cYEnd){
                            System.out.println("Click vao i: " + i + " j: " + j);
                        }
                    }
                }
            }
        });
        try{
            imgX = ImageIO.read(getClass().getResource("x.png"));
            imgO = ImageIO.read(getClass().getResource("o.png"));
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public void initMatrix(){
        for (int i=0; i<N; i++){
            for (int j=0; j<M; j++){
                Cell cell = new Cell();
                matrix[i][j] = cell;
            }
        }
    }

    public void paint(Graphics g){
        int w = getWidth()/3;
        int h = getHeight()/3;
        Graphics2D graphics2d = (Graphics2D) g;
        
        int k = 0;
        for (int i = 0; i < N; i++){
            for (int j = 0; j < M; j++){
                int x = j*w;
                int y = i*h;

                Cell cell = matrix[i][j];
                cell.setX(x);
                cell.setY(y);
                cell.setW(w);
                cell.setH(h);
                Color color = k % 2 == 0 ? Color.BLUE : Color.RED;
                graphics2d.setColor(color); 
                graphics2d.fillRect(x, y, w, h);
                k++;
            }
        }
    }
    
}
