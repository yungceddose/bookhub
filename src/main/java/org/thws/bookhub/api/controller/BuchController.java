package org.thws.bookhub.api.controller;

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
    public List<Buch> getAllBuecher() {
        return buchService.findAllBuecher();
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
    public List<Buch> getBuecherByTitel(@RequestParam String titel) {
        return buchService.findBuecherByTitel(titel);
    }

    // Bücher nach Veröffentlichungsdatum suchen (GET)
    @GetMapping("/by-veroeffentlichungsdatum")
    public List<Buch> getBuecherByVeroeffentlichungsdatum(@RequestParam LocalDate veroeffentlichungsdatum) {
        return buchService.findBuecherByVeroeffentlichungsdatum(veroeffentlichungsdatum);
    }

    // Bücher nach Genre suchen (GET)
    @GetMapping("/by-genre")
    public List<Buch> getBuecherByGenre(@RequestParam Genre genre) {
        return buchService.findBuecherByGenre(genre);
    }

    // Bücher nach Seitenanzahl suchen (GET)
    @GetMapping("/by-seitenanzahl")
    public List<Buch> getBuecherBySeitenanzahl(@RequestParam int seitenanzahl) {
        return buchService.findBuecherBySeitenanzahl(seitenanzahl);
    }

}
