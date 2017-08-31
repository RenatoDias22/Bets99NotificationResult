package com.example.renatodias.bets99notificationresult.model;

/**
 * Created by renatodias on 30/08/17.
 */

public class Jogo {
    private int id;
    private String nameHomeTeam;
    private String nameFgTeam;
    private String startDate;
//    private int pais;

    public Jogo(Jogo jogo) {
        this.id = jogo.id;
        this.nameHomeTeam = jogo.nameHomeTeam;
        this.nameFgTeam = jogo.nameFgTeam;
        this.startDate = jogo.startDate;
    }

    public Jogo(int id, String timeCasa, String timeFora,String dataJogo) {
        this.id = id;
        this.nameHomeTeam = timeCasa;
        this.nameFgTeam = timeFora;
        this.startDate = dataJogo;
    }

    public Jogo() {

    }

    public int getId(){
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNameHomeTeam(){
        return this.nameHomeTeam;
    }

    public String getNameFgTeam(){
        return this.nameFgTeam;
    }

    public String getStartDate(){
        return this.startDate;
    }

}
