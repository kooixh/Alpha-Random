package com.alpha.kooi.configuration;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Kooi on 24/06/2017.
 */

public class Session implements Serializable{

    private String name;
    private String userTrouble;
    private List<String> lisOfOptions;


    public Session(String name,List lisOfOptions,String userTrouble){
        this.name = name;
        this.lisOfOptions = lisOfOptions;
        this.userTrouble = userTrouble;
    }


    public List<String> getLisOfOptions() {
        return lisOfOptions;
    }

    public String getName(){
        return name;
    }

    public String getUserTrouble() {
        return userTrouble;
    }

    @Override
    public String toString() {
        return getName();
    }
}
