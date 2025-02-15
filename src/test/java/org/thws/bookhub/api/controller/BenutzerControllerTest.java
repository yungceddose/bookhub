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
import org.thws.bookhub.api.assembler.BenutzerModelAssembler;
import org.thws.bookhub.domain.model.Benutzer;
import org.thws.bookhub.domain.service.BenutzerService;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BenutzerController.class)
class BenutzerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BenutzerService benutzerService;

    @MockBean
    private BenutzerModelAssembler assembler;

    @MockBean
    private PagedResourcesAssembler<Benutzer> pagedResourcesAssembler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBenutzer() throws Exception {
        Benutzer benutzer = new Benutzer();
        benutzer.setId(1L);

        given(benutzerService.addBenutzer(any(Benutzer.class))).willReturn(benutzer);

        mockMvc.perform(post("/api/benutzer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Max Mustermann\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllBenutzer() throws Exception {
        Page<Benutzer> page = new PageImpl<>(Arrays.asList(new Benutzer(), new Benutzer()));
        given(benutzerService.getAllBenutzer(any())).willReturn(page);
        given(pagedResourcesAssembler.toModel(eq(page), any(BenutzerModelAssembler.class)))
                .willReturn(PagedModel.of(Arrays.asList(EntityModel.of(new Benutzer())), new PagedModel.PageMetadata(2, 0, 2)));

        mockMvc.perform(get("/api/benutzer"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBenutzerById() throws Exception {
        Benutzer benutzer = new Benutzer();
        benutzer.setId(1L);
        given(benutzerService.getBenutzerById(1L)).willReturn(Optional.of(benutzer));
        given(assembler.toModel(any())).willReturn(EntityModel.of(benutzer));

        mockMvc.perform(get("/api/benutzer/1"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateBenutzer() throws Exception {
        Benutzer benutzer = new Benutzer();
        benutzer.setId(1L);
        given(benutzerService.updateBenutzer(eq(1L), any(Benutzer.class))).willReturn(benutzer);

        mockMvc.perform(put("/api/benutzer/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Max Mustermann\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteBenutzer() throws Exception {
        mockMvc.perform(delete("/api/benutzer/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetBenutzerByEmail() throws Exception {
        Benutzer benutzer = new Benutzer();
        benutzer.setEmail("test@example.com");
        given(benutzerService.findBenutzerByEmail("test@example.com")).willReturn(benutzer);
        given(assembler.toModel(any())).willReturn(EntityModel.of(benutzer));

        mockMvc.perform(get("/api/benutzer/by-email")
                        .param("email", "test@example.com"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetBenutzerByName() throws Exception {
        Page<Benutzer> page = new PageImpl<>(Arrays.asList(new Benutzer(), new Benutzer()));
        given(benutzerService.getBenutzerByName(eq("name"), any())).willReturn(page);
        given(pagedResourcesAssembler.toModel(eq(page), any(BenutzerModelAssembler.class)))
                .willReturn(PagedModel.of(Arrays.asList(EntityModel.of(new Benutzer())), new PagedModel.PageMetadata(2, 0, 2)));

        mockMvc.perform(get("/api/benutzer/by-name")
                        .param("name", "name"))
                .andExpect(status().isOk());
    }
}