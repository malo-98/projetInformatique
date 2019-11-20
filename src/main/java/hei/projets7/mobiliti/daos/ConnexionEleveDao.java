package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.pojos.Eleve;

import java.util.List;

public interface ConnexionEleveDao {

    Eleve read(String login);
    List<Eleve> read();
    void modifyPassword(Integer idEleve, String Password);
}
