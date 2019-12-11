package hei.projets7.mobiliti.services;

import hei.projets7.mobiliti.daos.impl.DestinationDaoImpl;
import hei.projets7.mobiliti.daos.impl.FavorisDaoImpl;
import hei.projets7.mobiliti.exception.DonneIllegalFormatException;
import hei.projets7.mobiliti.pojos.Destination;
import hei.projets7.mobiliti.pojos.Favoris;

import java.util.List;

public class FavorisServices {
    private static  class  FavorisListHolder{
        private final static FavorisServices instance = new FavorisServices();
    }

    public static FavorisServices getInstance(){return FavorisListHolder.instance;}

    private FavorisDaoImpl favorisDao= new FavorisDaoImpl();

    public FavorisServices(){
    }

    public List<Destination> favorisListByID(Integer id){return favorisDao.listFavorisByIdEleve(id); }

    public  Favoris addFavoris(Favoris favoris){return  favorisDao.addFavoris(favoris);}

    public void modifyFavoris(Integer id, Integer user_id) {
        favorisDao.modifyFavoris(id, user_id);
    }

}
