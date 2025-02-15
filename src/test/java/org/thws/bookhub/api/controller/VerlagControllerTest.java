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
import org.thws.bookhub.api.assembler.VerlagModelAssembler;
import org.thws.bookhub.domain.model.Verlag;
import org.thws.bookhub.domain.service.VerlagService;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VerlagController.class)
class VerlagControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VerlagService verlagService;

    @MockBean
    private VerlagModelAssembler assembler;

    @MockBean
    private PagedResourcesAssembler<Verlag> pagedResourcesAssembler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateVerlag() throws Exception {
        Verlag verlag = new Verlag();
        verlag.setId(1L);
        verlag.setName("Sample Verlag");

        given(verlagService.createVerlag(any(Verlag.class))).willReturn(verlag);

        mockMvc.perform(post("/api/verlage")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Sample Verlag\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllVerlage() throws Exception {
        Page<Verlag> page = new PageImpl<>(Arrays.asList(new Verlag(), new Verlag()));
        given(verlagService.findAllVerlage(any())).willReturn(page);
        given(pagedResourcesAssembler.toModel(eq(page), any(VerlagModelAssembler.class)))
                .willReturn(PagedModel.of(Arrays.asList(EntityModel.of(new Verlag())), new PagedModel.PageMetadata(2, 0, 2)));

        mockMvc.perform(get("/api/verlage"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateVerlag() throws Exception {
        Verlag verlag = new Verlag();
        given(verlagService.updateVerlag(any(Long.class), any(Verlag.class))).willReturn(verlag);

        mockMvc.perform(put("/api/verlage/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Sample Verlag\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteVerlag() throws Exception {
        mockMvc.perform(delete("/api/verlage/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testGetVerlagByName() throws Exception {
        Verlag verlag = new Verlag();
        verlag.setName("Sample Verlag");

        given(verlagService.findVerlagByName(eq("Sample Verlag"))).willReturn(verlag);
        given(assembler.toModel(any())).willReturn(EntityModel.of(verlag));

        mockMvc.perform(get("/api/verlage/by-name")
                        .param("name", "Sample Verlag"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetVerlagBySitz() throws Exception {
        Page<Verlag> page = new PageImpl<>(Arrays.asList(new Verlag(), new Verlag()));
        given(verlagService.findVerlagBySitz(eq("Berlin"), any())).willReturn(page);
        given(pagedResourcesAssembler.toModel(eq(page), any(VerlagModelAssembler.class)))
                .willReturn(PagedModel.of(Arrays.asList(EntityModel.of(new Verlag())), new PagedModel.PageMetadata(2, 0, 2)));

        mockMvc.perform(get("/api/verlage/by-sitz")
                        .param("sitz", "Berlin"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetVerlagByGruendungsjahr() throws Exception {
        Page<Verlag> page = new PageImpl<>(Arrays.asList(new Verlag(), new Verlag()));
        given(verlagService.findVerlagByGruendungsjahr(eq(2000), any())).willReturn(page);
        given(pagedResourcesAssembler.toModel(eq(page), any(VerlagModelAssembler.class)))
                .willReturn(PagedModel.of(Arrays.asList(EntityModel.of(new Verlag())), new PagedModel.PageMetadata(2, 0, 2)));

        mockMvc.perform(get("/api/verlage/by-gruendungsjahr")
                        .param("gruendungsjahr", "2000"))
                .andExpect(status().isOk());
    }
}