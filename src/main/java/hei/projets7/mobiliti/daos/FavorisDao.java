package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.exception.ChoixAlreadyExistException;
import hei.projets7.mobiliti.entity.Destination;
import hei.projets7.mobiliti.entity.Favoris;

import java.sql.SQLException;
import java.util.List;

public interface FavorisDao {
    void modifyFavoris(Integer id, Integer user_id);
    Favoris addFavoris(Favoris favoris) throws SQLException, ChoixAlreadyExistException;
    List<Destination> listFavorisByIdEleve(Integer id);
}
