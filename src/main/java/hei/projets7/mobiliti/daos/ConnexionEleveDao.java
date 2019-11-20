package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.pojos.Eleve;

import java.util.List;

public interface ConnexionEleveDao {

    Eleve read(String login);
    List<Eleve> read();
    String getPasswordByEmail(String email);
    Eleve getEleve(String email);
    void modifyPassword(String email, String Password);
    void deleteEleve(String email);
}
