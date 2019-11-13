package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.daos.pojos.Eleve;
import hei.projets7.mobiliti.pojos.Eleve;

public interface InscriptionEleveDao {

    public Eleve addEleve(Eleve eleve);
    public void modifyPassword(Integer idEleve, String Password);
}
