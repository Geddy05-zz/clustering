package com.schellevis;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.print("Start Program");

        ReadData read = new ReadData();
        ArrayList<Costumer> costumers = read.readCSV();

        Calculation cal = new Calculation();
        cal.createClusters(costumers,4);

        //TODO: Sum of squared errors

    }
}
