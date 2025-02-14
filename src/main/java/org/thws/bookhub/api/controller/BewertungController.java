package org.thws.bookhub.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.thws.bookhub.api.assembler.BewertungModelAssembler;
import org.thws.bookhub.domain.model.Autor;
import org.thws.bookhub.domain.model.Bewertung;
import org.thws.bookhub.domain.service.BewertungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/bewertungen")
public class BewertungController {

    private final BewertungService bewertungService;
    private final BewertungModelAssembler assembler;
    private final PagedResourcesAssembler<Bewertung> pagedResourcesAssembler;

    @Autowired
    public BewertungController(BewertungService bewertungService,
                               BewertungModelAssembler assembler,
                               PagedResourcesAssembler<Bewertung> pagedResourcesAssembler) {
        this.bewertungService = bewertungService;
        this.assembler = assembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    // Bewertung erstellen (POST)
    @PostMapping
    public Bewertung createBewertung(@RequestBody Bewertung bewertung) {
        return bewertungService.createBewertung(bewertung);
    }

    // Alle Bewertungen abrufen (GET)
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Bewertung>>> getAllBewertungen(Pageable pageable) {
        Page<Bewertung> bewertungPage = bewertungService.findAllBewertungen(pageable);
        PagedModel<EntityModel<Bewertung>> pagedModel = pagedResourcesAssembler.toModel(bewertungPage, assembler);
        return ResponseEntity.ok(pagedModel);
    }

    // 3. Bewertung nach ID abrufen (GET)
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Bewertung>> getBewertungById(@PathVariable Long id) {
        Optional<Bewertung> bewertung = bewertungService.findBewertungById(id);
        return bewertung.map(value -> ResponseEntity.ok(assembler.toModel(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Bewertung aktualisieren (PUT)
    @PutMapping("/{id}")
    public Bewertung updateBewertung(@PathVariable Long id, @RequestBody Bewertung bewertung) {
        return bewertungService.updateBewertung(id, bewertung);
    }

    // Bewertung löschen (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBewertung(@PathVariable Long id) {
        bewertungService.deleteBewertung(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Bewertungen nach Punktzahl suchen (GET)
    @GetMapping("/by-punktzahl")
    public ResponseEntity<PagedModel<EntityModel<Bewertung>>> getBewertungenByPunktzahl(@RequestParam int punktzahl, Pageable pageable) {
        Page<Bewertung> bewertungPage = bewertungService.findBewertungenByPunktzahl(punktzahl, pageable);
        PagedModel<EntityModel<Bewertung>> pagedModel = pagedResourcesAssembler.toModel(bewertungPage, assembler);
        return ResponseEntity.ok(pagedModel);
    }

}
