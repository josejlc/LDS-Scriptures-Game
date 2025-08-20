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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class GameController {

    private static final Logger logger = LoggerFactory.getLogger(GameController.class);
    private static final String GAME_SESSION_ATTR = "gameSession";
    private static final String CURRENT_QUESTION_ATTR = "currentQuestion";
    private static final String DEFAULT_PLAYER_NAME = "Jugador";

    @Autowired
    private GameService gameService;

    @Autowired
    private GameSessionRepository gameSessionRepository;

    // --- Flujo del Juego ---

    /**
     * Página de inicio del juego
     */
    @GetMapping("/")
    public String home() {
        logger.info("Accediendo a la página de inicio");
        return "index";
    }

    /**
     * Inicia una nueva sesión de juego
     */
    @PostMapping("/game/start")
    public String startGame(@RequestParam String playerName,
                            @RequestParam ScriptureBookType bookType,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        try {
            // Validación y sanitización del nombre del jugador
            if (playerName == null || playerName.trim().isEmpty()) {
                playerName = DEFAULT_PLAYER_NAME;
            } else {
                playerName = playerName.trim();
                // Limitar longitud del nombre
                if (playerName.length() > 50) {
                    playerName = playerName.substring(0, 50);
                }
            }

            // Validación del tipo de libro
            if (bookType == null) {
                redirectAttributes.addFlashAttribute("error", "Debe seleccionar un tipo de escritura");
                return "redirect:/";
            }

            GameSession gameSession = gameService.createNewGameSession(playerName, bookType);
            gameSessionRepository.save(gameSession);
            session.setAttribute(GAME_SESSION_ATTR, gameSession);

            logger.info("Nueva sesión de juego creada para el jugador: {} con libro: {}",
                    playerName, bookType);

            return "redirect:/game/question";
        } catch (Exception e) {
            logger.error("Error al iniciar nueva sesión de juego", e);
            redirectAttributes.addFlashAttribute("error",
                    "Error al iniciar el juego. Por favor, inténtelo de nuevo.");
            return "redirect:/";
        }
    }

    /**
     * Muestra una pregunta del juego
     */
    @GetMapping("/game/question")
    public String showQuestion(HttpSession session, Model model, RedirectAttributes redirectAttributes) {
        GameSession gameSession = (GameSession) session.getAttribute(GAME_SESSION_ATTR);

        if (gameSession == null) {
            logger.warn("Intento de acceso a pregunta sin sesión activa");
            redirectAttributes.addFlashAttribute("error", "No hay una sesión de juego activa");
            return "redirect:/";
        }

        // Si el juego ha terminado, redirige a los resultados
        if (!gameSession.getIsActive()) {
            return "redirect:/game/results";
        }

        try {
            Question question = gameService.generateQuestion(gameSession.getBookType());
            if (question == null) {
                logger.error("No se pudo generar una pregunta para el tipo: {}",
                        gameSession.getBookType());
                model.addAttribute("error", "No se pudo generar una pregunta. Por favor, inténtelo de nuevo.");
                return "error";
            }

            session.setAttribute(CURRENT_QUESTION_ATTR, question);

            model.addAttribute("question", question);
            model.addAttribute("gameSession", gameSession);

            return "game";
        } catch (Exception e) {
            logger.error("Error al generar pregunta", e);
            model.addAttribute("error", "Error al cargar la pregunta. Por favor, recargue la página.");
            return "error";
        }
    }

    /**
     * Procesa la respuesta del jugador
     */
    @PostMapping("/game/submit")
    public String submitAnswer(@RequestParam Long questionId,
                               @RequestParam String selectedAnswer,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {

        GameSession gameSession = (GameSession) session.getAttribute(GAME_SESSION_ATTR);
        Question currentQuestion = (Question) session.getAttribute(CURRENT_QUESTION_ATTR);

        if (gameSession == null || currentQuestion == null) {
            logger.warn("Intento de envío de respuesta sin sesión o pregunta válida");
            redirectAttributes.addFlashAttribute("error", "Sesión inválida");
            return "redirect:/";
        }

        // Validación adicional: verificar que el questionId coincida
        if (!currentQuestion.getId().equals(questionId)) {
            logger.warn("ID de pregunta no coincide. Esperado: {}, Recibido: {}",
                    currentQuestion.getId(), questionId);
            redirectAttributes.addFlashAttribute("error", "Pregunta inválida");
            return "redirect:/game/question";
        }

        // Validación de respuesta
        if (selectedAnswer == null || selectedAnswer.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Debe seleccionar una respuesta");
            return "redirect:/game/question";
        }

        try {
            boolean isCorrect = gameService.processAnswer(gameSession, currentQuestion, selectedAnswer.trim());

            if (isCorrect) {
                gameSession.correctAnswer(1);
                redirectAttributes.addFlashAttribute("feedback", "¡Respuesta correcta!");
                logger.debug("Respuesta correcta del jugador: {}", gameSession.getPlayerName());
            } else {
                gameSession.incorrectAnswer();
                redirectAttributes.addFlashAttribute("feedback",
                        "Respuesta incorrecta. La respuesta correcta era: " + currentQuestion.getCorrectAnswer());
                logger.debug("Respuesta incorrecta del jugador: {}", gameSession.getPlayerName());
            }

            gameSessionRepository.save(gameSession);

            // Limpiar la pregunta actual de la sesión
            session.removeAttribute(CURRENT_QUESTION_ATTR);

        } catch (Exception e) {
            logger.error("Error al procesar respuesta", e);
            redirectAttributes.addFlashAttribute("error", "Error al procesar la respuesta");
        }

        return "redirect:/game/question";
    }

    /**
     * Muestra la página de resultados
     */
    @GetMapping("/game/results")
    public String showResults(HttpSession session, Model model) {
        GameSession gameSession = (GameSession) session.getAttribute(GAME_SESSION_ATTR);

        if (gameSession == null) {
            logger.warn("Intento de acceso a resultados sin sesión activa");
            return "redirect:/";
        }

        try {
            gameSession.setIsActive(false);
            gameSessionRepository.save(gameSession);

            model.addAttribute("gameSession", gameSession);
            logger.info("Juego terminado para el jugador: {} con puntuación: {}",
                    gameSession.getPlayerName(), gameSession.getScore());

        } catch (Exception e) {
            logger.error("Error al guardar resultados del juego", e);
        } finally {
            session.invalidate(); // Invalida la sesión para un nuevo juego
        }

        return "results";
    }

    // --- Tablas de Puntuaciones ---

    /**
     * Muestra la tabla de clasificación
     */
    @GetMapping("/leaderboard")
    public String showLeaderboard(Model model) {
        try {
            logger.info("Accediendo a la tabla de clasificación");

            // Obtener top jugadores - usar método básico si no existe el personalizado
            List<GameSession> topPlayers = gameSessionRepository.findAll();

            // Ordenar por puntuación (descendente) y limitar a top 10
            topPlayers = topPlayers.stream()
                    .filter(session -> !session.getIsActive()) // Solo sesiones completadas
                    .sorted((a, b) -> Integer.compare(b.getScore(), a.getScore()))
                    .limit(10)
                    .toList();

            model.addAttribute("topPlayers", topPlayers);

            return "leaderboard";
        } catch (Exception e) {
            logger.error("Error al cargar la tabla de clasificación", e);
            model.addAttribute("error", "Error al cargar la tabla de clasificación. Por favor, inténtelo más tarde.");
            return "error";
        }
    }

    /**
     * Muestra el historial de juegos
     */
    @GetMapping("/history")
    public String showHistory(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "20") int size) {
        try {
            logger.info("Accediendo al historial de juegos");

            // Implementar paginación básica
            if (size > 100) size = 100; // Limitar tamaño máximo de página
            if (page < 0) page = 0;

            List<GameSession> allSessions = gameSessionRepository.findAll();

            // Filtrar solo sesiones completadas y ordenar por ID (como proxy de fecha)
            List<GameSession> gameHistory = allSessions.stream()
                    .filter(session -> !session.getIsActive()) // Solo sesiones completadas
                    .sorted((a, b) -> Long.compare(b.getId(), a.getId())) // Ordenar por ID descendente (más recientes primero)
                    .skip(page * size)
                    .limit(size)
                    .toList();

            model.addAttribute("gameHistory", gameHistory);
            model.addAttribute("currentPage", page);
            model.addAttribute("pageSize", size);
            model.addAttribute("totalSessions", allSessions.size());

            return "history";
        } catch (Exception e) {
            logger.error("Error al cargar el historial de juegos", e);
            model.addAttribute("error", "Error al cargar el historial de juegos. Por favor, inténtelo más tarde.");
            return "error";
        }
    }

    /**
     * Endpoint para reiniciar un juego
     */
    @PostMapping("/game/restart")
    public String restartGame(HttpSession session) {
        logger.info("Reiniciando juego");
        session.invalidate();
        return "redirect:/";
    }

    /**
     * Manejo de errores específicos del controlador
     */
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model, HttpServletRequest request) {
        logger.error("Error no manejado en GameController: {} en URL: {}", e.getMessage(), request.getRequestURL());

        // Agregar información detallada para la página de error
        model.addAttribute("error", "Ha ocurrido un error inesperado. Por favor, inténtelo de nuevo.");
        model.addAttribute("status", 500);

        // En modo desarrollo, agregar stack trace
        if (isDevelopmentMode()) {
            model.addAttribute("trace", getStackTrace(e));
        }

        return "error";
    }

    /**
     * Manejo específico para errores de base de datos
     */
    @ExceptionHandler({org.springframework.dao.DataAccessException.class})
    public String handleDatabaseException(Exception e, Model model, HttpServletRequest request) {
        logger.error("Error de base de datos en GameController: {} en URL: {}", e.getMessage(), request.getRequestURL());

        model.addAttribute("error", "Error de conexión con la base de datos. Por favor, inténtelo más tarde.");
        model.addAttribute("status", 503);

        if (isDevelopmentMode()) {
            model.addAttribute("trace", getStackTrace(e));
        }

        return "error";
    }

    /**
     * Manejo específico para errores de validación
     */
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public String handleValidationException(Exception e, Model model, HttpServletRequest request) {
        logger.warn("Error de validación en GameController: {} en URL: {}", e.getMessage(), request.getRequestURL());

        model.addAttribute("error", "Datos inválidos: " + e.getMessage());
        model.addAttribute("status", 400);

        if (isDevelopmentMode()) {
            model.addAttribute("trace", getStackTrace(e));
        }

        return "error";
    }

    /**
     * Verifica si estamos en modo desarrollo
     */
    private boolean isDevelopmentMode() {
        // Puedes ajustar esta lógica según tu configuración
        return java.util.Arrays.asList(
                System.getProperty("spring.profiles.active", "").split(",")
        ).contains("dev") ||
                "development".equals(System.getProperty("app.environment"));
    }

    /**
     * Obtiene el stack trace como string
     */
    private String getStackTrace(Exception e) {
        java.io.StringWriter sw = new java.io.StringWriter();
        java.io.PrintWriter pw = new java.io.PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}