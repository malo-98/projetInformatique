package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.pojos.Eleve;

import java.util.List;

public interface ConnexionEleveDao {

    Eleve read(String login);
    List<Eleve> read();
<<<<<<< HEAD
    String getPasswordByEmail(String email) throws EleveNotFoundException;
    Eleve getEleve(String email) throws EleveNotFoundException;
=======
    String getPasswordByEmail(String email);
    Eleve getEleve(String email);
>>>>>>> 4561af820c2925d257f8b336af00487ab728aea8
    void modifyPassword(String email, String Password);
    void deleteEleve(String email);
}
