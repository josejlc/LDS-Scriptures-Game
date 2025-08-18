package com.kjawank_jose.lds_scriptures_game.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "game_sessions")
public class GameSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String playerName;
    private Integer score = 0;
    private Integer levelReached = 1;
    private Integer livesRemaining = 3;
    private Integer currentStreak = 0;
    private Integer bestStreak = 0;
    private Integer correctAnswers = 0;
    private Integer incorrectAnswers = 0;
    private LocalDateTime sessionStart = LocalDateTime.now();
    private LocalDateTime sessionEnd;
    private Boolean isActive = true;

    // Constructors
    public GameSession() {}

    public GameSession(String playerName) {
        this.playerName = playerName;
    }

    // Business methods
    public void correctAnswer(int points) {
        this.score += points;
        this.currentStreak++;
        this.levelReached++;
        this.correctAnswers++;
        this.bestStreak = Math.max(this.currentStreak, this.bestStreak);
    }

    public void incorrectAnswer() {
        this.livesRemaining--;
        this.currentStreak = 0;
        this.incorrectAnswers++;
    }

    public boolean isGameOver() {
        return livesRemaining <= 0;
    }

    public double getAccuracyPercentage() {
        int total = correctAnswers + incorrectAnswers;
        return total > 0 ? (double) correctAnswers / total * 100 : 0;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlayerName() { return playerName; }
    public void setPlayerName(String playerName) { this.playerName = playerName; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public Integer getLevelReached() { return levelReached; }
    public void setLevelReached(Integer levelReached) { this.levelReached = levelReached; }

    public Integer getLivesRemaining() { return livesRemaining; }
    public void setLivesRemaining(Integer livesRemaining) { this.livesRemaining = livesRemaining; }

    public Integer getCurrentStreak() { return currentStreak; }
    public void setCurrentStreak(Integer currentStreak) { this.currentStreak = currentStreak; }

    public Integer getBestStreak() { return bestStreak; }
    public void setBestStreak(Integer bestStreak) { this.bestStreak = bestStreak; }

    public Integer getCorrectAnswers() { return correctAnswers; }
    public void setCorrectAnswers(Integer correctAnswers) { this.correctAnswers = correctAnswers; }

    public Integer getIncorrectAnswers() { return incorrectAnswers; }
    public void setIncorrectAnswers(Integer incorrectAnswers) { this.incorrectAnswers = incorrectAnswers; }

    public LocalDateTime getSessionStart() { return sessionStart; }
    public void setSessionStart(LocalDateTime sessionStart) { this.sessionStart = sessionStart; }

    public LocalDateTime getSessionEnd() { return sessionEnd; }
    public void setSessionEnd(LocalDateTime sessionEnd) { this.sessionEnd = sessionEnd; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}