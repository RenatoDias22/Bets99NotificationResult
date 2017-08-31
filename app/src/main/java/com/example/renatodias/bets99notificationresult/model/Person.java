package com.example.renatodias.bets99notificationresult.model;

/**
 * Created by renatodias on 31/08/17.
 */

public class Person {

    int id;
    String resultadoJogo;


    public Person(){};

    public void setResultadoJogo(String resultadoJogo) {
        this.resultadoJogo = resultadoJogo;
    }

    public String getResultadoJogo() {
        return resultadoJogo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

