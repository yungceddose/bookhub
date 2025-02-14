package org.thws.bookhub.api.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import org.thws.bookhub.api.controller.VerlagController;
import org.thws.bookhub.domain.model.Verlag;

@Component
public class VerlagModelAssembler implements RepresentationModelAssembler<Verlag, EntityModel<Verlag>> {

    @Override
    public EntityModel<Verlag> toModel(Verlag verlag) {
        return EntityModel.of(verlag,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VerlagController.class).getAllVerlage(null)).withRel("verlage"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VerlagController.class).getVerlagByName(verlag.getName())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VerlagController.class).getVerlagBySitz(verlag.getSitz(), null)).withRel("sitz"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VerlagController.class).getVerlagByGruendungsjahr(verlag.getGruendungsjahr(), null)).withRel("gruendungsjahr"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VerlagController.class).updateVerlag(verlag.getId(), null)).withRel("update"),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(VerlagController.class).deleteVerlag(verlag.getId())).withRel("delete"));
    }
}
