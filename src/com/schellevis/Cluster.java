package com.schellevis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by geddy on 13/05/16.
 */
public class Cluster {
    private int clusterNR;
    private ArrayList<Costumer> points = new ArrayList<>();
    public Costumer centerCluster;

    public Cluster(int nr, Costumer point){
        this.clusterNR = nr;
        centerCluster = new Costumer(point.getiD(),point.getItems());
    }

    public void addPoint(Costumer point){
        points.add(point);
    }

    public void emptyPoints(){
        points = new ArrayList<>();
    }

    public ArrayList<Costumer> getPoints() {
        return points;
    }

    public int getClusterNR() {
        return clusterNR;
    }

    public void recalculateCenter(){
        int numberOfPoints = points.size();
        for(Map.Entry<Integer, Double> item : centerCluster.getItems().entrySet()){
            double sum = 0;
            for (Costumer costumer : points){
                sum += costumer.getItems().get(item.getKey());
            }
            centerCluster.updateItems(item.getKey(),(sum / numberOfPoints));
        }
    }
}
