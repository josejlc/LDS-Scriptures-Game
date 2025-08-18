package com.kjawank_jose.lds_scriptures_game.model;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "verse_id", nullable = false)
    private Verse verse;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String questionText;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String correctAnswer;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String optionA;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String optionB;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String optionC;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String optionD;

    @Column(nullable = false)
    private String gameMode;

    private Integer difficultyPoints = 10;

    // Constructors
    public Question() {}

    public Question(Verse verse, String questionText, String correctAnswer,
                    String optionA, String optionB, String optionC, String optionD,
                    String gameMode, Integer difficultyPoints) {
        this.verse = verse;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.gameMode = gameMode;
        this.difficultyPoints = difficultyPoints;
    }

    public boolean isCorrectAnswer(String answer) {
        return correctAnswer.equals(answer);
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Verse getVerse() { return verse; }
    public void setVerse(Verse verse) { this.verse = verse; }

    public String getQuestionText() { return questionText; }
    public void setQuestionText(String questionText) { this.questionText = questionText; }

    public String getCorrectAnswer() { return correctAnswer; }
    public void setCorrectAnswer(String correctAnswer) { this.correctAnswer = correctAnswer; }

    public String getOptionA() { return optionA; }
    public void setOptionA(String optionA) { this.optionA = optionA; }

    public String getOptionB() { return optionB; }
    public void setOptionB(String optionB) { this.optionB = optionB; }

    public String getOptionC() { return optionC; }
    public void setOptionC(String optionC) { this.optionC = optionC; }

    public String getOptionD() { return optionD; }
    public void setOptionD(String optionD) { this.optionD = optionD; }

    public String getGameMode() { return gameMode; }
    public void setGameMode(String gameMode) { this.gameMode = gameMode; }

    public Integer getDifficultyPoints() { return difficultyPoints; }
    public void setDifficultyPoints(Integer difficultyPoints) { this.difficultyPoints = difficultyPoints; }
}
