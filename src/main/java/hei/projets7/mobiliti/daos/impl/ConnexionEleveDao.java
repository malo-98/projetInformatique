package hei.projets7.mobiliti.daos.impl;

import hei.projets7.mobiliti.pojos.Eleve;

import java.util.List;

public interface ConnexionEleveDao {

    Eleve read(String login);
    List<Eleve> read();
}
