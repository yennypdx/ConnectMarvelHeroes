package com.example.yennypurwanto.connectmarvelheros;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    BoardTile m_vertData;
    boolean processed;
    ArrayList<Edge> m_vertEdges;
    private int x, y;

    public Vertex(){
        m_vertData = new BoardTile();
        processed = false;
        m_vertEdges = new ArrayList<Edge>();
    }

    public Vertex(BoardTile inVertData)
    {
        m_vertData = inVertData;
        processed = false;
        m_vertEdges = new ArrayList<Edge>();
    }

    public Vertex(Vertex inCopy){
        m_vertData = inCopy.getVertData();
        processed = inCopy.processed;
        m_vertEdges = inCopy.getVertEdges();
    }

    public boolean equals (Vertex inVert){
        boolean outEquals = false;

        if(m_vertData.equals(inVert.getVertData()))
        {
            outEquals = true;
        }

        return outEquals;
    }

    public void setVertData(BoardTile inData){
        m_vertData = inData;
    }

    public void setVertEdges(ArrayList<Edge> inVertEdges){
        m_vertEdges = new ArrayList<>(inVertEdges);
    }

    public void setProcessed(boolean inProcessed){
        processed = inProcessed;
    }

    public BoardTile getVertData() {
        return m_vertData;
    }

    public boolean getProcessed() { return processed; }


    public ArrayList<Edge> getVertEdges() {
        return m_vertEdges;
    }

    public void insertEdge(Vertex toDestination, TileStatus inData){
        Edge tempEdge = new Edge(inData, toDestination);
        m_vertEdges.add(tempEdge);
    }

}
