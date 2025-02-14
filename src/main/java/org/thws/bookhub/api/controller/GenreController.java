package org.thws.bookhub.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.thws.bookhub.api.assembler.AutorModelAssembler;
import org.thws.bookhub.api.assembler.GenreModelAssembler;
import org.thws.bookhub.domain.model.Autor;
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
    private final GenreModelAssembler assembler;
    private final PagedResourcesAssembler<Genre> pagedResourcesAssembler;

    @Autowired
    public GenreController(GenreService genreService,
                           GenreModelAssembler assembler,
                           PagedResourcesAssembler<Genre> pagedResourcesAssembler) {
        this.genreService = genreService;
        this.assembler = assembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    // Genre erstellen (POST)
    @PostMapping
    public Genre createGenre(@RequestBody Genre genre) {
        return genreService.createGenre(genre);
    }

    // Alle Genres abrufen (GET)
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Genre>>> getAllGenres(Pageable pageable) {
        Page<Genre> genrePage = genreService.findAllGenres(pageable);
        PagedModel<EntityModel<Genre>> pagedModel = pagedResourcesAssembler.toModel(genrePage, assembler);
        return ResponseEntity.ok(pagedModel);
    }

    // 4. Genre aktualisieren (PUT)
    @PutMapping("/{id}")
    public Genre updateGenre(@PathVariable Long id, @RequestBody Genre genre) {
        return genreService.updateGenre(id, genre);
    }

    // 5. Genre löschen (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/by-name")
    public ResponseEntity<PagedModel<EntityModel<Genre>>> getGenreByName(@RequestParam String name, Pageable pageable) {
        Page<Genre> genrePage = genreService.findGenreByName(name, pageable);
        PagedModel<EntityModel<Genre>> pagedModel = pagedResourcesAssembler.toModel(genrePage, assembler);
        return ResponseEntity.ok(pagedModel);
    }

}
