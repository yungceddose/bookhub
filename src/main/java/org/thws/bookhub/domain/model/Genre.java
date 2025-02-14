package org.thws.bookhub.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "genre")
    @JsonManagedReference(value = "genre-buecher")
    private List<Buch> buecher = new ArrayList<>();


    // Getter and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Buch> getBuecher() {
        return buecher;
    }

    public void setBuecher(List<Buch> buecher) {
        this.buecher = buecher;
    }
}

