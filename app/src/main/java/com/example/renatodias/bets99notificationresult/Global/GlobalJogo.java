package com.example.renatodias.bets99notificationresult.Global;

/**
 * Created by renatodias on 31/08/17.
 */

import android.app.Application;


import com.example.renatodias.bets99notificationresult.model.Jogo;

import java.util.ArrayList;
import java.util.List;

public class GlobalJogo {

    public static List<Jogo> resultadosPendentes = new ArrayList<>();

    public static List<Jogo> getJogadoresSelecionados() {
        return resultadosPendentes;
    }
}
