package hei.projets7.mobiliti.services;

import hei.projets7.mobiliti.daos.impl.ChoixDaoImpl;
import hei.projets7.mobiliti.daos.impl.DataSourceProvider;
import hei.projets7.mobiliti.exception.ChoixAlreadyExistException;
import hei.projets7.mobiliti.exception.ChoixNotFoundException;
import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.pojos.Choix;
import hei.projets7.mobiliti.pojos.Destination;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class ChoixServices {

    private ChoixDaoImpl choixDao = new ChoixDaoImpl();


    private static class ChoixLibraryHolder {
        private final static ChoixServices instance = new ChoixServices();
    }



    public static ChoixServices getInstance() {
        return ChoixLibraryHolder.instance;
    }


    public List<Choix> listChoix(){
    return choixDao.listAll();
    }

    public Choix getChoix(Integer id_eleve)  throws EleveNotFoundException, ChoixNotFoundException {
        Choix choix = choixDao.read(id_eleve);
            return choix;

    }

    public void modifyChoix(Integer id_eleve) throws ChoixNotFoundException {
        choixDao.modifyChoix(id_eleve);
    }

    public Choix addChoix (Choix choix) throws SQLException, ChoixAlreadyExistException {
        /*
       String query="SELECT id_eleve, FROM choix";
       Integer id_base =Integer.parseInt(query);

        if (choix.getId_eleve() == id_base ){
            throw new ChoixAlreadyExistException(choix);
        }

         */
        /*
        if(choix == null) {
            throw new IllegalArgumentException("The destination can not be null.");
        }
        if(choix.getId_choix() == null || " ".equals(choix.getId_choix())){
            throw new IllegalArgumentException("The id can not be null.");
        }

         */
        return choixDao.addChoix(choix);
    }

    public Integer countChoixByIdDestination(Integer id) /*throws ChoixNotFoundException*/ {
        return  choixDao.countChoixByIdDestination(id);
    }

}
