package hei.projets7.mobiliti.services;

import hei.projets7.mobiliti.daos.InscriptionEleveDao;
import hei.projets7.mobiliti.daos.impl.InscriptionEleveDaoImpl;
import hei.projets7.mobiliti.pojos.Eleve;

public class EleveServices {

    private InscriptionEleveDao inscriptionEleveDao = new InscriptionEleveDaoImpl();

    public Eleve addEleve(Eleve eleve) {
        if(eleve == null) {
            throw new IllegalArgumentException("The eleve can not be null.");
        }
        if(eleve.getNom() == null || " ".equals(eleve.getNom())){
            throw new IllegalArgumentException("The Nom can not be null.");
        }
        if(eleve.getPrenom() == null || " ".equals(eleve.getPrenom())){
            throw new IllegalArgumentException("The Prenom can not be null.");
        }
        if(eleve.getEmail() == null || " ".equals(eleve.getEmail())){
            throw new IllegalArgumentException("The Email can not be null.");
        }
        if(eleve.getPassword() == null || " ".equals(eleve.getPassword())){
            throw new IllegalArgumentException("The Password can not be null.");
        }
        if(eleve.getDomaine() == null || " ".equals(eleve.getDomaine())){
            throw new IllegalArgumentException("The Domaine can not be null.");
        }

        return inscriptionEleveDao.addEleve(eleve);
    }



}
