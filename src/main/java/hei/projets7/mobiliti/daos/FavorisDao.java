package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.pojos.Favoris;

import java.util.List;

public interface FavorisDao {
    List<Favoris> listFavorisByIdEleve(Integer id);
    Favoris addFavoris(Favoris favoris);
    void deleteFavoris(Integer id);
}
