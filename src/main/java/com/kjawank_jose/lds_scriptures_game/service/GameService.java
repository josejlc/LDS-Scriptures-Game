package com.kjawank_jose.lds_scriptures_game.service;

import com.kjawank_jose.lds_scriptures_game.model.GameSession;
import com.kjawank_jose.lds_scriptures_game.model.Question;
import com.kjawank_jose.lds_scriptures_game.enums.ScriptureBookType;
import com.kjawank_jose.lds_scriptures_game.repository.GameSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GameService {

    @Autowired
    private QuestionGeneratorService questionGeneratorService;

    public GameSession createNewGameSession(String playerName) {
        GameSession session = new GameSession(playerName);
        return session;
    }

    public Question generateQuestion(ScriptureBookType bookType) {
        return questionGeneratorService.generateRandomQuestion(bookType);
    }

    public boolean processAnswer(GameSession session, Question question, String selectedAnswer) {
        boolean isCorrect = question.isCorrectAnswer(selectedAnswer);

        if (isCorrect) {
            session.correctAnswer(question.getDifficultyPoints());

            // Bonus por racha cada 5 respuestas correctas
            if (session.getCurrentStreak() % 5 == 0) {
                session.correctAnswer(25);
            }
        } else {
            session.incorrectAnswer();
        }

        return isCorrect;
    }

    public void endGameSession(GameSession session) {
        session.setSessionEnd(LocalDateTime.now());
        session.setIsActive(false);
        saveGameSession(session); //Guardar en la base de datos
    }

    public boolean canContinueGame(GameSession session) {
        return !session.isGameOver() && session.getIsActive();
    }

    @Autowired
    private GameSessionRepository gameSessionRepository;

    public GameSession saveGameSession(GameSession session) {
        return gameSessionRepository.save(session);
    }
}
