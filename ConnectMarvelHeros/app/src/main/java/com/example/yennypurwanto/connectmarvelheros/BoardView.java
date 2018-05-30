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

public class BoardView extends View {

    private static final int BORDER_THICKNESS = 2;
    private static final int MATRIX_MARGIN = 5;
    private static final int STROKE_WIDTH = 1;
    private int width, height, matrixWidth, matrixHeight;
    private Paint gridPaint;
    private Gameboard gameBoard;
    private MainActivity mainActivity;

    Bitmap bp_img = BitmapFactory.decodeResource(getResources(), R.drawable.bphanter_img);
    Bitmap sw_img = BitmapFactory.decodeResource(getResources(), R.drawable.scarletw_img);

    public BoardView(Context context){
        super(context);
    }

    public BoardView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        gridPaint = new Paint();
        gameBoard = new Gameboard();
    }

    public BoardView(Context context, @Nullable AttributeSet attrs, int defStyleAttrs){
        super(context, attrs, defStyleAttrs);
    }

    public void setMainActivity(MainActivity main){
        mainActivity = main;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        height = View.MeasureSpec.getSize(heightMeasureSpec);
        width = View.MeasureSpec.getSize(widthMeasureSpec);
        matrixWidth = (width - BORDER_THICKNESS) / 11;
        matrixHeight = (height - BORDER_THICKNESS) / 11;

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBorders(canvas);
        drawTheImgBoard(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!gameBoard.isEndGame()  &&  event.getAction() == MotionEvent.ACTION_BUTTON_PRESS) {
            int x = (int) (event.getX() / matrixWidth);
            int y = (int) (event.getY() / matrixHeight);

            gameBoard.playGame(x, y);
            invalidate();
        }
        return super.onTouchEvent(event);
    }

    private void drawBorders(Canvas canvas) {
        for (int i = 0; i < 10; i++) {
            // vertical lines
            float left = matrixWidth * (i + 1);
            float right = left + BORDER_THICKNESS;
            float top = 0;
            float bottom = height;

            canvas.drawRect(left, top, right, bottom, gridPaint);

            // horizontal lines
            float left2 = 0;
            float right2 = width;
            float top2 = matrixHeight * (i + 1);
            float bottom2 = top2 + BORDER_THICKNESS;

            canvas.drawRect(left2, top2, right2, bottom2, gridPaint);

        }
    }

    private void drawTheImgBoard(Canvas canvas){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                drawTheMatrix(canvas, gameBoard.getTile(i, j));
            }
        }
    }

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

