package hei.projets7.mobiliti.services;

import hei.projets7.mobiliti.daos.ChoixDao;
import hei.projets7.mobiliti.daos.impl.ChoixDaoImpl;
import hei.projets7.mobiliti.exception.ChoixAlreadyExistException;
import hei.projets7.mobiliti.exception.DestinationNotFoundException;
import hei.projets7.mobiliti.pojos.Choix;

import java.sql.SQLException;


public class ChoixServices {

    private ChoixDaoImpl choixDao = new ChoixDaoImpl();


    private static class ChoixLibraryHolder {
        private final static ChoixServices instance = new ChoixServices();
    }



    public static ChoixServices getInstance() {
        return ChoixLibraryHolder.instance;
    }


    public Choix getChoix(Integer id_eleve)  {
        Choix choix = choixDao.read(id_eleve);

            return choix;

    }



    public void modifyChoix(Integer id_eleve, Integer id_destination) throws DestinationNotFoundException {
        Choix choix = getChoix(id_eleve);
        if (id_destination==null || " ".equals(id_destination)){
            throw new DestinationNotFoundException(id_destination);
        }
        choixDao.modifyChoix(id_eleve,id_destination);
    }




    public Choix addChoix (Choix choix) throws ChoixAlreadyExistException, SQLException {


        if(choix == null) {
            throw new IllegalArgumentException("The destination can not be null.");
        }
        if(choix.getId_choix() == null || " ".equals(choix.getId_choix())){
            throw new IllegalArgumentException("The id can not be null.");
        }


        return choixDao.addChoix(choix);
    }






}
