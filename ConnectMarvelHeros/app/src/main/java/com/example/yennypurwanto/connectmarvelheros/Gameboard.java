package com.example.yennypurwanto.connectmarvelheros;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class Gameboard {

    protected BoardTile[][] matrix;
    private boolean endGame;
    private TileStatus currentPlayer;
    TileStatus P1, P2;
    private uGraph graph;

    public Gameboard(){
        matrix = new BoardTile[11][11];
        graph = new uGraph();
        P1 = TileStatus.BP;
        P2 = TileStatus.SW;
        startNewGame();
    }

    public void startNewGame(){
        boardSetup();
        endGame = false;
    }

    public boolean isEndGame(){
        return endGame;
    }

    public void changePlayer(){
        currentPlayer = (currentPlayer == P1? P2: P1);
    }

    public BoardTile getTile(int x, int y){
        return matrix[x][y];
    }

    private void boardSetup(){

        for(int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++)
            {
                matrix[i][j] = new BoardTile();
            }
        }
        //deactivate corner tiles
        matrix[0][0].deactivateTile();
        matrix[0][10].deactivateTile();
        matrix[10][0].deactivateTile();
        matrix[10][10].deactivateTile();

        //setting up BlackPanther Tiles
        for(int i = 0; i < 11; i+=2){
            for(int j = 1; j < 10; j+=2){
                matrix[i][j].changeStatus(TileStatus.BP);
                matrix[i][j].setPosition(i, j);

                //add Vertex obj
                Vertex tempVert = new Vertex(matrix[i][j]);
                graph.insertVertex(tempVert);
            }
        }

        //setting up ScarletWitch Tiles
        for(int i = 1; i < 10; i+=2){
            for(int j = 0; j < 11; j+=2){
                matrix[i][j].changeStatus(TileStatus.SW);
                matrix[i][j].setPosition(i, j);

                //add Vertex obj
                Vertex tempVert = new Vertex(matrix[i][j]);
                graph.insertVertex(tempVert);
            }
        }
    }

    public boolean validMove(int x, int y){
        boolean valid = false;

        if(matrix[x][y].getStatus().equals(TileStatus.BLANK)){
            if(currentPlayer == P1){
                //cannot fill in col[0] and col[10]
                if(y > 0 && y < 11){
                    valid = true;
                }
            }
            if(currentPlayer == P2){
                //cannot fill in row[0] and row[10]
                if(x > 0 && x < 11){
                    valid = true;
                }
            }
        }
        return valid;
    }

    public void makeMove(int x, int y){
        if(!endGame){
            if (validMove(x, y)) {
                matrix[x][y].changeStatus(currentPlayer);

                ArrayList<Vertex> thisList = new ArrayList<>();
                try{
                    Vertex vtop = graph.getSingleVertex(x-1, y, currentPlayer);
                    thisList.add(vtop);
                } catch (Exception e){/*do nothing*/}

                try{
                    Vertex vbottom = graph.getSingleVertex(x+1, y, currentPlayer);
                    thisList.add(vbottom);
                } catch (Exception e){/*do nothing*/}

                try{
                    Vertex vleft = graph.getSingleVertex(x, y-1, currentPlayer);
                    thisList.add(vleft);
                } catch (Exception e){/*do nothing*/}

                try{
                    Vertex vright = graph.getSingleVertex(x, y+1, currentPlayer);
                    thisList.add(vright);
                } catch (Exception e){/*do nothing*/}

                //bidirectional info for alg
                thisList.get(0).insertEdge(thisList.get(1), currentPlayer);
                thisList.get(1).insertEdge(thisList.get(0), currentPlayer);

            }
        }
    }

    public boolean checkWinGame(float x, float y){
        boolean win = false;
        ArrayList<Position> startPos = new ArrayList<>();
        ArrayList<Position> endPos = new ArrayList<>();

        if(currentPlayer == P1){
            //checking winning via Line mode
            startPos.add(new Position(0,1));
            startPos.add(new Position(0,3));
            startPos.add(new Position(0,5));
            startPos.add(new Position(0,7));
            startPos.add(new Position(0,9));

            endPos.add(new Position(10,1));
            endPos.add(new Position(10,3));
            endPos.add(new Position(10,5));
            endPos.add(new Position(10,7));
            endPos.add(new Position(10,9));
        }
        else
        {
            //checking winning via Line mode
            startPos.add(new Position(1,0));
            startPos.add(new Position(3,0));
            startPos.add(new Position(5,0));
            startPos.add(new Position(7,0));
            startPos.add(new Position(9,0));

            endPos.add(new Position(1,10));
            endPos.add(new Position(3,10));
            endPos.add(new Position(5,10));
            endPos.add(new Position(7,10));
            endPos.add(new Position(9,10));
        }

        //run search
        for(Position sp: startPos){
            for (Position ep: endPos){
                if(graph.breadthfirstSearch(sp, ep)){
                    win = true;
                }

            }
        }

        //checking winning via Circular mode
        if(graph.breadthfirstSearch(new Position(x, y), new Position(x,y))){
            win = true;
        }

        return win;
    }

}
