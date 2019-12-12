package hei.projets7.mobiliti.services;

import hei.projets7.mobiliti.daos.impl.ChoixDaoImpl;
import hei.projets7.mobiliti.entity.Eleve;
import hei.projets7.mobiliti.exception.*;
import hei.projets7.mobiliti.entity.Choix;

import java.sql.SQLException;
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

    public Choix getChoix(Integer id_eleve)  throws ChoixNotFoundException {

        Choix choix = choixDao.read(id_eleve);
        if(choix==null || " ".equals(choix)) {
            throw new ChoixNotFoundException(id_eleve);
        }
        return choix;

    }

    public void modifyChoix(Integer id_eleve) throws ChoixNotFoundException {

        Choix choix = choixDao.read(id_eleve);
        if(choix==null || " ".equals(choix)) {
            throw new ChoixNotFoundException(id_eleve);
        }else{
            choixDao.modifyChoix(id_eleve);
        }
    }

    public Choix addChoix (Choix choix) throws ChoixAlreadyExistException {

        List<Choix> choixes = choixDao.listAll();
        List<Integer> ids=new ArrayList<Integer>();
        for (int i=0;i<choixes.size();i++){
            ids.add(choixes.get(i).getId_choix());
        }

        if(ids.contains(choix.getId_choix())) {
            throw new ChoixAlreadyExistException(choix);
        }


        if(choix == null) {
            throw new IllegalArgumentException("The id can not be null.");
        }

        return choixDao.addChoix(choix);
    }

    public Integer countChoixByIdDestination(Integer id) /* DestinationNotFoundException */ {
        return  choixDao.countChoixByIdDestination(id);
    }

}
