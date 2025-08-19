package com.kjawank_jose.lds_scriptures_game.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Variables que tu servicio necesita
    private String questionText;
    private String correctAnswer;
    private String incorrectAnswer; // La variable debe ser en singular

    // Constructores
    public Question() {
    }

    public Question(String questionText, String correctAnswer, String incorrectAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswer = incorrectAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getIncorrectAnswer() {
        return incorrectAnswer;
    }

    public void setIncorrectAnswer(String incorrectAnswer) {
        this.incorrectAnswer = incorrectAnswer;
    }

    // Método para obtener las opciones en orden aleatorio
    @Transient // Esto no se guardará en la base de datos
    public List<String> getOptions() {
        List<String> options = new ArrayList<>();
        options.add(correctAnswer);
        options.add(incorrectAnswer);
        Collections.shuffle(options);
        return options;
    }
}