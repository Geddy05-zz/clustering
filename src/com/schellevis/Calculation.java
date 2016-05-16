package com.schellevis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by geddy on 16/05/16.
 */
public class Calculation {

    private ArrayList<Costumer> centroids = new ArrayList<>();
    private ArrayList<Cluster> clusters = new ArrayList<>();


    public void createClusters(ArrayList<Costumer> costumers, int numberOfClusters){
        Random rand = new Random();
        for(int i = 0; i < numberOfClusters; i++){
            int clusterNR = rand.nextInt(costumers.size());
            centroids.add(costumers.get(clusterNR));
            clusters.add(new Cluster(i,costumers.get(clusterNR)));
        }
        for (Costumer c: costumers){
            calculateDistance(c);
        }

        for(int i = 0; i < 100;i++){
            for(Cluster c : clusters){
                c.recalculateCenter();
                c.emptyPoints();

                for (Costumer cos: costumers){
                    calculateDistance(cos);
                }
            }
        }

        for (Cluster c: clusters){
            System.out.println(" Cluster id " + c.getClusterNR());
            ArrayList<Costumer> cs = c.getPoints();
            for (Costumer cos : cs){
                System.out.print(cos.getiD()+ ", ");
            }
            System.out.println("");
        }
    }

    public void calculateDistance(Costumer costumer){
        double distance = 0;
        int clusterNR = 0;
        int counter = 0;
        for (Costumer centroid : centroids) {
                HashMap<Integer, Double> centroidDem = centroid.getItems();
                HashMap<Integer, Double> costumerDem = costumer.getItems();
                Double sum = 0.0;
                for (Map.Entry<Integer, Double> entry : centroidDem.entrySet()) {
                    int key = entry.getKey();
                    double value = entry.getValue();
                    double dist;

                    if (costumerDem.containsKey(key)) {
                        dist = costumerDem.get(key);
                        sum += Math.pow((value - dist), 2);
                    }
                }
                double tempDistance = 1 / (1 + ((float) Math.sqrt(sum)));
                if (tempDistance > distance) {
                    distance = tempDistance;
                    clusterNR = counter;
                }
            counter++;
        }
        if(!clusters.get(clusterNR).getPoints().contains(costumer)){
            clusters.get(clusterNR).addPoint(costumer);
        }

    }
}
