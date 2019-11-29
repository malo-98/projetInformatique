package hei.projets7.mobiliti.services;

import hei.projets7.mobiliti.daos.InscriptionEleveDao;
import hei.projets7.mobiliti.daos.impl.ChoixDaoImpl;
import hei.projets7.mobiliti.daos.impl.ConnexionEleveDaoImpl;
import hei.projets7.mobiliti.daos.impl.InscriptionEleveDaoImpl;
import hei.projets7.mobiliti.exception.DonneIllegalFormatException;
import hei.projets7.mobiliti.exception.EleveAlreadyExistException;
import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.exception.PasswordIllegalFormatException;
import hei.projets7.mobiliti.pojos.Choix;
import hei.projets7.mobiliti.pojos.Eleve;

import java.util.ArrayList;
import java.util.List;


public class EleveServices {

    private InscriptionEleveDao inscriptionEleveDao = new InscriptionEleveDaoImpl();
    private ConnexionEleveDaoImpl connexionEleveDao = new ConnexionEleveDaoImpl();
    private ChoixDaoImpl choixDao = new ChoixDaoImpl();

    private static class EleveLibraryHolder {
        private final static EleveServices instance = new EleveServices();
    }


    public static EleveServices getInstance() {
        return EleveLibraryHolder.instance;
    }


    public Eleve getEleve(String email) throws EleveNotFoundException {
        Eleve eleve =connexionEleveDao.read(email);
        if (eleve!=null){
            return eleve;
        }
        throw new EleveNotFoundException(email);
    }


    public List<Eleve> listEleve() {
        return inscriptionEleveDao.listEleve();
    }


    public void modifyPassword(String email, String password) throws EleveNotFoundException, PasswordIllegalFormatException {
        Eleve eleve = getEleve(email);
        Integer id=eleve.getId_eleve();
        if (password==null || " ".equals(password)){
            throw new PasswordIllegalFormatException();
        }
        connexionEleveDao.modifyPassword(id, password);
    }

    public void modifyNom(String email, String nom) throws EleveNotFoundException, IllegalArgumentException, DonneIllegalFormatException {
        Eleve eleve = getEleve(email);
        Integer id=eleve.getId_eleve();
        if (nom==null || " ".equals(nom)){
            throw new DonneIllegalFormatException();
        }
        connexionEleveDao.modifyNom(id, nom);
    }

    public void modifyPrenom(String email, String prenom) throws EleveNotFoundException, IllegalArgumentException, DonneIllegalFormatException {
        Eleve eleve = getEleve(email);
        Integer id=eleve.getId_eleve();
        if (prenom==null || " ".equals(prenom)){
            throw new DonneIllegalFormatException();
        }
        connexionEleveDao.modifyPrenom(id, prenom);
    }

    public void modifyEmail(String email, String email2) throws EleveNotFoundException, IllegalArgumentException, DonneIllegalFormatException {
        Eleve eleve = getEleve(email);
        Integer id=eleve.getId_eleve();
        if (email2==null || " ".equals(email2)){
            throw new DonneIllegalFormatException();
        }
        connexionEleveDao.modifyEmail(id, email2);
    }

    public void modifyDomaine(String email, String domaine) throws EleveNotFoundException, IllegalArgumentException, DonneIllegalFormatException {
        Eleve eleve = getEleve(email);
        Integer id=eleve.getId_eleve();
        if (domaine==null || " ".equals(domaine)){
            throw new DonneIllegalFormatException();
        }
        connexionEleveDao.modifyDomaine(id, domaine);
    }



    public Eleve addEleve(Eleve eleve) throws EleveAlreadyExistException, DonneIllegalFormatException {

        List<Eleve> eleves = inscriptionEleveDao.listEleve();
        List<String> emails=new ArrayList<String>();
        for (int i=0;i<eleves.size();i++){
            emails.add(eleves.get(i).getEmail());
        }

        if(emails.contains(eleve.getEmail())) {
            throw new EleveAlreadyExistException(eleve);
        }

        if(eleve == null) {
            throw new DonneIllegalFormatException();
        }
        if(eleve.getNom() == null || " ".equals(eleve.getNom())){
            throw new DonneIllegalFormatException();
        }
        if(eleve.getPrenom() == null || " ".equals(eleve.getPrenom())){
            throw new DonneIllegalFormatException();
        }
        if(eleve.getEmail() == null || " ".equals(eleve.getEmail())){
            throw new DonneIllegalFormatException();
        }
        if(eleve.getPassword() == null || " ".equals(eleve.getPassword())){
            throw new DonneIllegalFormatException();
        }
        if(eleve.getDomaine() == null || " ".equals(eleve.getDomaine())){
            throw new DonneIllegalFormatException();
        }

        return inscriptionEleveDao.addEleve(eleve);
    }


    public boolean checkPassword(String email, String mdp) throws EleveNotFoundException {
        //LOGGER.info("Vérification du mot de passe pour le user {}", login);

        Eleve eleve = getEleve(email);
        return eleve.getPassword().equals(mdp);
    }


    public String getPasswordByEmail (String email) throws EleveNotFoundException {
        //Récupération des informations de connexion par le mail
        //LOGGER.info("Recuperation du password du user avec l'email {}",user.getPassword());
        Eleve eleve = getEleve(email);
        return eleve.getPassword();
    }


    public void deleteUser(String email) throws EleveNotFoundException {
        Eleve eleve=getEleve(email);
        connexionEleveDao.deleteEleve(eleve.getId_eleve());
    }

    public Integer getDestinationbyEmail (String email) throws EleveNotFoundException{
        Eleve eleve = getEleve(email);
        Integer id_eleve = eleve.getId_eleve();
        Choix choix_eleve = choixDao.read(id_eleve);

        return choix_eleve.getId_destination() ;
    }


}
