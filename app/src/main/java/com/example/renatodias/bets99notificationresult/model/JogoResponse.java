package com.example.renatodias.bets99notificationresult.model;

import java.util.ArrayList;

/**
 * Created by renatodias on 31/08/17.
 */

public class JogoResponse {
    ArrayList<Jogo> matches = new ArrayList<Jogo>();

    public ArrayList<Jogo> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Jogo> matchs) {
        this.matches = matches;
    }

    public String toString() {
        return "ResponseModel [response=" + matches + "]";
    }
}
