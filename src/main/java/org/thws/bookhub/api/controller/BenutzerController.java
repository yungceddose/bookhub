package org.thws.bookhub.api.controller;

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
    public List<Benutzer> getAllBenutzer() {
        return benutzerService.getAllBenutzer();
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
    public List<Benutzer> getBenutzerByName(@RequestParam String name) {
        return benutzerService.getBenutzerByName(name);
    }

}
