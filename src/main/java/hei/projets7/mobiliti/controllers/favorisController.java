package hei.projets7.mobiliti.controllers;

import hei.projets7.mobiliti.entity.Favoris;
import hei.projets7.mobiliti.services.FavorisServices;

import javax.ws.rs.*;

@Path("/favoris")
public class favorisController {

    @POST
    @Path("/create")
    public void createCity(@FormParam("destination_id") Integer destination_id, @FormParam("user_id") Integer user_id) {
        Favoris favoris=new Favoris(null,  user_id, destination_id);
        FavorisServices.getInstance().addFavoris(favoris);
    }

    @POST
    @Path("/delete")
    public void deleteCity(@FormParam("destination_id") Integer id_destination, @FormParam("user_id") Integer user_id){
        FavorisServices.getInstance().modifyFavoris(id_destination, user_id);
    }




}
