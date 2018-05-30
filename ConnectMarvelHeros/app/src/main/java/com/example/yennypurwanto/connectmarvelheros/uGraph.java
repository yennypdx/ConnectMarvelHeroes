package com.example.yennypurwanto.connectmarvelheros;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class uGraph {

    ArrayList<Vertex> m_adjacent;

    public uGraph(){
        m_adjacent = new ArrayList<>();
    }

    public uGraph(uGraph inCopy){
        m_adjacent = new ArrayList<>(inCopy.getMAdjacent());
    }

    void insertVertex(Vertex inData){
        m_adjacent.add(inData);
    }

    void insertEdge(Vertex fromOrigin, Vertex toDestination, TileStatus inData)
    {
        for(Vertex v: m_adjacent){
            if(v.equals(fromOrigin)){
                v.insertEdge(toDestination, inData);
            }
        }
    }

    public Vertex getStartingVert(Position inPos)
    {
        Vertex tempvert = new Vertex();

        for(Vertex v: m_adjacent){
            if(v.getVertData().getPosition().equals(inPos)){
                tempvert = v;
            }
        }

        return tempvert;
    }

    public ArrayList<Vertex> getMAdjacent() {
        return m_adjacent;
    }

    public Vertex getSingleVertex(int x, int y, TileStatus inStat){

        boolean found = false;
        Position tempPos = new Position(x,y);
        Vertex tempVert = new Vertex();

        for(Vertex v: m_adjacent){
            if(v.getVertData().getPosition().equals(tempPos) &&
                    v.getVertData().getStatus().equals(inStat)){
                tempVert = v;
                found = true;
            }
        }
        if(found == false){
            //Do not this ever again!!
            throw new IllegalArgumentException("Not found.");
        }
        return tempVert;
    }


    public boolean breadthfirstSearch(Position startPos, Position endPos)
    {
        boolean found = false;
        Queue<Vertex> queueVert = new LinkedList<>();
        Vertex startVert = getStartingVert(startPos);


        queueVert.add(startVert);
        while (found == false && !queueVert.isEmpty()) {
            //Get the next Vertex
            Vertex tempVert = queueVert.peek();

            if (tempVert.getVertData().getPosition().equals(endPos)) {
                found = true;
            }
            if (tempVert.getProcessed() == false) {
                tempVert.setProcessed(true);
                ArrayList<Edge> edges = tempVert.getVertEdges();

                while (!edges.isEmpty()) {
                    Edge tempEdge = edges.get(0);
                    queueVert.add(tempEdge.getEdgeDestination());
                    edges.remove(0);
                }
            } else {
                throw new IllegalArgumentException("Not found.");
            }
            queueVert.remove(0);
        }
        return found;
    }


}
