package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.entity.Eleve;

import java.util.List;

public interface ConnexionEleveDao {

    Eleve read(String email);
    List<Eleve> read();
    void modifyPassword(Integer id, String Password);
    public void modifyDomaine(Integer id, String domaine);
    public void modifyNom(Integer id, String nom);
    public void modifyPrenom(Integer id, String prenom);
    public void modifyEmail(Integer id, String email);
    void deleteEleve(Integer id);

}
