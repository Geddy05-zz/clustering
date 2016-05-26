package com.schellevis;

import java.util.List;

/**
 * Created by geddy on 26/05/16.
 */
public class Result {

    public double error;
    public List<Cluster>clusters;

    public Result(double error, List<Cluster> clusters){
        this.error = error;
        this.clusters = clusters;
    }
}
