package hei.projets7.mobiliti.services;


import hei.projets7.mobiliti.daos.impl.FavorisDaoImpl;
import hei.projets7.mobiliti.entity.Destination;
import hei.projets7.mobiliti.entity.Favoris;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FavorisServiceTest {

    @Mock
    FavorisDaoImpl favorisDao;

    @InjectMocks
    FavorisServices favorisServices;

    @Test
    public void shouldListFavorisById(){
        //GIVEN
        List<Favoris> listFavoris=new ArrayList<>();
        List<Destination> listDestination=new ArrayList<>();
        int id_eleve=2;
        Favoris f1= new Favoris(1, id_eleve, 3);
        Favoris f2=new Favoris(2, id_eleve, 26);
        Destination d1=new Destination(3, "name1", "city1", "country1", "des1", "dom1", 4);
        Destination d2=new Destination(26, "name2", "city2", "country2", "des2", "dom2", 8);
        listFavoris.add(f1);
        listFavoris.add(f2);
        listDestination.add(d1);
        listDestination.add(d2);
        Mockito.when(favorisDao.listFavorisByIdEleve(id_eleve)).thenReturn(listDestination);

        //WHEN
        List<Destination> result=favorisServices.favorisListByID(id_eleve);

        //THEN
        Assertions.assertThat(result).containsExactlyInAnyOrderElementsOf(listDestination);
        Assertions.assertThat(result).hasSize(2);
    }

    @Test
    public void shouldAddFavoris(){
        List<Favoris> favorisList=new ArrayList<>();
        Favoris f1=new Favoris(1,2,3);
        Favoris f2=new Favoris(2,2,5);
        favorisList.add(f1);
        Mockito.when(favorisDao.addFavoris(f2)).thenReturn(f2);

        //WHEN
        Favoris result=favorisServices.addFavoris(f2);

        //THEN
        Assertions.assertThat(result).isEqualTo(f2);
    }


    @Test
    public void shouldModifyFavoris(){
        //GIVEN
        List<Favoris> listFavoris=new ArrayList<>();
        List<Destination> destinationList=new ArrayList<>();
        Favoris f1=new Favoris(1,2,3);
        Favoris f2=new Favoris(2, 2, 4);
        Destination d1=new Destination(2, "name1", "city1", "country1", "des1", "dom1", 4);
        listFavoris.add(f1);
        listFavoris.add(f2);
        destinationList.add(d1);
        Mockito.when(favorisDao.listFavorisByIdEleve(2)).thenReturn(destinationList);

        //WHEN
        List<Destination> destinationList1=favorisServices.favorisListByID(2);
        favorisServices.modifyFavoris(2,4);

        //THEN
        Assertions.assertThat(destinationList1).isEmpty();

    }
}
