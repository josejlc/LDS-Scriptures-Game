package com.kjawank_jose.lds_scriptures_game.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "scripture_books")
public class ScriptureBook {
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Verse> verses;

    // Constructors
    public ScriptureBook() {}

    public ScriptureBook(Long id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public List<Verse> getVerses() { return verses; }
    public void setVerses(List<Verse> verses) { this.verses = verses; }
}
