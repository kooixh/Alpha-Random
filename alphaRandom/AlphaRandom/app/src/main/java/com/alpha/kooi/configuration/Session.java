package com.alpha.kooi.configuration;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Kooi on 24/06/2017.
 */

public class Session implements Serializable{

    private String name;
    private List<String> lisOfOptions;


    public Session(String name,List lisOfOptions){
        this.name = name;
        this.lisOfOptions = lisOfOptions;
    }


    public List<String> getLisOfOptions() {
        return lisOfOptions;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
