package hei.projets7.mobiliti.services;

import hei.projets7.mobiliti.daos.impl.DestinationDaoImpl;
import hei.projets7.mobiliti.pojos.Destination;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class DestinationServiceTest {

    @Mock
    DestinationDaoImpl destinationDao;

    @InjectMocks
    DestinationServices destinationServices;

    @Test
    public void shouldListDestination(){
        //GIVEN
        List<Destination> destinations = new ArrayList<>();
        Destination d1=new Destination(1, "name1", "city1", "country1", "des1", "dom1", 4);
        Destination d2=new Destination(2, "name2", "city2", "country2", "des2", "dom2", 8);
        destinations.add(d1);
        destinations.add(d2);
        Mockito.when(destinationDao.listDestinations()).thenReturn(destinations);

        //WHEN
        List<Destination> result=destinationServices.destinationList();

        //THEN
        Assertions.assertThat(result).containsExactlyInAnyOrderElementsOf(destinations);
        Assertions.assertThat(result).hasSize(2);
    }

    @Test
    public void shouldAddDestination(){
        //GIVEN
        List<Destination> destinations= new ArrayList<>();
        Destination d1=new Destination(1, "name1", "city1", "country1", "des1", "dom1", 4);
        destinations.add(d1);
        Destination d2=new Destination(2, "name2", "city2", "country2", "des2", "dom2", 8);
        Mockito.when(destinationDao.addDestination(d2)).thenReturn(d2);

        //WHEN
        Destination result=destinationServices.addDestination(d2);

        //THEN
        Assertions.assertThat(result).isEqualTo(d2);
    }

    @Test
    public void shouldDeleteDestination(){
        //Given
        Destination destination=new Destination(1, "name1", "city1", "country1", "des1", "dom1", 4);

        //WHEN
        destinationServices.deleteDestination(destination.getId());

        //THEN
        Mockito.verify(destinationDao).deleteDestinationByID(destination.getId());
    }

}
