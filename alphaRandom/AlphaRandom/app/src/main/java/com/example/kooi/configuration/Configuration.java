package com.example.kooi.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kooi on 24/06/2017.
 */

public class Configuration implements Serializable {

    //List containing saved sessions
    private List<Session> listOfSessions = new ArrayList<Session>();

    public Configuration(){

    }


    public List<Session> getListOfSessions() {
        return listOfSessions;
    }


    /**
     * Method to add  session into the list of saved session
     *
     * @param s session to be added to the list
     */
    public void addSession(Session s){
        listOfSessions.add(s);
    }
}
