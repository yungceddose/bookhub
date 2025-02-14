package org.thws.bookhub.api.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import org.thws.bookhub.api.controller.BewertungController;
import org.thws.bookhub.domain.model.Bewertung;

@Component
public class BewertungModelAssembler implements RepresentationModelAssembler<Bewertung, EntityModel<Bewertung>> {

    @Override
    public EntityModel<Bewertung> toModel(Bewertung bewertung) {
        return EntityModel.of(bewertung,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BewertungController.class).getBewertungById(bewertung.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BewertungController.class).getAllBewertungen(null)).withRel("bewertungen"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BewertungController.class).getBewertungenByPunktzahl(bewertung.getPunktzahl(), null)).withRel("search-by-score"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BewertungController.class).updateBewertung(bewertung.getId(), null)).withRel("update"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BewertungController.class).deleteBewertung(bewertung.getId())).withRel("delete"));
    }
}
