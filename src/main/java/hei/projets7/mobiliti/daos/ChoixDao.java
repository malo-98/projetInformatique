package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.exception.ChoixAlreadyExistException;
import hei.projets7.mobiliti.exception.ChoixNotFoundException;
import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.pojos.Choix;

import java.sql.SQLException;
import java.util.List;

public interface ChoixDao {

    List<Choix> listAll();
    Choix read(Integer id_eleve) throws EleveNotFoundException, ChoixNotFoundException;
    void modifyChoix(Integer id_eleve) throws ChoixNotFoundException;
    Choix addChoix(Choix choix) throws ChoixAlreadyExistException;
    Integer countChoixByIdDestination (Integer id) ;

}
