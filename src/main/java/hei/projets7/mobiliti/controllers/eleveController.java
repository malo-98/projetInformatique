package hei.projets7.mobiliti.controllers;

import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.services.EleveServices;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/listEleve")
public class eleveController {
    @DELETE
    @Path("/{eleveEmail}")
    public void deleteCity(
            @PathParam("eleveEmail") String eleveEmail) throws EleveNotFoundException {
        EleveServices.getInstance().deleteUser(eleveEmail);
    }
}
