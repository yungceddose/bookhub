package org.thws.bookhub.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.thws.bookhub.domain.model.Genre;
import org.thws.bookhub.domain.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    // Genre erstellen (POST)
    @PostMapping
    public Genre createGenre(@RequestBody Genre genre) {
        return genreService.createGenre(genre);
    }

    // Alle Genres abrufen (GET)
    @GetMapping
    public ResponseEntity<Page<Genre>> getAllGenres(Pageable pageable) {
        Page<Genre> genrePage = genreService.findAllGenres(pageable);
        return new ResponseEntity<>(genrePage, HttpStatus.OK);
    }

    // 4. Genre aktualisieren (PUT)
    @PutMapping("/{id}")
    public Genre updateGenre(@PathVariable Long id, @RequestBody Genre genre) {
        return genreService.updateGenre(id, genre);
    }

    // 5. Genre löschen (DELETE)
    @DeleteMapping("/{id}")
    public void deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
    }

    @GetMapping("/by-name")
    public ResponseEntity<Page<Genre>> getGenreByName(@RequestParam String name, Pageable pageable) {
        Page<Genre> genrePage = genreService.findGenreByName(name, pageable);
        return new ResponseEntity<>(genrePage, HttpStatus.OK);
    }

}
