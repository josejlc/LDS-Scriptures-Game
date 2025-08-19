package com.kjawank_jose.lds_scriptures_game.repository;

import com.kjawank_jose.lds_scriptures_game.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    // 1. Obtener los 10 mejores puntajes para el leaderboard global
    List<Score> findTop10ByOrderByScoreDesc();

    // 2. Obtener el historial de puntajes de un usuario específico
    List<Score> findByUserIdOrderByCompletionDateDesc(Long userId);

    // 3. Contar el número de partidas que ha jugado un usuario
    long countByUserId(Long userId);

    // 4. Obtener el puntaje más alto de un usuario
    Score findFirstByUserIdOrderByScoreDesc(Long userId);

    // 5. Obtener el historial de puntajes de un usuario
    List<Score> findByUserUsernameOrderByCompletionDateDesc(String username);
}
