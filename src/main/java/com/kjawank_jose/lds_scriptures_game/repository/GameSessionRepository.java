package com.kjawank_jose.lds_scriptures_game.repository;

import com.kjawank_jose.lds_scriptures_game.model.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameSessionRepository extends JpaRepository<GameSession, Long> {

    List<GameSession> findByPlayerNameOrderByScoreDesc(String playerName);

    List<GameSession> findByIsActiveTrue();

    @Query("SELECT g FROM GameSession g WHERE g.isActive = false ORDER BY g.score DESC")
    List<GameSession> findTopScores();

    @Query("SELECT g FROM GameSession g WHERE g.playerName = :playerName AND g.isActive = false ORDER BY g.score DESC")
    List<GameSession> findPlayerHistory(@Param("playerName") String playerName);

    @Query(value = "SELECT * FROM game_sessions WHERE is_active = false ORDER BY score DESC LIMIT 10", nativeQuery = true)
    List<GameSession> findTop10ByIsActiveFalseOrderByScoreDesc();

    @Query(value = "SELECT * FROM game_sessions WHERE is_active = false ORDER BY session_start DESC LIMIT 20", nativeQuery = true)
    List<GameSession> findTop20ByIsActiveFalseOrderBySessionStartDesc();
}



