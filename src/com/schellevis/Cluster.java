package com.schellevis;

import java.util.*;
import java.util.concurrent.SynchronousQueue;

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
            if(sum > 0) {
                centerCluster.updateItems(item.getKey(), (sum / numberOfPoints));
            }else{
                centerCluster.updateItems(item.getKey(), 0.0);
            }
        }
    }

    public double sumOfError(){
        double sum = 0;
        for(Map.Entry<Integer, Double> item : centerCluster.getItems().entrySet()){
            for (Costumer costumer : points) {
                double valueCostumer = costumer.getItems().get(item.getKey());
                sum += Math.pow((item.getValue() - valueCostumer), 2);
            }
        }
        return sum;
    }

    public Map mostSoldItems(){
        HashMap<Integer,Integer> items = new HashMap<>();
        int totaal = 0;
        for(Map.Entry<Integer, Double> item : centerCluster.getItems().entrySet()) {
            int totaalSold = 0;

            for (Costumer costumer: points){
                totaalSold  += costumer.getItems().get(item.getKey()).intValue();
            }
            totaal += totaalSold;
//            System.out.println("Item: "+item.getKey()+ " Amount: "+totaalSold);
            items.put(item.getKey(),totaalSold);
        }

//        for(Map.Entry<Integer, Integer> item : items.entrySet()) {
//            System.out.println("Item: "+item.getKey() + " value " + item.getValue() );
//        }

//        Map<Integer ,Integer> sortedMap = new TreeMap(new ValueComparator(items));
//        sortedMap.putAll(items);
        System.out.println(totaal);
        return items;
    }
}

class ValueComparator implements Comparator {
    Map map;

    public ValueComparator(Map map) {
        this.map = map;
    }

    public int compare(Object keyA, Object keyB) {
        Comparable valueA = (Comparable) map.get(keyA);
        Comparable valueB = (Comparable) map.get(keyB);
        return valueB.compareTo(valueA);
    }
}
