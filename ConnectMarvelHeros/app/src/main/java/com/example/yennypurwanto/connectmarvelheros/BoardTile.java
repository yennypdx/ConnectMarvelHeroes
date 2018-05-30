package com.example.yennypurwanto.connectmarvelheros;

import android.graphics.Bitmap;

import static com.example.yennypurwanto.connectmarvelheros.TileStatus.BLANK;

enum TileStatus{BLANK, BP, SW;}

public class BoardTile {

    Bitmap heroImg;
    boolean activeTile;
    private TileStatus status;
    private Position position;

    public BoardTile(){
        status = BLANK;
        activeTile = true;
        position = new Position();
        final Bitmap pict = this.heroImg;
    }

    public TileStatus getStatus(){
        return status;
    }

    public boolean getActiveStatus(){
        return activeTile;
    }

    public void deactivateTile(){
        activeTile = false;
    }

    public void changeStatus(TileStatus inStat){
        status = inStat;
    }

    public boolean equals(BoardTile inTile) {
        boolean outEquals = false;

        if (status.equals(inTile.getStatus()) && activeTile == inTile.getActiveStatus()){
            outEquals = true;
        }
        return outEquals;
    }

    public void setPosition(int x, int y){
        position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }
}
