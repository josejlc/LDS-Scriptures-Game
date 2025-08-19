package com.kjawank_jose.lds_scriptures_game.model;

import com.kjawank_jose.lds_scriptures_game.enums.ScriptureBookType;
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
    private Integer correctAnswers = 0;
    private Integer incorrectAnswers = 0;
    private Integer currentStreak = 0;
    private Integer longestStreak = 0;
    private LocalDateTime sessionStart = LocalDateTime.now();
    private LocalDateTime sessionEnd;
    private Boolean isActive = true;

    @Enumerated(EnumType.STRING)
    private ScriptureBookType bookType;

    // Métodos de negocio
    public void correctAnswer(int points) {
        this.score += points;
        this.correctAnswers++;
        this.currentStreak++;
        if (this.currentStreak > this.longestStreak) {
            this.longestStreak = this.currentStreak;
        }
    }

    public void incorrectAnswer() {
        this.incorrectAnswers++;
        this.currentStreak = 0;
    }

    public boolean isGameOver() {
        // Podrías basar esto en el número de preguntas si hay un límite
        // O en una variable como vidas, si la implementas.
        return false; // Este método debe implementarse con la lógica del juego.
    }

    public void endGame() {
        this.isActive = false;
        this.sessionEnd = LocalDateTime.now();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(Integer correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    public Integer getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(Integer incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public Integer getCurrentStreak() {
        return currentStreak;
    }

    public void setCurrentStreak(Integer currentStreak) {
        this.currentStreak = currentStreak;
    }

    public Integer getLongestStreak() {
        return longestStreak;
    }

    public void setLongestStreak(Integer longestStreak) {
        this.longestStreak = longestStreak;
    }

    public LocalDateTime getSessionStart() {
        return sessionStart;
    }

    public void setSessionStart(LocalDateTime sessionStart) {
        this.sessionStart = sessionStart;
    }

    public LocalDateTime getSessionEnd() {
        return sessionEnd;
    }

    public void setSessionEnd(LocalDateTime sessionEnd) {
        this.sessionEnd = sessionEnd;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public ScriptureBookType getBookType() {
        return bookType;
    }

    public void setBookType(ScriptureBookType bookType) {
        this.bookType = bookType;
    }
}