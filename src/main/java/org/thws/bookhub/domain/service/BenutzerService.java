package org.thws.bookhub.domain.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.thws.bookhub.domain.model.Benutzer;
import org.thws.bookhub.persistence.BenutzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BenutzerService {

    private final BenutzerRepository benutzerRepository;

    @Autowired
    public BenutzerService(BenutzerRepository benutzerRepository) {
        this.benutzerRepository = benutzerRepository;
    }

    // Benutzer erstellen
    public Benutzer addBenutzer(Benutzer benutzer) {
        return benutzerRepository.save(benutzer);
    }

    // Alle Benutzer abrufen
    public Page<Benutzer> getAllBenutzer(Pageable pageable) {
        return benutzerRepository.findAllBy(pageable);
    }

    // Benutzer nach ID abrufen
    public Optional<Benutzer> getBenutzerById(Long id) {
        return benutzerRepository.findById(id);
    }

    // Benutzer aktualisieren
    public Benutzer updateBenutzer(Long id, Benutzer benutzer) {
        if (benutzerRepository.existsById(id)) {
            benutzer.setId(id); // ID beibehalten
            return benutzerRepository.save(benutzer);
        }
        return null;
    }

    // Benutzer löschen
    public void deleteBenutzer(Long id) {
        benutzerRepository.deleteById(id);
    }

    // Benutzer nach Name abrufen
    public Page<Benutzer> getBenutzerByName(String name, Pageable pageable) {
        return benutzerRepository.findByName(name, pageable);
    }

    // Benutzer nach E-Mail abrufen
    public Benutzer findBenutzerByEmail(String email) {
        return benutzerRepository.findByEmail(email);
    }

}
