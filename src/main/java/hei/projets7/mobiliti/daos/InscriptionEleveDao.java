package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.pojos.Eleve;

import java.util.List;


public interface InscriptionEleveDao {

    Eleve addEleve(Eleve eleve);
    List<Eleve> listEleve();

}
