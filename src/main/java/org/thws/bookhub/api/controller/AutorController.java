package org.thws.bookhub.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.thws.bookhub.domain.model.Autor;
import org.thws.bookhub.domain.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/autoren")
public class AutorController {

    private final AutorService autorService;

    @Autowired
    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }



    // Neuen Autor hinzufügen (Create)
    @PostMapping
    public ResponseEntity<Autor> addAuthor(@RequestBody Autor autor) {
        Autor neuerAutor = autorService.addAutor(autor);
        return new ResponseEntity<>(neuerAutor, HttpStatus.CREATED); // Neuer Autor wurde erstellt, Status 201 Created
    }

    // Alle Autoren abrufen (Read)
    @GetMapping
    public ResponseEntity<List<Autor>> getAllAuthors() {
        List<Autor> autoren = autorService.getAllAutoren();
        return new ResponseEntity<>(autoren, HttpStatus.OK); // Rückgabe einer Liste aller Autoren mit Status 200 OK
    }

    // Autoren nach ID abrufen (Read)
    @GetMapping("/{id}")
    public ResponseEntity<Autor> getAutorById(@PathVariable Long id) {
        Autor autor = autorService.getAutorById(id);
        if (autor != null) {
            return new ResponseEntity<>(autor, HttpStatus.OK); // Rückgabe des Autors mit Status 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Autor wurde nicht gefunden, Status 404
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
    @GetMapping("/api/autoren/by-nachname")
    public List<Autor> getAutorenByNachname(@RequestParam String nachname) {
        return autorService.getAutorbyNachname(nachname);
    }

    // Autoren nach Vorname suchen
    @GetMapping("/api/autoren/by-vorname")
    public List<Autor> getAutorenByVorname(@RequestParam String vorname) {
        return autorService.getAutorByVorname(vorname);
    }

    // Autoren nach Nationalität suchen
    @GetMapping("/api/autoren/by-nationalitaet")
    public List<Autor> getAutorenByNationalitaet(@RequestParam String nationalitaet) {
        return autorService.getAutorByNationalitaet(nationalitaet);
    }

    // Autoren nach Geburtsdatum suchen
    @GetMapping("/api/autoren/by-geburtsdatum")
    public List<Autor> getAutorenByGeburtsdatum(@RequestParam LocalDate geburtsdatum) {
        return autorService.getAutorByGeburtsdatum(geburtsdatum);
    }

}



