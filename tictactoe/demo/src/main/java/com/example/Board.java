package com.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Board extends JPanel {
    private static final int N = 3;
    private static final int M = 3;
    public static final int ST_DRAW = 0;
    public static final int ST_WIN = 1;
    public static final int ST_NORMAL = 2;
    private Image imgX;
    private Image imgO;
    private Cell[][] matrix = new Cell[N][M];
    private String currentPLayer = Cell.EMPTY_VALUE;
    private EndgameListener endgameListener;
    //Constructor
    public Board(String currentPLayer){
        this();
        this.currentPLayer = currentPLayer;
    }
    public Board(){       
        this.initMatrix();
        int k=0;
        addMouseListener(new MouseInputAdapter() {
            public void mousePressed (MouseEvent e){
                super.mousePressed(e);
                int x = e.getX();
                int y = e.getY();
                if (currentPLayer.equals(Cell.EMPTY_VALUE)){
                    return;
                }
                soundClick();
                for (int i=0; i<N; i++){
                    for (int j=0; j<M; j++){
                        Cell cell = matrix[i][j];
                        int cXStart = cell.getX();
                        int cYStart = cell.getY();
                        
                        int cXEnd = cXStart + cell.getW();
                        int cYEnd = cYStart + cell.getH();
                        if (x >= cXStart && x <= cXEnd && y >= cYStart && y <= cYEnd){
                            if (cell.getValue().equals(Cell.EMPTY_VALUE)){
                                cell.setValue(currentPLayer);
                                repaint();
                                int result = checkWin(currentPLayer);
                                if (endgameListener != null)
                                    endgameListener.end(currentPLayer, result);
                            
                                if (result == ST_NORMAL)
                                    currentPLayer = currentPLayer.equals(Cell.O_VALUE) ? Cell.X_VALUE : Cell.O_VALUE;
                            }
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

    private synchronized  void soundClick(){
        Thread thread = new Thread(new Runnable() {
            public void run (){
                try{
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource("click.wav"));
                    clip.open(audioInputStream);
                    clip.start();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
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
                if (cell.getValue().equals(Cell.X_VALUE)){
                    Image img =imgX;
                    graphics2d.drawImage(img, x, y, w, h, this);
                }else if (cell.getValue().equals(Cell.O_VALUE)){
                    Image img =imgO;
                    graphics2d.drawImage(img, x, y, w, h, this);
                }
                k++;
            }
        }
    }
    public void setCurrentPLayer(String currentPLayer) {
        this.currentPLayer = currentPLayer;
    }

    public void setEndgameListener(EndgameListener endgameListener) {
        this.endgameListener = endgameListener;
    }
    
    public void reset(){
        this.initMatrix();
        this.setCurrentPLayer(Cell.EMPTY_VALUE);
        repaint();
    }
    public int checkWin (String player){
        //Crossover
        if (this.matrix[0][0].getValue().equals(player) && this.matrix[1][1].getValue().equals(player) && this.matrix[2][2].getValue().equals(player))
            return ST_WIN;

            //Crossup
        if (this.matrix[2][0].getValue().equals(player) && this.matrix[1][1].getValue().equals(player) && this.matrix[0][2].getValue().equals(player))
            return ST_WIN;
        
            //Row 0
        if (this.matrix[0][0].getValue().equals(player) && this.matrix[0][1].getValue().equals(player) && this.matrix[0][2].getValue().equals(player))
            return ST_WIN;
        
            //Row 1
        if (this.matrix[1][0].getValue().equals(player) && this.matrix[1][1].getValue().equals(player) && this.matrix[1][2].getValue().equals(player))
            return ST_WIN;
        
            //Row 2
        if (this.matrix[2][0].getValue().equals(player) && this.matrix[2][1].getValue().equals(player) && this.matrix[2][2].getValue().equals(player))
            return ST_WIN;
        
            //Columns 0
        if (this.matrix[0][0].getValue().equals(player) && this.matrix[1][0].getValue().equals(player) && this.matrix[2][0].getValue().equals(player))
            return ST_WIN;
        
            //Columns 1
        if (this.matrix[0][1].getValue().equals(player) && this.matrix[1][1].getValue().equals(player) && this.matrix[2][1].getValue().equals(player))
            return ST_WIN;
        
            //Columns 2
        if (this.matrix[0][2].getValue().equals(player) && this.matrix[1][2].getValue().equals(player) && this.matrix[2][2].getValue().equals(player))
            return ST_WIN;
        
            //CheckTable
        if (this.isFull())
            return ST_DRAW;
        
        return ST_NORMAL;
    }
    private boolean isFull(){
        int number = N * M;
        int k=0; 
        for (int i=0; i<N; i++){
            for (int j=0; j<M; j++){
                Cell cell = matrix[i][j];
                if(!cell.getValue().equals(Cell.EMPTY_VALUE)){
                    k++;
                }
            }
        }
        return k==N*M;
    }
}
