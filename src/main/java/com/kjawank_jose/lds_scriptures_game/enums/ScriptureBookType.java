package com.kjawank_jose.lds_scriptures_game.enums;

import java.util.Arrays;

public enum ScriptureBookType {
    OLD_TESTAMENT(1, "Antiguo Testamento"),
    NEW_TESTAMENT(2, "Nuevo Testamento"),
    BOOK_OF_MORMON(3, "Libro de Mormón"),
    DOCTRINE_COVENANTS(4, "Doctrina y Convenios e Historia de la Iglesia"),
    ALL_BOOKS(5, "Todos los libros");

    private final int code;
    private final String name;

    ScriptureBookType(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static ScriptureBookType getByCode(int code) {
        return Arrays.stream(values())
                .filter(book -> book.getCode() == code)
                .findFirst()
                .orElse(ALL_BOOKS);
    }

    public static ScriptureBookType getByName(String name) {
        return Arrays.stream(values())
                .filter(book -> book.getName().equals(name))
                .findFirst()
                .orElse(ALL_BOOKS);
    }
}
