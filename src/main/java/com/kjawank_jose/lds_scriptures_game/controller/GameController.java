package com.kjawank_jose.lds_scriptures_game.controller;

import com.kjawank_jose.lds_scriptures_game.model.GameSession;
import com.kjawank_jose.lds_scriptures_game.model.Question;
import com.kjawank_jose.lds_scriptures_game.enums.ScriptureBookType;
import com.kjawank_jose.lds_scriptures_game.repository.GameSessionRepository;
import com.kjawank_jose.lds_scriptures_game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private GameSessionRepository gameSessionRepository;

    // --- Flujo del Juego ---

    // 1. Página de inicio del juego
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // 2. Inicia una nueva sesión de juego
    @PostMapping("/game/start")
    public String startGame(@RequestParam String playerName,
                            @RequestParam ScriptureBookType bookType,
                            HttpSession session) {
        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Jugador";
        }
        GameSession gameSession = gameService.createNewGameSession(playerName, bookType);
        gameSessionRepository.save(gameSession);
        session.setAttribute("gameSession", gameSession);

        return "redirect:/game/question";
    }

    // 3. Muestra una pregunta del juego
    @GetMapping("/game/question")
    public String showQuestion(HttpSession session, Model model) {
        GameSession gameSession = (GameSession) session.getAttribute("gameSession");

        if (gameSession == null) {
            return "redirect:/"; // Si no hay sesión, vuelve al inicio.
        }

        // Si el juego ha terminado, redirige a los resultados
        if (!gameSession.getIsActive()) {
            return "redirect:/game/results";
        }

        Question question = gameService.generateQuestion(gameSession.getBookType());
        if (question == null) {
            model.addAttribute("error", "No se pudo generar una pregunta.");
            return "error";
        }

        session.setAttribute("currentQuestion", question);

        model.addAttribute("question", question);
        model.addAttribute("gameSession", gameSession);

        return "game"; // Renderiza la página de juego
    }

    // 4. Procesa la respuesta del jugador
    @PostMapping("/game/submit")
    public String submitAnswer(@RequestParam Long questionId,
                               @RequestParam String selectedAnswer,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {

        GameSession gameSession = (GameSession) session.getAttribute("gameSession");
        Question currentQuestion = (Question) session.getAttribute("currentQuestion");

        if (gameSession == null || currentQuestion == null) {
            return "redirect:/";
        }

        boolean isCorrect = gameService.processAnswer(gameSession, currentQuestion, selectedAnswer);

        if (!isCorrect) {
            gameSession.incorrectAnswer();
        } else {
            gameSession.correctAnswer(1); // Suma 1 punto por respuesta correcta
        }

        gameSessionRepository.save(gameSession); // Guarda la sesión después de cada respuesta

        return "redirect:/game/question";
    }

    // 5. Muestra la página de resultados
    @GetMapping("/game/results")
    public String showResults(HttpSession session, Model model) {
        GameSession gameSession = (GameSession) session.getAttribute("gameSession");
        if (gameSession != null) {
            gameSession.setIsActive(false); // Marca la sesión como inactiva
            gameSessionRepository.save(gameSession); // Guarda el resultado final
            model.addAttribute("gameSession", gameSession);
        }
        session.invalidate(); // Invalida la sesión actual para un nuevo juego
        return "results";
    }

    // --- Tablas de Puntuaciones ---

    @GetMapping("/leaderboard")
    public String showLeaderboard(Model model) {
        // Lógica para mostrar la tabla de clasificación
        return "leaderboard";
    }

    @GetMapping("/history")
    public String showHistory(Model model) {
        // Lógica para mostrar el historial de juegos
        return "history";
    }
}