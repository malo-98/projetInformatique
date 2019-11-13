package hei.projets7.mobiliti.services;

import hei.projets7.mobiliti.pojos.Eleve;

public class EleveServices {

    public Eleve addEleve(String nom, String prenom, String email, String password, String domaine){
        Eleve eleve=new Eleve(nom, prenom, email, password, domaine);
        return eleve;
    }
}
