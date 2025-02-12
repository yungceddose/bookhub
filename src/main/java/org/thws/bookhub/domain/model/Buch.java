package org.thws.bookhub.domain.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Buch {
    @Id
    private String isbnNummer;
    private String titel;
    private LocalDate veroeffentlichungsdatum;
    private int seitenanzahl;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "verlag_id")
    private Verlag verlag;

    @OneToMany(mappedBy = "buch")
    private List<Bewertung> bewertungen = new ArrayList<>();


    // Getter and Setters

    public String getIsbnNummer() {
        return isbnNummer;
    }

    public void setIsbnNummer(String isbnNummer) {
        this.isbnNummer = isbnNummer;
    }

    public List<Bewertung> getBewertungen() {
        return bewertungen;
    }

    public void setBewertungen(List<Bewertung> bewertungen) {
        this.bewertungen = bewertungen;
    }

    public Verlag getVerlag() {
        return verlag;
    }

    public void setVerlag(Verlag verlag) {
        this.verlag = verlag;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public int getSeitenanzahl() {
        return seitenanzahl;
    }

    public void setSeitenanzahl(int seitenanzahl) {
        this.seitenanzahl = seitenanzahl;
    }

    public LocalDate getVeroeffentlichungsdatum() {
        return veroeffentlichungsdatum;
    }

    public void setVeroeffentlichungsdatum(LocalDate veroeffentlichungsdatum) {
        this.veroeffentlichungsdatum = veroeffentlichungsdatum;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }
}

