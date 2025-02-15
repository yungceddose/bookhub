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
import org.thws.bookhub.domain.model.Verlag;
import org.thws.bookhub.persistence.VerlagRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VerlagServiceTest {

    @Mock
    private VerlagRepository verlagRepository;

    @InjectMocks
    private VerlagService verlagService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateVerlag() {
        Verlag verlag = new Verlag();
        when(verlagRepository.save(any(Verlag.class))).thenReturn(verlag);

        Verlag result = verlagService.createVerlag(verlag);
        assertNotNull(result);
        verify(verlagRepository, times(1)).save(verlag);
    }

    @Test
    void testFindAllVerlage() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Verlag> verlagPage = new PageImpl<>(Arrays.asList(new Verlag(), new Verlag()));
        when(verlagRepository.findAll(pageable)).thenReturn(verlagPage);

        Page<Verlag> result = verlagService.findAllVerlage(pageable);
        assertEquals(2, result.getTotalElements());
        verify(verlagRepository, times(1)).findAll(pageable);
    }

    @Test
    void testUpdateVerlag() {
        Verlag verlag = new Verlag();
        when(verlagRepository.existsById(1L)).thenReturn(true);
        when(verlagRepository.save(any(Verlag.class))).thenReturn(verlag);

        Verlag result = verlagService.updateVerlag(1L, verlag);
        assertNotNull(result);
        verify(verlagRepository, times(1)).save(verlag);
    }

    @Test
    void testDeleteVerlag() {
        verlagService.deleteVerlag(1L);
        verify(verlagRepository, times(1)).deleteById(1L);
    }

    @Test
    void testFindVerlagByName() {
        Verlag verlag = new Verlag();
        when(verlagRepository.findByName("TestVerlag")).thenReturn(verlag);

        Verlag result = verlagService.findVerlagByName("TestVerlag");
        assertNotNull(result);
        verify(verlagRepository, times(1)).findByName("TestVerlag");
    }

    @Test
    void testFindVerlagBySitz() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Verlag> verlagPage = new PageImpl<>(Arrays.asList(new Verlag(), new Verlag()));
        when(verlagRepository.findBySitz("Berlin", pageable)).thenReturn(verlagPage);

        Page<Verlag> result = verlagService.findVerlagBySitz("Berlin", pageable);
        assertEquals(2, result.getTotalElements());
        verify(verlagRepository, times(1)).findBySitz("Berlin", pageable);
    }

    @Test
    void testFindVerlagByGruendungsjahr() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Verlag> verlagPage = new PageImpl<>(Arrays.asList(new Verlag(), new Verlag()));
        when(verlagRepository.findByGruendungsjahr(2000, pageable)).thenReturn(verlagPage);

        Page<Verlag> result = verlagService.findVerlagByGruendungsjahr(2000, pageable);
        assertEquals(2, result.getTotalElements());
        verify(verlagRepository, times(1)).findByGruendungsjahr(2000, pageable);
    }
}