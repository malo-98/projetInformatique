package hei.projets7.mobiliti.controllers;

import hei.projets7.mobiliti.pojos.Favoris;
import hei.projets7.mobiliti.services.FavorisServices;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

public class favorisController {

    public void createCity(Integer id_destination,  Integer id_eleve){
        Favoris favoris=new Favoris(null, id_eleve,id_destination);
        FavorisServices.getInstance().addFavoris(favoris);
    }

    public void deleteCity(Integer id_destination){
        FavorisServices.getInstance().modifyFavoris(id_destination);
    }




}
