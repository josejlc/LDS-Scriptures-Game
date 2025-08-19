package com.kjawank_jose.lds_scriptures_game.service;

import com.kjawank_jose.lds_scriptures_game.model.Score;
import com.kjawank_jose.lds_scriptures_game.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaderboardService {

    @Autowired
    private ScoreRepository scoreRepository;

    public List<Score> getTop10Score(){
    return scoreRepository.findTop10ByOrderByScoreDesc();
    }
    public List<Score> getPlayerHistory(String playerName) {
    // Se necesita una logica para obtener el histrial del jugador
    // Asumiendo que el User tiene un campo username, podrias necesitasr una consulta personalizada
    // Por ahora para simplificar se asume que se pasa el ID de usuario desde el controlador
    // Para esto necesitarias unmetodo como:
    // return
scoreRepository.findByUserUsernameOrderByCompletionDateDesc(playerName);
    return null; //Aun no hay logica implementada, es un placeholder
    }
}

