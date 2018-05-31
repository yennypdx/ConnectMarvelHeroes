package com.example.yennypurwanto.connectmarvelheros;

public class Position {

    float xCoord, yCoord;

    public Position(){
        xCoord = 0;
        yCoord = 0;
    }

    public Position(float x, float y){
        xCoord = x;
        yCoord = y;
    }

    public float getxCoord(){
        return xCoord;
    }

    public float getyCoord(){
        return yCoord;
    }

    public void setxCoord(float x){
        xCoord = x;
    }

    public void setyCoord(float y){
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
