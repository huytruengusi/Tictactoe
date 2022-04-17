package com.example;

public class Cell {
    private int x;
    private int y;
    private int w;
    private int h;
    private String value;
    
    public static final String X_VALUE = "X";
    public static final String O_VALUE = "O";
    public static final String EMPTY_VALUE = "";


    public Cell(){
        this.value = "";
    }
    public Cell(int x, int y, int w, int h){
        this();
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
    }
    public Cell(int x, int y, int w, int h, String value){
        this(x,y,w,h);
        this.value=value;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public void setW(int w) {
        this.w = w;
    }
    public void setH(int h) {
        this.h = h;
    }
    public int getX(){
        return this.x;
    }
    
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public int getY(){
        return this.y;
    }
    
    public int getW(){
        return this.w;
    }

    public int getH(){
        return this.h;
    }
}
