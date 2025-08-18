package com.kjawank_jose.lds_scriptures_game.model;

import jakarta.persistence.*;

@Entity
@Table(name = "verses")
public class Verse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(nullable = false)
    private String reference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private ScriptureBook book;

    // Constructors
    public Verse() {}

    public Verse(String text, String reference, ScriptureBook book) {
        this.text = text;
        this.reference = reference;
        this.book = book;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getReference() { return reference; }
    public void setReference(String reference) { this.reference = reference; }

    public ScriptureBook getBook() { return book; }
    public void setBook(ScriptureBook book) { this.book = book; }
}
