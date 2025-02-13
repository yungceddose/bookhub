package org.thws.bookhub.domain.service;
import org.springframework.cglib.core.Local;
import org.thws.bookhub.domain.model.Genre;
import org.thws.bookhub.persistence.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    // Genre erstellen
    public Genre createGenre(Genre genre) {
        return genreRepository.save(genre);
    }

    // Alle Genres abrufen
    public List<Genre> findAllGenres() {
        return genreRepository.findAll();
    }

    // Genre aktualisieren
    public Genre updateGenre(Long id, Genre genre) {
        if (genreRepository.existsById(id)) {
            genre.setId(id); // ID beibehalten
            return genreRepository.save(genre);
        }
        return null;
    }

    // Genre löschen
    public void deleteGenre(Long id) {
        genreRepository.deleteById(id);
    }

    // Genre nach Name suchen
    public List<Genre> findGenreByName(String name) {
        return genreRepository.findByName(name);
    }

}
