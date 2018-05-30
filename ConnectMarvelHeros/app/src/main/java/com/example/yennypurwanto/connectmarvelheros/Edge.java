package com.example.yennypurwanto.connectmarvelheros;

public class Edge {

    TileStatus edgeData;
    Vertex m_edgeDestination;

    public Edge(TileStatus inTile, Vertex inDest){
        edgeData = inTile;
        m_edgeDestination = inDest;
    }

    public Edge(Edge inCopy){
        edgeData = inCopy.getEdgeData();
        m_edgeDestination = inCopy.getEdgeDestination();
    }

    public boolean equals (Edge inEdge){
        boolean outEquals = false;

        if(edgeData.equals(inEdge.getEdgeData()) &&
                m_edgeDestination.equals(inEdge.getEdgeDestination()))
        {
            outEquals = true;
        }

        return outEquals;
    }

    public void setEdgeData(TileStatus inData) {
        edgeData = inData;
    }

    public void setEdgeDestination(Vertex inDestination){}

    public TileStatus getEdgeData(){
        return edgeData;
    }

    public Vertex getEdgeDestination(){
        return m_edgeDestination;
    }
}
