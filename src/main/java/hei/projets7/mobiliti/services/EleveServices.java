package hei.projets7.mobiliti.services;

import hei.projets7.mobiliti.pojos.Eleve;

public class EleveServices {

    public Eleve addEleve(Integer id,  String nom, String prenom, String email, String password, String domaine){
        Eleve eleve=new Eleve(id, nom, prenom, email, password, domaine);
        return eleve;
    }
}
