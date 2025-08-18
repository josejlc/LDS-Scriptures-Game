package com.kjawank_jose.lds_scriptures_game.controller;


import com.kjawank_jose.lds_scriptures_game.model.GameSession;
import com.kjawank_jose.lds_scriptures_game.model.Question;
import com.kjawank_jose.lds_scriptures_game.repository.GameSessionRepository;
import com.kjawank_jose.lds_scriptures_game.service.GameService;
import com.kjawank_jose.lds_scriptures_game.service.VerseService;
import com.kjawank_jose.lds_scriptures_game.enums.ScriptureBookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
@RequestMapping("/")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private VerseService verseService;

    @Autowired
    private GameSessionRepository gameSessionRepository;

    @GetMapping
    public String home() {
        return "index";
    }

    @GetMapping("/game/start")
    public String showStartGame(Model model) {
        model.addAttribute("books", ScriptureBookType.values());
        return "start-game";
    }

    @PostMapping("/game/start")
    public String startGame(@RequestParam String playerName,
                            @RequestParam int bookType,
                            HttpSession session) {
        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Jugador";
        }

        GameSession gameSession = gameService.createNewGameSession(playerName);
        // GUARDAR INMEDIATAMENTE la sesión
        gameSession = gameService.saveGameSession(gameSession);

        ScriptureBookType selectedBook = ScriptureBookType.getByCode(bookType);

        session.setAttribute("gameSession", gameSession);
        session.setAttribute("selectedBook", selectedBook);

        return "redirect:/game/question";
    }

    @GetMapping("/game/question")
    public String showQuestion(HttpSession session, Model model) {
        GameSession gameSession = (GameSession) session.getAttribute("gameSession");
        ScriptureBookType selectedBook = (ScriptureBookType) session.getAttribute("selectedBook");

        if (gameSession == null) {
            return "redirect:/game/start";
        }

        if (!gameService.canContinueGame(gameSession)) {
            return "redirect:/game/results";
        }

        Question question = gameService.generateQuestion(selectedBook);
        if (question == null) {
            model.addAttribute("error", "No se pudo generar una pregunta");
            return "error";
        }

        session.setAttribute("currentQuestion", question);

        model.addAttribute("question", question);
        model.addAttribute("gameSession", gameSession);
        model.addAttribute("selectedBook", selectedBook);

        return "question";
    }

    @PostMapping("/game/answer")
    public String processAnswer(@RequestParam String selectedAnswer,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        GameSession gameSession = (GameSession) session.getAttribute("gameSession");
        Question currentQuestion = (Question) session.getAttribute("currentQuestion");

        if (gameSession == null || currentQuestion == null) {
            return "redirect:/game/start";
        }

        boolean isCorrect = gameService.processAnswer(gameSession, currentQuestion, selectedAnswer);

        // GUARDAR después de cada respuesta
        gameSession = gameService.saveGameSession(gameSession);

        redirectAttributes.addFlashAttribute("isCorrect", isCorrect);
        redirectAttributes.addFlashAttribute("correctAnswer", currentQuestion.getCorrectAnswer());
        redirectAttributes.addFlashAttribute("verse", currentQuestion.getVerse());

        session.setAttribute("gameSession", gameSession);

        return "redirect:/game/feedback";
    }

    @GetMapping("/game/feedback")
    public String showFeedback(Model model, HttpSession session) {
        GameSession gameSession = (GameSession) session.getAttribute("gameSession");

        if (gameSession == null) {
            return "redirect:/game/start";
        }

        model.addAttribute("gameSession", gameSession);
        return "feedback";
    }

    @PostMapping("/game/continue")
    public String continueGame(HttpSession session) {
        GameSession gameSession = (GameSession) session.getAttribute("gameSession");

        if (gameSession == null || !gameService.canContinueGame(gameSession)) {
            return "redirect:/game/results";
        }

        return "redirect:/game/question";
    }

    @GetMapping("/game/results")
    public String showResults(HttpSession session, Model model) {
        GameSession gameSession = (GameSession) session.getAttribute("gameSession");

        if (gameSession != null) {
            gameService.endGameSession(gameSession);
            model.addAttribute("gameSession", gameSession);
        }

        return "results";
    }

    @PostMapping("/game/restart")
    public String restartGame(HttpSession session) {
        session.removeAttribute("gameSession");
        session.removeAttribute("currentQuestion");
        session.removeAttribute("selectedBook");
        return "redirect:/game/start";
    }

    @GetMapping("/h2-console")
    public String h2Console() {
        return "redirect:/h2-console/";
    }

    @GetMapping("/leaderboard")
    public String showLeaderboard(Model model) {
        // Obtener top 10 puntuaciones
        List<GameSession> topScores = gameSessionRepository.findTop10ByIsActiveFalseOrderByScoreDesc();
        model.addAttribute("topScores", topScores);
        return "leaderboard";
    }

    @GetMapping("/history")
    public String showHistory(@RequestParam(required = false) String playerName, Model model) {
        List<GameSession> history;

        if (playerName != null && !playerName.trim().isEmpty()) {
            history = gameSessionRepository.findPlayerHistory(playerName);
            model.addAttribute("searchedPlayer", playerName);
        } else {
            history = gameSessionRepository.findTop20ByIsActiveFalseOrderBySessionStartDesc();
        }

        model.addAttribute("gameHistory", history);
        return "history";
    }
}