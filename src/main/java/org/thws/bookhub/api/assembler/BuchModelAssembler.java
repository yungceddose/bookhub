package org.thws.bookhub.api.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import org.thws.bookhub.api.controller.BuchController;
import org.thws.bookhub.domain.model.Buch;

@Component
public class BuchModelAssembler implements RepresentationModelAssembler<Buch, EntityModel<Buch>> {

    @Override
    public EntityModel<Buch> toModel(Buch buch) {
        return EntityModel.of(buch,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BuchController.class).getBuchByIsbn(buch.getIsbnNummer())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BuchController.class).getAllBuecher(null)).withRel("buecher"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BuchController.class).getBuecherByTitel(buch.getTitel(), null)).withRel("search-by-title"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BuchController.class).getBuecherByVeroeffentlichungsdatum(buch.getVeroeffentlichungsdatum(), null)).withRel("search-by-release-date"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BuchController.class).getBuecherByGenre(buch.getGenre(), null)).withRel("search-by-genre"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BuchController.class).getBuecherBySeitenanzahl(buch.getSeitenanzahl(), null)).withRel("search-by-page-count"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BuchController.class).updateBuch(buch.getIsbnNummer(), null)).withRel("update"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BuchController.class).deleteBuch(buch.getIsbnNummer())).withRel("delete"));
    }
}
