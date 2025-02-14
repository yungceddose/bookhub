package org.thws.bookhub.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.thws.bookhub.api.assembler.AutorModelAssembler;
import org.thws.bookhub.domain.model.Autor;
import org.thws.bookhub.domain.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/autoren")
public class AutorController {

    private final AutorService autorService;
    private final AutorModelAssembler assembler;
    private final PagedResourcesAssembler<Autor> pagedResourcesAssembler;

    @Autowired
    public AutorController(AutorService autorService,
                           AutorModelAssembler assembler,
                           PagedResourcesAssembler<Autor> pagedResourcesAssembler) {
        this.autorService = autorService;
        this.assembler = assembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }



    // Neuen Autor hinzufügen (Create)
    @PostMapping
    public ResponseEntity<Autor> addAuthor(@RequestBody Autor autor) {
        Autor neuerAutor = autorService.addAutor(autor);
        return new ResponseEntity<>(neuerAutor, HttpStatus.CREATED); // Neuer Autor wurde erstellt, Status 201 Created
    }

    // Alle Autoren abrufen (Read)
    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<Autor>>> getAllAuthors(Pageable pageable) {
        Page<Autor> autorenPage = autorService.getAllAutoren(pageable);
        PagedModel<EntityModel<Autor>> pagedModel = pagedResourcesAssembler.toModel(autorenPage, assembler);
        return ResponseEntity.ok(pagedModel); // Rückgabe einer Liste aller Autoren mit Status 200 OK
    }

    // Autoren nach ID abrufen (Read)
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Autor>> getAutorById(@PathVariable Long id) {
        Autor autor = autorService.getAutorById(id);
        if (autor != null) {
            return ResponseEntity.ok(assembler.toModel(autor)); // Rückgabe des Autors mit Status 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Autor wurde nicht gefunden, Status 404
        }
    }

    // Autor aktualisieren (Update)
    @PutMapping("/{id}")
    public ResponseEntity<Autor> updateAuthor(@PathVariable Long id, @RequestBody Autor autorDetails) {
        Autor aktualisierterAutor = autorService.updateAutor(id, autorDetails);
        if (aktualisierterAutor != null) {
            return new ResponseEntity<>(aktualisierterAutor, HttpStatus.OK); // Aktualisierter Autor, Status 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Autor wurde nicht gefunden, Status 404
        }
    }

    // Autor löschen (Delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        autorService.deleteAutor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Autor wurde gelöscht, Status 204 No Content
    }


    // ~~~~~~~~~~~~~~~~~~~~~~~~ Restliche Anfragen ~~~~~~~~~~~~~~~~~~~~~~~~~~


    // Autoren nach Nachname suchen
    @GetMapping("/by-nachname")
    public ResponseEntity<PagedModel<EntityModel<Autor>>> getAutorenByNachname(@RequestParam String nachname, Pageable pageable) {
        Page<Autor> autorenPage = autorService.getAutorByNachname(nachname, pageable);
        PagedModel<EntityModel<Autor>> pagedModel = pagedResourcesAssembler.toModel(autorenPage, assembler);
        return ResponseEntity.ok(pagedModel);
    }

    // Autoren nach Vorname suchen
    @GetMapping("/by-vorname")
    public ResponseEntity<PagedModel<EntityModel<Autor>>> getAutorenByVorname(@RequestParam String vorname, Pageable pageable) {
        Page<Autor> autorenPage = autorService.getAutorByVorname(vorname, pageable);
        PagedModel<EntityModel<Autor>> pagedModel = pagedResourcesAssembler.toModel(autorenPage, assembler);
        return ResponseEntity.ok(pagedModel);
    }

    // Autoren nach Nationalität suchen
    @GetMapping("/by-nationalitaet")
    public ResponseEntity<PagedModel<EntityModel<Autor>>> getAutorenByNationalitaet(@RequestParam String nationalitaet, Pageable pageable) {
        Page<Autor> autorenPage = autorService.getAutorByNationalitaet(nationalitaet, pageable);
        PagedModel<EntityModel<Autor>> pagedModel = pagedResourcesAssembler.toModel(autorenPage, assembler);
        return ResponseEntity.ok(pagedModel);
    }

    // Autoren nach Geburtsdatum suchen
    @GetMapping("/by-geburtsdatum")
    public ResponseEntity<PagedModel<EntityModel<Autor>>> getAutorenByGeburtsdatum(@RequestParam LocalDate geburtsdatum, Pageable pageable) {
        Page<Autor> autorenPage = autorService.getAutorByGeburtsdatum(geburtsdatum, pageable);
        PagedModel<EntityModel<Autor>> pagedModel = pagedResourcesAssembler.toModel(autorenPage, assembler);
        return ResponseEntity.ok(pagedModel);
    }

}



