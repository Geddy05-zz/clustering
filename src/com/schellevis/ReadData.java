package com.schellevis;

import java.io.FileReader;
import java.util.*;

/**
 * Created by geddy on 15/05/16.
 */
public class ReadData {

    public ArrayList<Costumer>  readCSV() {
        Random rand = new Random();
        ArrayList<Costumer> costumers = new ArrayList();
        try {
            final Scanner data = new Scanner(new FileReader("WineData.csv"));
            int item = 0;
            while (data.hasNext()) {
                final String[] columns = data.nextLine().split(",");
                for (int i = 0; i < columns.length; i++){
                    if( item == 0){
                        Costumer c = new Costumer(i, Double.parseDouble(columns[i]));
                        costumers.add(c);
                    }else{
                        Costumer c = costumers.get(i);
                        c.updateItems(item, Double.parseDouble(columns[i]));
                    }
                }
                item++;
            }
        }

        catch(Exception e) {
        }
        return costumers;
    }

}
