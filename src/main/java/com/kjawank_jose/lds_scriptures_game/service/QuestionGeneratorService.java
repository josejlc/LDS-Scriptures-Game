package com.kjawank_jose.lds_scriptures_game.service;

import com.kjawank_jose.lds_scriptures_game.model.Question;
import com.kjawank_jose.lds_scriptures_game.model.Verse;
import com.kjawank_jose.lds_scriptures_game.enums.GameMode;
import com.kjawank_jose.lds_scriptures_game.enums.ScriptureBookType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuestionGeneratorService {

    @Autowired
    private VerseService verseService;

    private Random random = new Random();

    public Question generateRandomQuestion(ScriptureBookType bookType) {
        Verse verse = verseService.getRandomVerseFromBook(bookType);
        if (verse == null) return null;

        GameMode mode = selectGameMode(verse);
        return buildQuestion(verse, mode);
    }

    private GameMode selectGameMode(Verse verse) {
        // Si el versículo es muy corto para completar, evitar ese modo
        if (verse.getText().split("\\s+").length < 6) {
            return random.nextBoolean() ? GameMode.REFERENCE : GameMode.PHRASE;
        }
        return GameMode.getRandomMode();
    }

    private Question buildQuestion(Verse verse, GameMode mode) {
        String questionText;
        String correctAnswer;
        List<String> options = new ArrayList<>();

        switch (mode) {
            case REFERENCE:
                questionText = "Frase clave: \"" + verse.getText() + "\"\nSelecciona la Referencia:";
                correctAnswer = verse.getReference();
                options = generateReferenceOptions(verse);
                break;

            case PHRASE:
                questionText = "Referencia: \"" + verse.getReference() + "\"\nSelecciona la Frase clave:";
                correctAnswer = verse.getText();
                options = generatePhraseOptions(verse);
                break;

            case COMPLETE:
                questionText = "Referencia: " + verse.getReference() + "\nCompleta las palabras que faltan:";
                String[] gapResult = generateTextWithGaps(verse.getText());
                if (gapResult == null) {
                    return buildQuestion(verse, GameMode.PHRASE);
                }
                questionText += "\n" + gapResult[0];
                correctAnswer = gapResult[1];
                options = generateCompletionOptions(correctAnswer);
                break;

            default:
                return buildQuestion(verse, GameMode.REFERENCE);
        }

        Collections.shuffle(options);

        return new Question(verse, questionText, correctAnswer,
                options.get(0), options.get(1), options.get(2), options.get(3),
                mode.name(), mode.getPoints());
    }

    private List<String> generateReferenceOptions(Verse correctVerse) {
        List<String> options = new ArrayList<>();
        options.add(correctVerse.getReference());

        List<Verse> allVerses = verseService.getAllVerses();
        Collections.shuffle(allVerses);

        for (Verse v : allVerses) {
            if (options.size() >= 4) break;
            if (!options.contains(v.getReference())) {
                options.add(v.getReference());
            }
        }

        while (options.size() < 4) {
            options.add("Opción " + options.size());
        }

        return options;
    }

    private List<String> generatePhraseOptions(Verse correctVerse) {
        List<String> options = new ArrayList<>();
        options.add(correctVerse.getText());

        List<Verse> allVerses = verseService.getAllVerses();
        Collections.shuffle(allVerses);

        for (Verse v : allVerses) {
            if (options.size() >= 4) break;
            if (!options.contains(v.getText())) {
                options.add(v.getText());
            }
        }

        while (options.size() < 4) {
            options.add("Texto alternativo " + options.size());
        }

        return options;
    }

    private String[] generateTextWithGaps(String text) {
        String[] words = text.split("\\s+");
        if (words.length < 4) return null;

        Set<Integer> gapPositions = new HashSet<>();
        while (gapPositions.size() < 2) {
            int pos = random.nextInt(words.length);
            if (words[pos].length() > 2) {
                gapPositions.add(pos);
            }
        }

        List<String> missingWords = new ArrayList<>();
        String[] wordsWithGaps = words.clone();

        for (int pos : gapPositions) {
            missingWords.add(words[pos]);
            wordsWithGaps[pos] = "______";
        }

        String textWithGaps = String.join(" ", wordsWithGaps);
        String missingWordsText = String.join(" - ", missingWords);

        return new String[]{textWithGaps, missingWordsText};
    }

    private List<String> generateCompletionOptions(String correctAnswer) {
        List<String> options = new ArrayList<>();
        options.add(correctAnswer);

        Set<String> usedOptions = new HashSet<>();
        usedOptions.add(correctAnswer);

        List<Verse> allVerses = verseService.getAllVerses();
        Collections.shuffle(allVerses);

        // Generar opciones incorrectas usando palabras de otros versículos
        int attempts = 0;
        while (options.size() < 4 && attempts < 100) {
            for (Verse verse : allVerses) {
                if (options.size() >= 4) break;

                String[] words = verse.getText().split("\\s+");
                if (words.length >= 2) {
                    // Seleccionar 2 palabras aleatorias del versículo
                    List<String> cleanWords = new ArrayList<>();
                    for (String word : words) {
                        String cleanWord = cleanWord(word);
                        if (cleanWord.length() > 2) {
                            cleanWords.add(cleanWord);
                        }
                    }

                    if (cleanWords.size() >= 2) {
                        Collections.shuffle(cleanWords);
                        String option = cleanWords.get(0) + " - " + cleanWords.get(1);

                        if (!usedOptions.contains(option)) {
                            options.add(option);
                            usedOptions.add(option);
                        }
                    }
                }
            }
            attempts++;
        }

        // Si aún faltan opciones, usar palabras más comunes de las escrituras
        String[] commonWords = {"Dios", "Señor", "Cristo", "Jesús", "pueblo", "corazón", "humildad", "enseña", "principo", "doctrina",
                "tierra", "casa", "alma", "espíritu", "palabra", "verdad", "mandamientos", "vara","leyes", "convenio", "pacto","revelación", "bendiciones"};

        while (options.size() < 4) {
            String option = commonWords[random.nextInt(commonWords.length)] + " - " +
                    commonWords[random.nextInt(commonWords.length)];
            if (!usedOptions.contains(option)) {
                options.add(option);
                usedOptions.add(option);
            }
        }

        return options;
    }

    private String cleanWord(String word) {
        return word.replaceAll("[^a-zA-ZáéíóúÁÉÍÓÚñÑ]", "");
    }
}
