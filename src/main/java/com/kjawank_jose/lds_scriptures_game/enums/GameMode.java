package com.kjawank_jose.lds_scriptures_game.enums;

import java.util.Random;

public enum GameMode {
    REFERENCE(0, "Seleccionar Referencia", 10),
    PHRASE(1, "Seleccionar Frase", 10),
    COMPLETE(2, "Completar Espacios", 15);

    private final int code;
    private final String description;
    private final int points;

    GameMode(int code, String description, int points) {
        this.code = code;
        this.description = description;
        this.points = points;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public int getPoints() {
        return points;
    }

    public static GameMode getRandomMode() {
        GameMode[] modes = values();
        return modes[new Random().nextInt(modes.length)];
    }

    public static GameMode getByCode(int code) {
        for (GameMode mode : values()) {
            if (mode.getCode() == code) {
                return mode;
            }
        }
        return REFERENCE;
    }
}
