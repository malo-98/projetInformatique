package hei.projets7.mobiliti.services;

import hei.projets7.mobiliti.daos.impl.DestinationDaoImpl;
import hei.projets7.mobiliti.exception.DestinationAlreadyExistException;
import hei.projets7.mobiliti.exception.DestinationNotFoundException;
import hei.projets7.mobiliti.exception.DonneIllegalFormatException;
import hei.projets7.mobiliti.entity.Destination;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;

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
    public void shouldAddDestination() throws DestinationAlreadyExistException {
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

    @Test(expected = DestinationAlreadyExistException.class)
    public void shouldNotAddDestinationAndThrowDestinationAlreadyExistException() throws DestinationAlreadyExistException {
        //GIVEN
        List<Destination> destinations = new ArrayList<Destination>();
        Destination d1= new Destination(1,"testNom1","testVille1","testPays1", "test description 1", "testDomaine1", 4);
        destinations.add(d1);
        Destination d2= new Destination(1,"testNom1","testVille1","testPays1", "test description 1", "testDomaine1", 4);
        Mockito.when(destinationDao.listDestinations()).thenReturn(destinations);

        //WHEN
        destinationServices.addDestination(d2);

        //THEN
        fail("Destination already exist exception");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAddDestinationAndThrowIllegalArgumentException() throws DestinationAlreadyExistException {
        //GIVEN
        Destination d2= new Destination(1," "," "," "," "," ",null);
        Mockito.lenient().when(destinationDao.addDestination(d2)).thenThrow(new IllegalArgumentException("The Destination can not be null."));

        //WHEN
        destinationServices.addDestination(d2);

        //THEN
        fail("illegal argument exception");

        }




    @Test
    public void shouldDeleteDestination() throws DonneIllegalFormatException {
        //Given
        Destination destination=new Destination(1, "name1", "city1", "country1", "des1", "dom1", 4);

        //WHEN
        destinationServices.deleteDestination(destination.getId());

        //THEN
        Mockito.verify(destinationDao,Mockito.times(1)).deleteDestinationByID(destination.getId());
    }

    @Test
    public void shouldUpdateNom() throws DestinationNotFoundException, DonneIllegalFormatException {
        //GIVEN
        Destination destination=new Destination(1, "test1", "city1", "country1", "des1", "dom1", 4);
        String newName = "test1";
        Mockito.when(destinationDao.read(destination.getId())).thenReturn(destination);
        //WHEN
        destinationServices.modifyNom(1,newName);
        //THEN
        Mockito.verify(destinationDao,Mockito.times(1)).modifyNom(destination.getId(),newName);
    }

    @Test
    public void shouldUpdateDescription() throws DestinationNotFoundException, DonneIllegalFormatException {
        //GIVEN
        Destination destination=new Destination(1, "test1", "city1", "country1", "des1", "dom1", 4);
        String newDesc = "des1";
        Mockito.when(destinationDao.read(destination.getId())).thenReturn(destination);
        //WHEN
        destinationServices.modifyDescription(1,newDesc);
        //THEN
        Mockito.verify(destinationDao,Mockito.times(1)).modifyDescription(destination.getId(),newDesc);
    }

    @Test
    public void shouldUpdateVille() throws DestinationNotFoundException, DonneIllegalFormatException {
        //GIVEN
        Destination destination=new Destination(1, "test1", "city1", "country1", "des1", "dom1", 4);
        String newCity = "city1";
        Mockito.when(destinationDao.read(destination.getId())).thenReturn(destination);
        //WHEN
        destinationServices.modifyVille(1,newCity);
        //THEN
        Mockito.verify(destinationDao,Mockito.times(1)).modifyVille(destination.getId(),newCity);
    }

    @Test
    public void shouldUpdatePays() throws DestinationNotFoundException, DonneIllegalFormatException {
        //GIVEN
        Destination destination=new Destination(1, "test1", "city1", "country1", "des1", "dom1", 4);
        String newCountry = "country1";
        Mockito.when(destinationDao.read(destination.getId())).thenReturn(destination);
        //WHEN
        destinationServices.modifyPays(1,newCountry);
        //THEN
        Mockito.verify(destinationDao,Mockito.times(1)).modifyPays(destination.getId(),newCountry);
    }

    @Test
    public void shouldUpdateDomaine() throws DestinationNotFoundException, DonneIllegalFormatException {
        //GIVEN
        Destination destination=new Destination(1, "test1", "city1", "country1", "des1", "dom1", 4);
        String newDomaine = "dom1";
        Mockito.when(destinationDao.read(destination.getId())).thenReturn(destination);
        //WHEN
        destinationServices.modifyDomaine(1,newDomaine);
        //THEN
        Mockito.verify(destinationDao,Mockito.times(1)).modifyDomaine(destination.getId(),newDomaine);
    }

    @Test
    public void shouldUpdateNbrePlace() throws DestinationNotFoundException, DonneIllegalFormatException {
        //GIVEN
        Destination destination=new Destination(1, "test1", "city1", "country1", "des1", "dom1", 4);
        Integer newNbplace = 4;
        Mockito.when(destinationDao.read(destination.getId())).thenReturn(destination);
        //WHEN
        destinationServices.modifyNbrePlace(1,4);
        //THEN
        Mockito.verify(destinationDao,Mockito.times(1)).modifyNbrePlace(destination.getId(),newNbplace);
    }

    @Test
    public void shouldNotUpdateNameDestinationAndThrowIllegalArgumentException() throws DonneIllegalFormatException {
        //GIVEN
        Destination destination= new Destination(1, "test1", "city1", "country1", "des1", "dom1", 4);
        Mockito.when(destinationDao.read(destination.getId())).thenReturn(destination);
        Exception result=null;
        //WHEN
        try{
            destinationServices.modifyNom(destination.getId(),null);
        }catch(Exception e){
            result=e;
        }
        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(DonneIllegalFormatException.class);
        Mockito.verify(destinationDao,Mockito.never()).modifyNom(Mockito.anyInt(),Mockito.anyString());
    }

    @Test
    public void shouldNotUpdateDescriptionDestinationAndThrowIllegalArgumentException() throws DonneIllegalFormatException {
        //GIVEN
        Destination destination= new Destination(1, "test1", "city1", "country1", "des1", "dom1", 4);
        Mockito.when(destinationDao.read(destination.getId())).thenReturn(destination);
        Exception result=null;
        //WHEN
        try{
            destinationServices.modifyDescription(destination.getId(),null);
        }catch(Exception e){
            result=e;
        }
        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(DonneIllegalFormatException.class);
        Mockito.verify(destinationDao,Mockito.never()).modifyNom(Mockito.anyInt(),Mockito.anyString());
    }

    @Test
    public void shouldNotUpdatePaysDestinationAndThrowIllegalArgumentException() throws DonneIllegalFormatException {
        //GIVEN
        Destination destination= new Destination(1, "test1", "city1", "country1", "des1", "dom1", 4);
        Mockito.when(destinationDao.read(destination.getId())).thenReturn(destination);
        Exception result=null;
        //WHEN
        try{
            destinationServices.modifyPays(destination.getId(),null);
        }catch(Exception e){
            result=e;
        }
        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(DonneIllegalFormatException.class);
        Mockito.verify(destinationDao,Mockito.never()).modifyNom(Mockito.anyInt(),Mockito.anyString());
    }

    @Test
    public void shouldNotUpdateDomaineDestinationAndThrowIllegalArgumentException() throws DonneIllegalFormatException {
        //GIVEN
        Destination destination= new Destination(1, "test1", "city1", "country1", "des1", "dom1", 4);
        Mockito.when(destinationDao.read(destination.getId())).thenReturn(destination);
        Exception result=null;
        //WHEN
        try{
            destinationServices.modifyDomaine(destination.getId(),null);
        }catch(Exception e){
            result=e;
        }
        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(DonneIllegalFormatException.class);
        Mockito.verify(destinationDao,Mockito.never()).modifyNom(Mockito.anyInt(),Mockito.anyString());
    }

    @Test
    public void shouldNotUpdateNbrePlaceDestinationAndThrowIllegalArgumentException() throws DonneIllegalFormatException {
        //GIVEN
        Destination destination= new Destination(1, "test1", "city1", "country1", "des1", "dom1", 4);
        Mockito.when(destinationDao.read(destination.getId())).thenReturn(destination);
        Exception result=null;
        //WHEN
        try{
            destinationServices.modifyNbrePlace(destination.getId(),null);
        }catch(Exception e){
            result=e;
        }
        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(DonneIllegalFormatException.class);
        Mockito.verify(destinationDao,Mockito.never()).modifyNom(Mockito.anyInt(),Mockito.anyString());
    }

    @Test
    public void shouldNotUpdateVilleDestinationAndThrowIllegalArgumentException() throws DonneIllegalFormatException {
        //GIVEN
        Destination destination= new Destination(1, "test1", "city1", "country1", "des1", "dom1", 4);
        Mockito.when(destinationDao.read(destination.getId())).thenReturn(destination);
        Exception result=null;
        //WHEN
        try{
            destinationServices.modifyVille(destination.getId(),null);
        }catch(Exception e){
            result=e;
        }
        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(DonneIllegalFormatException.class);
        Mockito.verify(destinationDao,Mockito.never()).modifyNom(Mockito.anyInt(),Mockito.anyString());
    }

    @Test
    public void shouldNotUpdateDescriptionDestinationAndThrowDestinationNotFound() throws DestinationNotFoundException {
        //GIVEN
        Integer id=1;
        String newDesc= "Desc1";
        Mockito.when(destinationDao.read(id)).thenReturn(null);
        Exception result=null;

        //WHEN
        try{
            destinationServices.modifyDescription(id,newDesc);
        }catch(Exception e){
            result=e;
        }
        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(DestinationNotFoundException.class);
        Mockito.verify(destinationDao,Mockito.never()).modifyDescription(Mockito.anyInt(),Mockito.anyString());
    }

    @Test
    public void shouldNotUpdateVilleDestinationAndThrowDestinationNotFound() throws DestinationNotFoundException {
        //GIVEN
        Integer id=1;
        String newVille= "Ville1";
        Mockito.when(destinationDao.read(id)).thenReturn(null);
        Exception result=null;

        //WHEN
        try{
            destinationServices.modifyVille(id,newVille);
        }catch(Exception e){
            result=e;
        }
        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(DestinationNotFoundException.class);
        Mockito.verify(destinationDao,Mockito.never()).modifyDescription(Mockito.anyInt(),Mockito.anyString());
    }

    @Test
    public void shouldNotUpdatePaysDestinationAndThrowDestinationNotFound() throws DestinationNotFoundException {
        //GIVEN
        Integer id=1;
        String newPays= "Pays1";
        Mockito.when(destinationDao.read(id)).thenReturn(null);
        Exception result=null;

        //WHEN
        try{
            destinationServices.modifyPays(id,newPays);
        }catch(Exception e){
            result=e;
        }
        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(DestinationNotFoundException.class);
        Mockito.verify(destinationDao,Mockito.never()).modifyDescription(Mockito.anyInt(),Mockito.anyString());
    }

    @Test
    public void shouldNotUpdateDomaineDestinationAndThrowDestinationNotFound() throws DestinationNotFoundException {
        //GIVEN
        Integer id=1;
        String newDom= "Dom1";
        Mockito.when(destinationDao.read(id)).thenReturn(null);
        Exception result=null;

        //WHEN
        try{
            destinationServices.modifyDescription(id,newDom);
        }catch(Exception e){
            result=e;
        }
        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(DestinationNotFoundException.class);
        Mockito.verify(destinationDao,Mockito.never()).modifyDescription(Mockito.anyInt(),Mockito.anyString());
    }

    @Test
    public void shouldNotUpdateNbrePlaceDestinationAndThrowDestinationNotFound() throws DestinationNotFoundException {
        //GIVEN
        Integer id=1;
        Integer newNbrePlace=4;
        Mockito.when(destinationDao.read(id)).thenReturn(null);
        Exception result=null;

        //WHEN
        try{
            destinationServices.modifyNbrePlace(id,newNbrePlace);
        }catch(Exception e){
            result=e;
        }
        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(DestinationNotFoundException.class);
        Mockito.verify(destinationDao,Mockito.never()).modifyDescription(Mockito.anyInt(),Mockito.anyString());
    }

    @Test
    public void shouldNotUpdateNameDestinationAndThrowDestinationNotFound() throws DestinationNotFoundException {
        //GIVEN
        Integer id=1;
        String newName="name1";
        Mockito.when(destinationDao.read(id)).thenReturn(null);
        Exception result=null;

        //WHEN
        try{
            destinationServices.modifyNom(id,newName);
        }catch(Exception e){
            result=e;
        }
        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(DestinationNotFoundException.class);
        Mockito.verify(destinationDao,Mockito.never()).modifyDescription(Mockito.anyInt(),Mockito.anyString());
    }


}
