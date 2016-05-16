package com.schellevis;

import java.util.HashMap;

/**
 * Created by geddy on 15/05/16.
 */
public class Costumer {
    private HashMap<Integer,Double> items = new HashMap<>();
    private int iD;

    Costumer(int id,Double data){
        this.iD = id;
        this.updateItems(0,data);
    }

    Costumer(int id,HashMap<Integer,Double>  data){
        this.iD = id;
        this.items = data;
    }

    public void updateItems(int key, Double value){
        items.put(key,value);
    }


    public HashMap<Integer, Double> getItems() {
        return items;
    }

    public int getiD() {
        return iD;
    }
}
