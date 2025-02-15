package org.thws.bookhub.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.thws.bookhub.api.assembler.BuchModelAssembler;
import org.thws.bookhub.domain.model.Buch;
import org.thws.bookhub.domain.model.Genre;
import org.thws.bookhub.domain.service.BuchService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BuchController.class)
class BuchControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BuchService buchService;

    @MockBean
    private BuchModelAssembler assembler;

    @MockBean
    private PagedResourcesAssembler<Buch> pagedResourcesAssembler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBuch() throws Exception {
        Buch buch = new Buch();
        buch.setIsbnNummer("123456789");

        given(buchService.createBuch(any(Buch.class))).willReturn(buch);

        mockMvc.perform(post("/api/buecher")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titel\": \"Buchtitel\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllBuecher() throws Exception {
        Page<Buch> page = new PageImpl<>(Arrays.asList(new Buch(), new Buch()));
        given(buchService.findAllBuecher(any())).willReturn(page);
        given(pagedResourcesAssembler.toModel(eq(page), any(BuchModelAssembler.class)))
                .willReturn(PagedModel.of(Arrays.asList(EntityModel.of(new Buch())), new PagedModel.PageMetadata(2, 0, 2)));

        mockMvc.perform(get("/api/buecher"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBuchByIsbn() throws Exception {
        Buch buch = new Buch();
        buch.setIsbnNummer("123456789");
        given(buchService.findBuchById("123456789")).willReturn(Optional.of(buch));
        given(assembler.toModel(any())).willReturn(EntityModel.of(buch));

        mockMvc.perform(get("/api/buecher/123456789"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateBuch() throws Exception {
        Buch buch = new Buch();
        buch.setIsbnNummer("123456789");
        given(buchService.updateBuch(eq("123456789"), any(Buch.class))).willReturn(buch);

        mockMvc.perform(put("/api/buecher/123456789")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"titel\": \"Neuer Buchtitel\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteBuch() throws Exception {
        mockMvc.perform(delete("/api/buecher/123456789"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetBuecherByTitel() throws Exception {
        Page<Buch> page = new PageImpl<>(Arrays.asList(new Buch(), new Buch()));
        given(buchService.findBuecherByTitel(eq("Buchtitel"), any())).willReturn(page);
        given(pagedResourcesAssembler.toModel(eq(page), any(BuchModelAssembler.class)))
                .willReturn(PagedModel.of(Arrays.asList(EntityModel.of(new Buch())), new PagedModel.PageMetadata(2, 0, 2)));

        mockMvc.perform(get("/api/buecher/by-titel")
                        .param("titel", "Buchtitel"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBuecherByVeroeffentlichungsdatum() throws Exception {
        Page<Buch> page = new PageImpl<>(Arrays.asList(new Buch(), new Buch()));
        LocalDate date = LocalDate.of(2020, 1, 1);
        given(buchService.findBuecherByVeroeffentlichungsdatum(eq(date), any())).willReturn(page);
        given(pagedResourcesAssembler.toModel(eq(page), any(BuchModelAssembler.class)))
                .willReturn(PagedModel.of(Arrays.asList(EntityModel.of(new Buch())), new PagedModel.PageMetadata(2, 0, 2)));

        mockMvc.perform(get("/api/buecher/by-veroeffentlichungsdatum")
                        .param("veroeffentlichungsdatum", "2020-01-01"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBuecherBySeitenanzahl() throws Exception {
        Page<Buch> page = new PageImpl<>(Arrays.asList(new Buch(), new Buch()));
        given(buchService.findBuecherBySeitenanzahl(eq(300), any())).willReturn(page);
        given(pagedResourcesAssembler.toModel(eq(page), any(BuchModelAssembler.class)))
                .willReturn(PagedModel.of(Arrays.asList(EntityModel.of(new Buch())), new PagedModel.PageMetadata(2, 0, 2)));

        mockMvc.perform(get("/api/buecher/by-seitenanzahl")
                        .param("seitenanzahl", "300"))
                .andExpect(status().isOk());
    }
}