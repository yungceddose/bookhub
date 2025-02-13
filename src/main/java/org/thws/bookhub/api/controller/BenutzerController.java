package org.thws.bookhub.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public BenutzerController(BenutzerService benutzerService) {
        this.benutzerService = benutzerService;
    }

    // Benutzer erstellen (POST)
    @PostMapping
    public Benutzer createBenutzer(@RequestBody Benutzer benutzer) {
        return benutzerService.addBenutzer(benutzer);
    }

    // Alle Benutzer abrufen (GET)
    @GetMapping
    public ResponseEntity<Page<Benutzer>> getAllBenutzer(Pageable pageable) {
        Page<Benutzer> benutzerPage = benutzerService.getAllBenutzer(pageable);
        return new ResponseEntity<>(benutzerPage, HttpStatus.OK);
    }

    // Benutzer nach ID abrufen (GET)
    @GetMapping("/{id}")
    public Optional<Benutzer> getBenutzerById(@PathVariable Long id) {
        return benutzerService.getBenutzerById(id);
    }

    // Benutzer aktualisieren (PUT)
    @PutMapping("/{id}")
    public Benutzer updateBenutzer(@PathVariable Long id, @RequestBody Benutzer benutzer) {
        return benutzerService.updateBenutzer(id, benutzer);
    }

    // Benutzer löschen (DELETE)
    @DeleteMapping("/{id}")
    public void deleteBenutzer(@PathVariable Long id) {
        benutzerService.deleteBenutzer(id);
    }

    // Benutzer nach E-Mail abrufen (GET)
    @GetMapping("/by-email")
    public Benutzer getBenutzerByEmail(@RequestParam String email) {
        return benutzerService.findBenutzerByEmail(email);
    }

    // Benutzer nach Name abrufen (GET)
    @GetMapping("/by-name")
    public ResponseEntity<Page<Benutzer>> getBenutzerByName(@RequestParam String name, Pageable pageable) {
        Page<Benutzer> benutzerPage = benutzerService.getBenutzerByName(name, pageable);
        return new ResponseEntity<>(benutzerPage, HttpStatus.OK);
    }

}
