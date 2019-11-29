package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.pojos.Choix;

import java.sql.SQLException;

public interface ChoixDao {

    Choix read(Integer id_eleve);
    void modifyChoix(Integer id_eleve, Integer id_destination);
    Choix addChoix(Choix choix) throws SQLException;

}
