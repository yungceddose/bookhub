package org.thws.bookhub.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.thws.bookhub.domain.model.Bewertung;
import org.thws.bookhub.persistence.BewertungRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BewertungServiceTest {

    @Mock
    private BewertungRepository bewertungRepository;

    @InjectMocks
    private BewertungService bewertungService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBewertung() {
        Bewertung bewertung = new Bewertung();
        when(bewertungRepository.save(any(Bewertung.class))).thenReturn(bewertung);

        Bewertung result = bewertungService.createBewertung(bewertung);
        assertNotNull(result);
        verify(bewertungRepository, times(1)).save(bewertung);
    }

    @Test
    void testFindAllBewertungen() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Bewertung> bewertungPage = new PageImpl<>(Arrays.asList(new Bewertung(), new Bewertung()));
        when(bewertungRepository.findAll(pageable)).thenReturn(bewertungPage);

        Page<Bewertung> result = bewertungService.findAllBewertungen(pageable);
        assertEquals(2, result.getTotalElements());
        verify(bewertungRepository, times(1)).findAll(pageable);
    }

    @Test
    void testFindBewertungById() {
        Bewertung bewertung = new Bewertung();
        when(bewertungRepository.findById(1L)).thenReturn(Optional.of(bewertung));

        Optional<Bewertung> result = bewertungService.findBewertungById(1L);
        assertTrue(result.isPresent());
        verify(bewertungRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateBewertung() {
        Bewertung bewertung = new Bewertung();
        when(bewertungRepository.existsById(1L)).thenReturn(true);
        when(bewertungRepository.save(any(Bewertung.class))).thenReturn(bewertung);

        Bewertung result = bewertungService.updateBewertung(1L, bewertung);
        assertNotNull(result);
        verify(bewertungRepository, times(1)).save(bewertung);
    }

    @Test
    void testDeleteBewertung() {
        bewertungService.deleteBewertung(1L);
        verify(bewertungRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindBewertungenByPunktzahl() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Bewertung> bewertungPage = new PageImpl<>(Arrays.asList(new Bewertung(), new Bewertung()));
        when(bewertungRepository.findByPunktzahl(5, pageable)).thenReturn(bewertungPage);

        Page<Bewertung> result = bewertungService.findBewertungenByPunktzahl(5, pageable);
        assertEquals(2, result.getTotalElements());
        verify(bewertungRepository, times(1)).findByPunktzahl(5, pageable);
    }
}