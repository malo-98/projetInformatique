package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.pojos.Eleve;

import java.util.List;

public interface ConnexionEleveDao {

    Eleve read(String login);
    List<Eleve> read();
<<<<<<< HEAD
    String getPasswordByEmail(String email);
    Eleve getEleve(String email);


=======
    void modifyPassword(String email, String Password);
    void deleteEleve(String email);
>>>>>>> da257079a4e287a5561e9ef3ce42fd292a45350b
}
