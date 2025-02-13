package org.thws.bookhub.api.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public VerlagController(VerlagService verlagService) {
        this.verlagService = verlagService;
    }

    // Verlag erstellen (POST)
    @PostMapping
    public Verlag createVerlag(@RequestBody Verlag verlag) {
        return verlagService.createVerlag(verlag);
    }

    // Alle Verlage abrufen (GET)
    @GetMapping
    public ResponseEntity<Page<Verlag>> getAllVerlage(Pageable pageable) {
        Page<Verlag> verlagPage = verlagService.findAllVerlage(pageable);
        return new ResponseEntity<>(verlagPage, HttpStatus.OK);
    }

    // Verlag aktualisieren (PUT)
    @PutMapping("/{id}")
    public Verlag updateVerlag(@PathVariable Long id, @RequestBody Verlag verlag) {
        return verlagService.updateVerlag(id, verlag);
    }

    // Verlag löschen (DELETE)
    @DeleteMapping("/{id}")
    public void deleteVerlag(@PathVariable Long id) {
        verlagService.deleteVerlag(id);
    }

    // Verlag nach Name suchen (GET)
    @GetMapping("/by-name")
    public Verlag getVerlagByName(@RequestParam String name) {
        return verlagService.findVerlagByName(name);
    }

    // Verlag nach Sitz suchen (GET)
    @GetMapping("/by-sitz")
    public ResponseEntity<Page<Verlag>> getVerlagBySitz(@RequestParam String sitz, Pageable pageable) {
        Page<Verlag> verlagPage = verlagService.findVerlagBySitz(sitz, pageable);
        return new ResponseEntity<>(verlagPage, HttpStatus.OK);
    }

    // Verlag nach Gründungsjahr suchen (GET)
    @GetMapping("/by-gruendungsjahr")
    public ResponseEntity<Page<Verlag>> getVerlagByGruendungsjahr(@RequestParam Integer gruendungsjahr, Pageable pageable) {
        Page<Verlag> verlagPage = verlagService.findVerlagByGruendungsjahr(gruendungsjahr, pageable);
        return new ResponseEntity<>(verlagPage, HttpStatus.OK);
    }

}
