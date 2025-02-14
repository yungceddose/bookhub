package org.thws.bookhub.domain.service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @CacheEvict(value = "verlage", allEntries = true)
    public Verlag createVerlag(Verlag verlag) {
        return verlagRepository.save(verlag);
    }

    // Alle Verlage abrufen
    @Cacheable("verlage")
    public Page<Verlag> findAllVerlage(Pageable pageable) {
        return verlagRepository.findAll(pageable);
    }

    // 6. Verlag aktualisieren
    @CacheEvict(value = "verlage", key = "#id")
    public Verlag updateVerlag(Long id, Verlag verlag) {
        if (verlagRepository.existsById(id)) {
            verlag.setId(id); // ID beibehalten
            return verlagRepository.save(verlag);
        }
        return null;
    }

    // Verlag löschen
    @CacheEvict(value = "verlage", key = "#id")
    public void deleteVerlag(Long id) {
        verlagRepository.deleteById(id);
    }

    // Verlag nach Name suchen
    @Cacheable(value = "verlage", key = "#name")
    public Verlag findVerlagByName(String name) {
        return verlagRepository.findByName(name);
    }

    // Verlag nach Sitz suchen
    @Cacheable(value = "verlage", key = "#sitz")
    public Page<Verlag> findVerlagBySitz(String sitz, Pageable pageable) {
        return verlagRepository.findBySitz(sitz, pageable);
    }

    // Verlag nach Gründungsjahr suchen
    @Cacheable(value = "verlage", key = "#gruendungsjahr")
    public Page<Verlag> findVerlagByGruendungsjahr(Integer gruendungsjahr, Pageable pageable) {
        return verlagRepository.findByGruendungsjahr(gruendungsjahr, pageable);
    }

}
