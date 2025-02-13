package org.thws.bookhub.api.controller;

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
    public List<Genre> getAllGenres() {
        return genreService.findAllGenres();
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
    public List<Genre> getGenreByName(@RequestParam String name) {
        return genreService.findGenreByName(name);
    }

}
