package org.thws.bookhub.api.controller;

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
    public List<Verlag> getAllVerlage() {
        return verlagService.findAllVerlage();
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
    public List<Verlag> getVerlagBySitz(@RequestParam String sitz) {
        return verlagService.findVerlagBySitz(sitz);
    }

    // Verlag nach Gründungsjahr suchen (GET)
    @GetMapping("/by-gruendungsjahr")
    public List<Verlag> getVerlagByGruendungsjahr(@RequestParam Integer gruendungsjahr) {
        return verlagService.findVerlagByGruendungsjahr(gruendungsjahr);
    }

}
