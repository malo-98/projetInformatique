package hei.projets7.mobiliti.controllers;

import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.pojos.Eleve;
import hei.projets7.mobiliti.services.EleveServices;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/listEleve")
public class eleveController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Eleve> listEleve(){
        return EleveServices.getInstance().listEleve();
    }

    @DELETE
    @Path("/{eleveEmail}")
    public void deleteCity(
            @PathParam("eleveEmail") String eleveEmail) throws EleveNotFoundException {
        EleveServices.getInstance().deleteUser(eleveEmail);
    }
}
