package org.thws.bookhub.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.thws.bookhub.api.assembler.AutorModelAssembler;
import org.thws.bookhub.api.assembler.VerlagModelAssembler;
import org.thws.bookhub.domain.model.Autor;
import org.thws.bookhub.domain.model.Verlag;
import org.thws.bookhub.domain.service.VerlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/verlage")
public class VerlagController {

    private final VerlagService verlagService;
    private final VerlagModelAssembler assembler;
    private final PagedResourcesAssembler<Verlag> pagedResourcesAssembler;

    @Autowired
    public VerlagController(VerlagService verlagService,
                            VerlagModelAssembler assembler,
                            PagedResourcesAssembler<Verlag> pagedResourcesAssembler) {
        this.verlagService = verlagService;
        this.assembler = assembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    // Verlag erstellen (POST)
    @PostMapping
    public Verlag createVerlag(@RequestBody Verlag verlag) {
        return verlagService.createVerlag(verlag);
    }

    // Alle Verlage abrufen (GET)
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Verlag>>> getAllVerlage(Pageable pageable) {
        Page<Verlag> verlagPage = verlagService.findAllVerlage(pageable);
        PagedModel<EntityModel<Verlag>> pagedModel = pagedResourcesAssembler.toModel(verlagPage, assembler);
        return ResponseEntity.ok(pagedModel);
    }

    // Verlag aktualisieren (PUT)
    @PutMapping("/{id}")
    public Verlag updateVerlag(@PathVariable Long id, @RequestBody Verlag verlag) {
        return verlagService.updateVerlag(id, verlag);
    }

    // Verlag löschen (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVerlag(@PathVariable Long id) {
        verlagService.deleteVerlag(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Verlag nach Name suchen (GET)
    @GetMapping("/by-name")
    public ResponseEntity<EntityModel<Verlag>> getVerlagByName(@RequestParam String name) {
        Verlag verlag = verlagService.findVerlagByName(name);
        return ResponseEntity.ok(assembler.toModel(verlag));
    }

    // Verlag nach Sitz suchen (GET)
    @GetMapping("/by-sitz")
    public ResponseEntity<PagedModel<EntityModel<Verlag>>> getVerlagBySitz(@RequestParam String sitz, Pageable pageable) {
        Page<Verlag> verlagPage = verlagService.findVerlagBySitz(sitz, pageable);
        PagedModel<EntityModel<Verlag>> pagedModel = pagedResourcesAssembler.toModel(verlagPage, assembler);
        return ResponseEntity.ok(pagedModel);
    }

    // Verlag nach Gründungsjahr suchen (GET)
    @GetMapping("/by-gruendungsjahr")
    public ResponseEntity<PagedModel<EntityModel<Verlag>>> getVerlagByGruendungsjahr(@RequestParam Integer gruendungsjahr, Pageable pageable) {
        Page<Verlag> verlagPage = verlagService.findVerlagByGruendungsjahr(gruendungsjahr, pageable);
        PagedModel<EntityModel<Verlag>> pagedModel = pagedResourcesAssembler.toModel(verlagPage, assembler);
        return ResponseEntity.ok(pagedModel);
    }

}
