package org.thws.bookhub.api.controller;

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

    @Autowired
    public BewertungController(BewertungService bewertungService) {
        this.bewertungService = bewertungService;
    }

    // Bewertung erstellen (POST)
    @PostMapping
    public Bewertung createBewertung(@RequestBody Bewertung bewertung) {
        return bewertungService.createBewertung(bewertung);
    }

    // Alle Bewertungen abrufen (GET)
    @GetMapping
    public List<Bewertung> getAllBewertungen() {
        return bewertungService.findAllBewertungen();
    }

    // 3. Bewertung nach ID abrufen (GET)
    @GetMapping("/{id}")
    public Optional<Bewertung> getBewertungById(@PathVariable Long id) {
        return bewertungService.findBewertungById(id);
    }

    // Bewertung aktualisieren (PUT)
    @PutMapping("/{id}")
    public Bewertung updateBewertung(@PathVariable Long id, @RequestBody Bewertung bewertung) {
        return bewertungService.updateBewertung(id, bewertung);
    }

    // Bewertung löschen (DELETE)
    @DeleteMapping("/{id}")
    public void deleteBewertung(@PathVariable Long id) {
        bewertungService.deleteBewertung(id);
    }

    // Bewertungen nach Punktzahl suchen (GET)
    @GetMapping("/by-punktzahl")
    public List<Bewertung> getBewertungenByPunktzahl(@RequestParam int punktzahl) {
        return bewertungService.findBewertungenByPunktzahl(punktzahl);
    }

}
