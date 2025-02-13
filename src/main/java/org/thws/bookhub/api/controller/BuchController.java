package org.thws.bookhub.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public BuchController(BuchService buchService) {
        this.buchService = buchService;
    }

    // Buch erstellen (POST)
    @PostMapping
    public Buch createBuch(@RequestBody Buch buch) {
        return buchService.createBuch(buch);
    }

    // Alle Bücher abrufen (GET)
    @GetMapping
    public ResponseEntity<Page<Buch>> getAllBuecher(Pageable pageable) {
        Page<Buch> buchPage = buchService.findAllBuecher(pageable);
        return new ResponseEntity<>(buchPage, HttpStatus.OK);
    }

    // Buch nach ISBN abrufen (GET)
    @GetMapping("/{isbn}")
    public Optional<Buch> getBuchByIsbn(@PathVariable String isbn) {
        return buchService.findBuchById(isbn);
    }

    // Buch aktualisieren (PUT)
    @PutMapping("/{isbn}")
    public Buch updateBuch(@PathVariable String isbn, @RequestBody Buch buch) {
        return buchService.updateBuch(isbn, buch);
    }

    // Buch löschen (DELETE)
    @DeleteMapping("/{isbn}")
    public void deleteBuch(@PathVariable String isbn) {
        buchService.deleteBuch(isbn);
    }

    // Bücher nach Titel suchen (GET)
    @GetMapping("/by-titel")
    public ResponseEntity<Page<Buch>> getBuecherByTitel(@RequestParam String titel, Pageable pageable) {
        Page<Buch> buchPage = buchService.findBuecherByTitel(titel, pageable);
        return new ResponseEntity<>(buchPage, HttpStatus.OK);
    }

    // Bücher nach Veröffentlichungsdatum suchen (GET)
    @GetMapping("/by-veroeffentlichungsdatum")
    public ResponseEntity<Page<Buch>> getBuecherByVeroeffentlichungsdatum(@RequestParam LocalDate veroeffentlichungsdatum, Pageable pageable) {
        Page<Buch> buchPage = buchService.findBuecherByVeroeffentlichungsdatum(veroeffentlichungsdatum, pageable);
        return new ResponseEntity<>(buchPage, HttpStatus.OK);
    }

    // Bücher nach Genre suchen (GET)
    @GetMapping("/by-genre")
    public ResponseEntity<Page<Buch>> getBuecherByGenre(@RequestParam Genre genre, Pageable pageable) {
        Page<Buch> buchPage = buchService.findBuecherByGenre(genre, pageable);
        return new ResponseEntity<>(buchPage, HttpStatus.OK);
    }

    // Bücher nach Seitenanzahl suchen (GET)
    @GetMapping("/by-seitenanzahl")
    public ResponseEntity<Page<Buch>> getBuecherBySeitenanzahl(@RequestParam int seitenanzahl, Pageable pageable) {
        Page<Buch> buchPage = buchService.findBuecherBySeitenanzahl(seitenanzahl, pageable);
        return new ResponseEntity<>(buchPage, HttpStatus.OK);
    }

}
