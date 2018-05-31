package com.example.yennypurwanto.connectmarvelheros;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.service.quicksettings.Tile;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class BoardView extends View {

    //all cons
    private static final Random RAND = new Random();
    private static final int GRIDLINE_THICKNESS = 5;
    private static final int BETWEENCELL_MARGIN = 15;
    //all private props
    private int width, height, cellWidth, cellHeight;
    private Paint gridPaint, borderPaint;
    private Gameboard gameBoard;
    private MainActivity mainActivity;
    //images props
    Bitmap bp_img = BitmapFactory.decodeResource(getResources(), R.drawable.bphanter_img);
    Bitmap sw_img = BitmapFactory.decodeResource(getResources(), R.drawable.scarletw_img);

    public BoardView(Context context){
        super(context);
        initializeGame();
    }

    public BoardView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        initializeGame();
    }

    public BoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttrs){
        super(context, attrs, defStyleAttrs);
        initializeGame();
    }

    public void setMainActivity(MainActivity main){
        mainActivity = main;
    }
    public void setGameBoard(Gameboard game){ gameBoard = game;}

    private void initializeGame()
    {
        width = 1320;
        height = 1320;
        cellWidth = 143;
        cellHeight = 143;
        gridPaint = new Paint();
        gridPaint.setAntiAlias(true);
        borderPaint = new Paint();
        borderPaint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        height = View.MeasureSpec.getSize(heightMeasureSpec);
        width = View.MeasureSpec.getSize(widthMeasureSpec);
        cellWidth = width / 11 + 1;
        cellHeight = height / 11 + 1;

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawGrid(canvas);
        //drawTheImgBoard(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int x = (int) (event.getX() / cellWidth);
        int y = (int) (event.getY() / cellHeight);

        if(gameBoard.isEndGame())
        {
            return false;
        }

        if (event.getAction() == MotionEvent.ACTION_BUTTON_PRESS)
        {
            if(!gameBoard.validMove(x, y)){
                return false;
            }
            else
            {
                //set the coordinates
                gameBoard.matrix[x][y].getPosition().xCoord =
                        (float) cellWidth * ( x + 1) - cellWidth / 2;
                gameBoard.matrix[x][y].getPosition().yCoord =
                        (float) cellHeight * (x + 1) - cellHeight / 2;
                gameBoard.matrix[x][y].deactivateTile();

                //if the coordinates from user in within bound
                if(gameBoard.matrix[x][y].getPosition().xCoord >= 0 &&
                        gameBoard.matrix[x][y].getPosition().xCoord <=10 &&
                        gameBoard.matrix[x][y].getPosition().yCoord >= 0 &&
                        gameBoard.matrix[x][y].getPosition().yCoord <= 10)
                {
                   gameBoard.checkWinGame(x, y);
                }

                gameBoard.changePlayer();
            }

            invalidate();
        }

        return false;
    }

    private void drawGrid(Canvas canvas) {
        for (int i = 0; i < 10; i++) {
            // vertical lines
            float left = cellWidth * (i + 1);
            float right = left + GRIDLINE_THICKNESS;
            float top = 0;
            float bottom = height;

            canvas.drawRect(left, top, right, bottom, gridPaint);

            // horizontal lines
            float left2 = 0;
            float right2 = width;
            float top2 = cellHeight * (i + 1);
            float bottom2 = top2 + GRIDLINE_THICKNESS;

            canvas.drawRect(left2, top2, right2, bottom2, gridPaint);

        }
    }

    //TODO: how to draw the tiles to the screen??
    private void drawTheImgBoard(Canvas canvas){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                drawTheMatrix(canvas, gameBoard.getTile(i, j));
            }
        }
    }

    //TODO: is this necessary?
    private void drawTheMatrix(Canvas canvas, BoardTile tile){

        for(int k = 0; k < 11; k+=2){
            for(int l = 1; l < 10; l+=2){
                //drawcBP
                //bp_img.setHeight(matrixHeight);
                //bp_img.setWidth(matrixWidth);
                canvas.drawBitmap(bp_img, k, l, null);
            }
        }

        for(int m = 1; m < 10; m+=2){
            for(int n = 0; n < 11; n+=2){
                //drawSW
                //canvas.drawBitmap(sw_img, m, n, null);
            }
        }

    }

}

