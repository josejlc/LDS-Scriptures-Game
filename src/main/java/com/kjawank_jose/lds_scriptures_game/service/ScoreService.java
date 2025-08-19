package com.kjawank_jose.lds_scriptures_game.service;

import com.kjawank_jose.lds_scriptures_game.model.Score;
import com.kjawank_jose.lds_scriptures_game.model.User;
import com.kjawank_jose.lds_scriptures_game.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    public void saveUserScore(User user, int scoreValue) { // Recibe el objeto User
        Score score = new Score();
        score.setUser(user);
        score.setScore(scoreValue);
        score.setCompletionDate(LocalDateTime.now());

        scoreRepository.save(score);
    }
}
