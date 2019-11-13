package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.pojos.Eleve;


public interface InscriptionEleveDao {

    Eleve addEleve(Eleve eleve);
    void modifyPassword(Integer idEleve, String Password);
}
