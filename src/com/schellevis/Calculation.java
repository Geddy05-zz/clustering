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
    private ArrayList<Cluster> clusters;
    private Random rand = new Random();
    private Result result;


    public void createClusters(ArrayList<Costumer> costumers, int numberOfClusters){
        for (int numberOfTry = 0; numberOfTry <= 30; numberOfTry++){
            clusters = new ArrayList<>();
            centroids = new ArrayList<>();
            for (int i = 0; i < numberOfClusters; i++) {
                int clusterNR = rand.nextInt(costumers.size());
                HashMap<Integer, Double> tempValues =  new HashMap<>(costumers.get(clusterNR).getItems());
                Costumer cost = new Costumer(clusterNR+50,tempValues);
                centroids.add(cost);
                clusters.add(new Cluster(i, cost));
            }
            for (Costumer c : costumers) {
                calculateDistance(c,numberOfClusters);
            }

            for (int i = 0; i < 20; i++) {
                int counter = 0;
                for (Cluster c : clusters) {
                    c.recalculateCenter();
                    c.emptyPoints();
                    clusters.set(counter,c);
                    counter++;
                }
                for (Cluster c : clusters) {
                    for (Costumer cos : costumers) {
                        calculateDistance(cos,numberOfClusters);
                    }
//                    clusters.set(counter,c);
//                    counter++;
                }
            }

            double error = 0;
            for (Cluster c : clusters){
                error += c.sumOfError();
            }

            if(Double.isNaN(error)){
                continue;
            }

            if(result != null){
                if (error < result.error){
                    result = new Result(error,clusters);
                }
            }else{
                result = new Result(error,clusters);
            }
        }
        printResults(result);
    }

    public void calculateDistance(Costumer costumer, int numberOfClusters){
        double distance = 0;
        int clusterNumber = 0;
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
                double tempDistance = Math.sqrt(sum);
                if (tempDistance < distance || distance == 0) {
                    distance = tempDistance;
                    clusterNumber = counter;
                }
            counter++;
        }

        if(!clusters.get(clusterNumber).getPoints().contains(costumer)) {
            Cluster cluster = clusters.get(clusterNumber);
            cluster.addPoint(costumer);
            clusters.set(clusterNumber,cluster);
        }
    }

    private void printResults(Result result){
        for (Cluster c : result.clusters) {
            Map<Integer,Integer> map = c.mostSoldItems();
//            System.out.println(" Cluster id " + c.getClusterNR());
//            for(Map.Entry<Integer ,Integer> entry : map.entrySet()){
//                System.out.println(" Item Number: "+entry.getKey()+" Times: "+entry.getValue());
//            }

            System.out.println(" Cluster id " + c.getClusterNR());
            ArrayList<Costumer> cs = c.getPoints();
            for (Costumer cos : cs) {
                System.out.print(cos.getiD() + ", ");
            }
            System.out.println("");
        }
        System.out.println(result.error);
    }
}
