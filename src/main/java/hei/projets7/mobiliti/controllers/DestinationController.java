package hei.projets7.mobiliti.controllers;

import hei.projets7.mobiliti.exception.DonneIllegalFormatException;
import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.services.DestinationServices;
import hei.projets7.mobiliti.services.EleveServices;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/destination?id=")
public class DestinationController {

    @DELETE
    @Path("{id_destination}")
    public void deleteDestination(
            @PathParam("id_destination") Integer id_destination) throws DonneIllegalFormatException {
        DestinationServices.getInstance().deleteDestination(id_destination);
    }
}
