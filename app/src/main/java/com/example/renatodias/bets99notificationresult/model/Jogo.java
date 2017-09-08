package com.example.renatodias.bets99notificationresult.model;

/**
 * Created by renatodias on 30/08/17.
 */

public class Jogo {
    private String id;
    private String nameHomeTeam;
    private String nameFgTeam;
    private String startDate;
    private String parametre;
    private String result;

    public Jogo(Jogo jogo) {
        this.id = jogo.id;
        this.nameHomeTeam = jogo.nameHomeTeam;
        this.nameFgTeam = jogo.nameFgTeam;
        this.startDate = jogo.startDate;
    }

    public Jogo(String result, String parametre) {
        this.result = result;
        this.parametre = parametre;
    }

    public Jogo(String id, String timeCasa, String timeFora,String dataJogo) {
        this.id = id;
        this.nameHomeTeam = timeCasa;
        this.nameFgTeam = timeFora;
        this.startDate = dataJogo;
    }

    public Jogo(CharSequence text, CharSequence itemTimecasaText, CharSequence itemTimeForaText, CharSequence dataJogoText) {

    }

    public String getId(){
        return this.id;
    }

    public void setId(String id) {
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
