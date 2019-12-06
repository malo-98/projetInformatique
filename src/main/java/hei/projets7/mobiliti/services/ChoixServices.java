package hei.projets7.mobiliti.services;

import hei.projets7.mobiliti.daos.impl.ChoixDaoImpl;
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

    public void modifyChoix(Integer id_eleve)  {
        choixDao.modifyChoix(id_eleve);
    }

    public Choix addChoix (Choix choix) throws SQLException {
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

    public Integer countChoixByIdDestination(Integer id){
        return  choixDao.countChoixByIdDestination(id);
    }






}
