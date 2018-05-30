package com.example.yennypurwanto.connectmarvelheros;

public class Position {

    int xCoord, yCoord;

    public Position(){
        xCoord = 0;
        yCoord = 0;
    }

    public Position(int x, int y){
        xCoord = x;
        yCoord = y;
    }

    public int getxCoord(){
        return xCoord;
    }

    public int getyCoord(){
        return yCoord;
    }

    public void setxCoord(int x){
        xCoord = x;
    }

    public void setyCoord(int y){
        yCoord = y;
    }

    public boolean equals(Position inPos) {
        boolean out = false;

        if(inPos.getxCoord() == xCoord && inPos.getyCoord() == yCoord){
            out = true;
        }

        return out;
    }
}
