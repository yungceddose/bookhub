package org.thws.bookhub.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.thws.bookhub.api.assembler.AutorModelAssembler;
import org.thws.bookhub.api.assembler.BuchModelAssembler;
import org.thws.bookhub.domain.model.Autor;
import org.thws.bookhub.domain.model.Buch;
import org.thws.bookhub.domain.model.Genre;
import org.thws.bookhub.domain.service.BuchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/buecher") // Basis-URL für diesen Controller
public class BuchController {

    private final BuchService buchService;
    private final BuchModelAssembler assembler;
    private final PagedResourcesAssembler<Buch> pagedResourcesAssembler;

    @Autowired
    public BuchController(BuchService buchService,
                          BuchModelAssembler assembler,
                          PagedResourcesAssembler<Buch> pagedResourcesAssembler) {
        this.buchService = buchService;
        this.assembler = assembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    // Buch erstellen (POST)
    @PostMapping
    public Buch createBuch(@RequestBody Buch buch) {
        return buchService.createBuch(buch);
    }

    // Alle Bücher abrufen (GET)
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Buch>>> getAllBuecher(Pageable pageable) {
        Page<Buch> buchPage = buchService.findAllBuecher(pageable);
        PagedModel<EntityModel<Buch>> pagedModel = pagedResourcesAssembler.toModel(buchPage, assembler);
        return ResponseEntity.ok(pagedModel);
    }

    // Buch nach ISBN abrufen (GET)
    @GetMapping("/{isbn}")
    public ResponseEntity<EntityModel<Buch>> getBuchByIsbn(@PathVariable String isbn) {
        Optional<Buch> buch = buchService.findBuchById(isbn);
        return buch.map(value -> ResponseEntity.ok(assembler.toModel(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Buch aktualisieren (PUT)
    @PutMapping("/{isbn}")
    public Buch updateBuch(@PathVariable String isbn, @RequestBody Buch buch) {
        return buchService.updateBuch(isbn, buch);
    }

    // Buch löschen (DELETE)
    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBuch(@PathVariable String isbn) {
        buchService.deleteBuch(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Bücher nach Titel suchen (GET)
    @GetMapping("/by-titel")
    public ResponseEntity<PagedModel<EntityModel<Buch>>> getBuecherByTitel(@RequestParam String titel, Pageable pageable) {
        Page<Buch> buchPage = buchService.findBuecherByTitel(titel, pageable);
        PagedModel<EntityModel<Buch>> pagedModel = pagedResourcesAssembler.toModel(buchPage, assembler);
        return ResponseEntity.ok(pagedModel);
    }

    // Bücher nach Veröffentlichungsdatum suchen (GET)
    @GetMapping("/by-veroeffentlichungsdatum")
    public ResponseEntity<PagedModel<EntityModel<Buch>>> getBuecherByVeroeffentlichungsdatum(@RequestParam LocalDate veroeffentlichungsdatum, Pageable pageable) {
        Page<Buch> buchPage = buchService.findBuecherByVeroeffentlichungsdatum(veroeffentlichungsdatum, pageable);
        PagedModel<EntityModel<Buch>> pagedModel = pagedResourcesAssembler.toModel(buchPage, assembler);
        return ResponseEntity.ok(pagedModel);
    }

    // Bücher nach Genre suchen (GET)
    @GetMapping("/by-genre")
    public ResponseEntity<PagedModel<EntityModel<Buch>>> getBuecherByGenre(@RequestParam Genre genre, Pageable pageable) {
        Page<Buch> buchPage = buchService.findBuecherByGenre(genre, pageable);
        PagedModel<EntityModel<Buch>> pagedModel = pagedResourcesAssembler.toModel(buchPage, assembler);
        return ResponseEntity.ok(pagedModel);
    }

    // Bücher nach Seitenanzahl suchen (GET)
    @GetMapping("/by-seitenanzahl")
    public ResponseEntity<PagedModel<EntityModel<Buch>>> getBuecherBySeitenanzahl(@RequestParam int seitenanzahl, Pageable pageable) {
        Page<Buch> buchPage = buchService.findBuecherBySeitenanzahl(seitenanzahl, pageable);
        PagedModel<EntityModel<Buch>> pagedModel = pagedResourcesAssembler.toModel(buchPage, assembler);
        return ResponseEntity.ok(pagedModel);
    }

}
