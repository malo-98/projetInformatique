package hei.projets7.mobiliti.controllers;

import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.pojos.Eleve;
import hei.projets7.mobiliti.services.EleveServices;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/eleve")
public class eleveController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Eleve> listEleves() {
        return EleveServices.getInstance().listEleve();
    }

    @DELETE
    @Path("/{email}")
    public void deleteEleve(
            @PathParam("email") String email
    ) throws EleveNotFoundException {
        EleveServices.getInstance().deleteUser(email);
    }
}
