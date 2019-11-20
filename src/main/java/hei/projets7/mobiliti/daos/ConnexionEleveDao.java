package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.pojos.Eleve;

import java.util.List;

public interface ConnexionEleveDao {

    Eleve read(String email);
    List<Eleve> read();
    void modifyPassword(Integer id, String Password);
    void deleteEleve(Integer id);

}
