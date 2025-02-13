package org.thws.bookhub.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String vorname;
    private String nachname;
    private LocalDate geburtsdatum;
    private String nationalitaet;

    @OneToMany(mappedBy = "autor")
    @JsonManagedReference
    private List<Buch> buecher = new ArrayList<>();

    // Standard-Konstruktor
    public Autor() {}

    // Konstruktor
    public Autor(String vorname, String nachname, LocalDate geburtsdatum, String nationalitaet) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.geburtsdatum = geburtsdatum;
        this.nationalitaet = nationalitaet;
    }

    // Getter and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public LocalDate getGeburtsdatum() {
        return geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getNationalitaet() {
        return nationalitaet;
    }

    public void setNationalitaet(String nationalitaet) {
        this.nationalitaet = nationalitaet;
    }

    public List<Buch> getBuecher() {
        return buecher;
    }

    public void setBuecher(List<Buch> buecher) {
        this.buecher = buecher;
    }
}

