package org.thws.bookhub.domain.service;
import org.thws.bookhub.domain.model.Verlag;
import org.thws.bookhub.persistence.VerlagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VerlagService {

    private final VerlagRepository verlagRepository;

    @Autowired
    public VerlagService(VerlagRepository verlagRepository) {
        this.verlagRepository = verlagRepository;
    }

    // Verlag erstellen
    public Verlag createVerlag(Verlag verlag) {
        return verlagRepository.save(verlag);
    }

    // Alle Verlage abrufen
    public List<Verlag> findAllVerlage() {
        return verlagRepository.findAll();
    }

    // 6. Verlag aktualisieren
    public Verlag updateVerlag(Long id, Verlag verlag) {
        if (verlagRepository.existsById(id)) {
            verlag.setId(id); // ID beibehalten
            return verlagRepository.save(verlag);
        }
        return null;
    }

    // Verlag löschen
    public void deleteVerlag(Long id) {
        verlagRepository.deleteById(id);
    }

    // Verlag nach Name suchen
    public Verlag findVerlagByName(String name) {
        return verlagRepository.findByName(name);
    }

    // Verlag nach Sitz suchen
    public List<Verlag> findVerlagBySitz(String sitz) {
        return verlagRepository.findBySitz(sitz);
    }

    // Verlag nach Gründungsjahr suchen
    public List<Verlag> findVerlagByGruendungsjahr(Integer gruendungsjahr) {
        return verlagRepository.findByGruendungsjahr(gruendungsjahr);
    }

}
