package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.pojos.Eleve;

import java.util.List;

public interface ConnexionEleveDao {

    Eleve read(String login);
    List<Eleve> read();
    String getPasswordByEmail(String email) throws EleveNotFoundException;
    Eleve getEleve(String email) throws EleveNotFoundException;
    void modifyPassword(String email, String Password);
    void deleteEleve(String email);
}
