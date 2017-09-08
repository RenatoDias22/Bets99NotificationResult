package com.example.renatodias.bets99notificationresult.model;

/**
 * Created by renatodias on 31/08/17.
 */

public class Person {

    String id;
    String resultadoJogo;


    public Person(){};

    public void setResultadoJogo(String resultadoJogo) {
        this.resultadoJogo = resultadoJogo;
    }

    public String getResultadoJogo() {
        return resultadoJogo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}

