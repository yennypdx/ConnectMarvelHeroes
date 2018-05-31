package com.example.yennypurwanto.connectmarvelheros;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;


import static com.example.yennypurwanto.connectmarvelheros.R.id.board;

public class MainActivity extends AppCompatActivity {

    private BoardView boardView;
    private Gameboard gameBoard;
    public Button newgameButton;
    public TileStatus statusBP, statusSW;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameBoard = new Gameboard();
        boardView = findViewById(R.id.board);
        boardView.setMainActivity(this);

        /*int childCount = gridLayout.getChildCount();
        for(int i = 0; i < childCount; i++){
            //gridLayout.getChildAt(i)
            gridLayout.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //this is how to view each children
                    int childIdx = gridLayout.indexOfChild(v);
                    int x = childIdx / 11;
                    int y = childIdx % 11;

                    gameBoard.playGame(x, y);
                    if(gameBoard.checkWinGame(x, y)){
                        gameBoard.changePlayer();
                        Toast.makeText(getApplicationContext(), "Next player turn!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }*/

        final Button newGameButton = findViewById(R.id.newGame_button);
        newGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                newGame();
            }
        });
    }

    private void newGame() {
        gameBoard.startNewGame();
        boardView.invalidate();
    }


}
