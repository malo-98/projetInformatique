package hei.projets7.mobiliti.controllers;

import hei.projets7.mobiliti.exception.DonneIllegalFormatException;
import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.services.DestinationServices;
import hei.projets7.mobiliti.services.EleveServices;

import javax.ws.rs.*;

@Path("/destination")
public class DestinationController {

    @POST
    @Path("/delete")
    public void deleteDestination(
            @FormParam("id_destination") Integer id_destination) throws DonneIllegalFormatException {
        DestinationServices.getInstance().deleteDestination(id_destination);
    }
}
