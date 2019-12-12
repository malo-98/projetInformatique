package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.entity.Eleve;

import java.util.List;


public interface InscriptionEleveDao {

    Eleve addEleve(Eleve eleve);
    List<Eleve> listEleve();
    int countEleve();

}
