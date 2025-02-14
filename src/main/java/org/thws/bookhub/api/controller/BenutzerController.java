package org.thws.bookhub.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.thws.bookhub.api.assembler.BenutzerModelAssembler;
import org.thws.bookhub.domain.model.Benutzer;
import org.thws.bookhub.domain.service.BenutzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/benutzer")
public class BenutzerController {

    private final BenutzerService benutzerService;
    private final BenutzerModelAssembler assembler;
    private final PagedResourcesAssembler<Benutzer> pagedResourcesAssembler;

    @Autowired
    public BenutzerController(BenutzerService benutzerService,
                              BenutzerModelAssembler assembler,
                              PagedResourcesAssembler<Benutzer> pagedResourcesAssembler) {
        this.benutzerService = benutzerService;
        this.assembler = assembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    // Benutzer erstellen (POST)
    @PostMapping
    public Benutzer createBenutzer(@RequestBody Benutzer benutzer) {
        return benutzerService.addBenutzer(benutzer);
    }

    // Alle Benutzer abrufen (GET)
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Benutzer>>> getAllBenutzer(Pageable pageable) {
        Page<Benutzer> benutzerPage = benutzerService.getAllBenutzer(pageable);
        PagedModel<EntityModel<Benutzer>> pagedModel = pagedResourcesAssembler.toModel(benutzerPage, assembler);
        return ResponseEntity.ok(pagedModel);
    }

    // Benutzer nach ID abrufen (GET)
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Benutzer>> getBenutzerById(@PathVariable Long id) {
        Optional<Benutzer> benutzer = benutzerService.getBenutzerById(id);
        return benutzer.map(value -> ResponseEntity.ok(assembler.toModel(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Benutzer aktualisieren (PUT)
    @PutMapping("/{id}")
    public Benutzer updateBenutzer(@PathVariable Long id, @RequestBody Benutzer benutzer) {
        return benutzerService.updateBenutzer(id, benutzer);
    }

    // Benutzer löschen (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteBenutzer(@PathVariable Long id) {
        benutzerService.deleteBenutzer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Benutzer nach E-Mail abrufen (GET)
    @GetMapping("/by-email")
    public ResponseEntity<EntityModel<Benutzer>> getBenutzerByEmail(@RequestParam String email) {
        Benutzer benutzer = benutzerService.findBenutzerByEmail(email);
        if (benutzer != null) {
            return ResponseEntity.ok(assembler.toModel(benutzer));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Benutzer nach Name abrufen (GET)
    @GetMapping("/by-name")
    public ResponseEntity<PagedModel<EntityModel<Benutzer>>> getBenutzerByName(@RequestParam String name, Pageable pageable) {
        Page<Benutzer> benutzerPage = benutzerService.getBenutzerByName(name, pageable);
        PagedModel<EntityModel<Benutzer>> pagedModel = pagedResourcesAssembler.toModel(benutzerPage, assembler);
        return ResponseEntity.ok(pagedModel);
    }

}
