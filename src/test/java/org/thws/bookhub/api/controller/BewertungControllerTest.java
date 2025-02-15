package org.thws.bookhub.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.thws.bookhub.api.assembler.BewertungModelAssembler;
import org.thws.bookhub.domain.model.Bewertung;
import org.thws.bookhub.domain.service.BewertungService;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BewertungController.class)
class BewertungControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BewertungService bewertungService;

    @MockBean
    private BewertungModelAssembler assembler;

    @MockBean
    private PagedResourcesAssembler<Bewertung> pagedResourcesAssembler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBewertung() throws Exception {
        Bewertung bewertung = new Bewertung();
        bewertung.setId(1L);

        given(bewertungService.createBewertung(any(Bewertung.class))).willReturn(bewertung);

        mockMvc.perform(post("/api/bewertungen")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"punktzahl\": 5}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllBewertungen() throws Exception {
        Page<Bewertung> page = new PageImpl<>(Arrays.asList(new Bewertung(), new Bewertung()));
        given(bewertungService.findAllBewertungen(any())).willReturn(page);
        given(pagedResourcesAssembler.toModel(eq(page), any(BewertungModelAssembler.class)))
                .willReturn(PagedModel.of(Arrays.asList(EntityModel.of(new Bewertung())), new PagedModel.PageMetadata(2, 0, 2)));

        mockMvc.perform(get("/api/bewertungen"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBewertungById() throws Exception {
        Bewertung bewertung = new Bewertung();
        bewertung.setId(1L);
        given(bewertungService.findBewertungById(1L)).willReturn(Optional.of(bewertung));
        given(assembler.toModel(any())).willReturn(EntityModel.of(bewertung));

        mockMvc.perform(get("/api/bewertungen/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateBewertung() throws Exception {
        Bewertung bewertung = new Bewertung();
        bewertung.setId(1L);
        given(bewertungService.updateBewertung(eq(1L), any(Bewertung.class))).willReturn(bewertung);

        mockMvc.perform(put("/api/bewertungen/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"punktzahl\": 5}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteBewertung() throws Exception {
        mockMvc.perform(delete("/api/bewertungen/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetBewertungenByPunktzahl() throws Exception {
        Page<Bewertung> page = new PageImpl<>(Arrays.asList(new Bewertung(), new Bewertung()));
        given(bewertungService.findBewertungenByPunktzahl(eq(5), any())).willReturn(page);
        given(pagedResourcesAssembler.toModel(eq(page), any(BewertungModelAssembler.class)))
                .willReturn(PagedModel.of(Arrays.asList(EntityModel.of(new Bewertung())), new PagedModel.PageMetadata(2, 0, 2)));

        mockMvc.perform(get("/api/bewertungen/by-punktzahl")
                        .param("punktzahl", "5"))
                .andExpect(status().isOk());
    }
}