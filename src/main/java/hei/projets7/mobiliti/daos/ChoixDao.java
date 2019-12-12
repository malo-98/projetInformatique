package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.entity.Choix;

import java.util.List;

public interface ChoixDao {

    List<Choix> listAll();
    Choix read(Integer id_eleve);
    void modifyChoix(Integer id_eleve);
    Choix addChoix(Choix choix);
    Integer countChoixByIdDestination (Integer id) ;

}
