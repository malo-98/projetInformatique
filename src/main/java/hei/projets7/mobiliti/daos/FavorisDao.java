package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.exception.ChoixAlreadyExistException;
import hei.projets7.mobiliti.pojos.Choix;
import hei.projets7.mobiliti.pojos.Favoris;

import java.sql.SQLException;
import java.util.List;

public interface FavorisDao {
    void modifyFavoris(Integer id);
    Favoris addFavoris(Favoris favoris) throws SQLException, ChoixAlreadyExistException;
    List<Favoris> listFavorisByIdEleve(Integer id);
}
