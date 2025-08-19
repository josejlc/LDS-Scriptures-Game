package com.kjawank_jose.lds_scriptures_game.service;

import com.kjawank_jose.lds_scriptures_game.enums.ScriptureBookType;
import com.kjawank_jose.lds_scriptures_game.model.GameSession;
import com.kjawank_jose.lds_scriptures_game.model.Question;
import com.kjawank_jose.lds_scriptures_game.repository.GameSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    private VerseService verseService;

    @Autowired
    private GameSessionRepository gameSessionRepository;

    public GameSession createNewGameSession(String playerName, ScriptureBookType bookType) {
        GameSession newSession = new GameSession();
        newSession.setPlayerName(playerName);
        newSession.setBookType(bookType);
        newSession.setScore(0);
        newSession.setCurrentStreak(0);
        newSession.setLongestStreak(0);
        newSession.setCorrectAnswers(0);
        newSession.setIncorrectAnswers(0);
        newSession.setIsActive(true);
        return newSession;
    }

    public boolean canContinueGame(GameSession session) {
        return session.getIsActive();
    }

    public Question generateQuestion(ScriptureBookType bookType) {
        // En esta versión simplificada, la pregunta es fija.
        // La lógica para generar preguntas a partir de versos reales
        // se puede añadir más adelante.
        return new Question("¿Cuál es el libro de Mormón?", "El Libro de Mormón", "La Biblia");
    }

    public boolean processAnswer(GameSession session, Question question, String selectedAnswer) {
        boolean isCorrect = selectedAnswer.equals(question.getCorrectAnswer());

        if (isCorrect) {
            session.correctAnswer(10);
        } else {
            session.incorrectAnswer();
        }

        gameSessionRepository.save(session);
        return isCorrect;
    }

    public void endGameSession(GameSession session) {
        session.setIsActive(false);
        gameSessionRepository.save(session);
    }
}