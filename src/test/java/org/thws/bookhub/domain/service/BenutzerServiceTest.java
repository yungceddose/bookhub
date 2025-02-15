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
import org.thws.bookhub.domain.model.Benutzer;
import org.thws.bookhub.persistence.BenutzerRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BenutzerServiceTest {

    @Mock
    private BenutzerRepository benutzerRepository;

    @InjectMocks
    private BenutzerService benutzerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBenutzer() {
        Benutzer benutzer = new Benutzer();
        when(benutzerRepository.save(any(Benutzer.class))).thenReturn(benutzer);

        Benutzer result = benutzerService.addBenutzer(benutzer);
        assertNotNull(result);
        verify(benutzerRepository, times(1)).save(benutzer);
    }

    @Test
    void testGetAllBenutzer() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Benutzer> benutzerList = Arrays.asList(new Benutzer(), new Benutzer());
        Page<Benutzer> benutzerPage = new PageImpl<>(benutzerList);

        when(benutzerRepository.findAll(pageable)).thenReturn(benutzerPage);

        Page<Benutzer> result = benutzerService.getAllBenutzer(pageable);
        assertEquals(2, result.getTotalElements());
        verify(benutzerRepository, times(1)).findAll(pageable);
    }

    @Test
    void testGetBenutzerById() {
        Benutzer benutzer = new Benutzer();
        when(benutzerRepository.findById(1L)).thenReturn(Optional.of(benutzer));

        Optional<Benutzer> result = benutzerService.getBenutzerById(1L);
        assertTrue(result.isPresent());
        verify(benutzerRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateBenutzer() {
        Benutzer benutzer = new Benutzer();
        when(benutzerRepository.existsById(1L)).thenReturn(true);
        when(benutzerRepository.save(any(Benutzer.class))).thenReturn(benutzer);

        Benutzer result = benutzerService.updateBenutzer(1L, benutzer);
        assertNotNull(result);
        verify(benutzerRepository, times(1)).save(benutzer);
    }

    @Test
    void testDeleteBenutzer() {
        benutzerService.deleteBenutzer(1L);
        verify(benutzerRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetBenutzerByName() {
        Pageable pageable = PageRequest.of(0, 10);
        List<Benutzer> benutzerList = Arrays.asList(new Benutzer(), new Benutzer());
        Page<Benutzer> benutzerPage = new PageImpl<>(benutzerList);

        when(benutzerRepository.findByName("name", pageable)).thenReturn(benutzerPage);

        Page<Benutzer> result = benutzerService.getBenutzerByName("name", pageable);
        assertEquals(2, result.getTotalElements());
        verify(benutzerRepository, times(1)).findByName("name", pageable);
    }

    @Test
    void testFindBenutzerByEmail() {
        Benutzer benutzer = new Benutzer();
        when(benutzerRepository.findByEmail("test@example.com")).thenReturn(benutzer);

        Benutzer result = benutzerService.findBenutzerByEmail("test@example.com");
        assertNotNull(result);
        verify(benutzerRepository, times(1)).findByEmail("test@example.com");
    }
}