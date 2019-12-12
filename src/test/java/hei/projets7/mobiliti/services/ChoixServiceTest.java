package hei.projets7.mobiliti.services;

import hei.projets7.mobiliti.daos.impl.ChoixDaoImpl;
import hei.projets7.mobiliti.exception.*;
import hei.projets7.mobiliti.entity.Choix;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.fail;

@RunWith(MockitoJUnitRunner.class)
public class ChoixServiceTest {

    @Mock
    ChoixDaoImpl choixDao;

    @InjectMocks
    ChoixServices choixServices;

    @Test
    public void SouldListAllChoix(){
        //GIVEN
        List<Choix> choixes = new ArrayList<Choix>();
        Choix c1= new Choix(1,1,1 );
        Choix c2=new Choix(2,2,2);
        choixes.add(c1);
        choixes.add(c2);
        Mockito.when(choixDao.listAll()).thenReturn(choixes);

        //WHEN
        List<Choix> results=choixDao.listAll();

        //THEN
        Assertions.assertThat(results).containsExactlyInAnyOrderElementsOf(choixes);

    }

//---------------------- Test Get --------------------------------

    @Test
    public void ShouldGetChoix() throws ChoixNotFoundException {
        //GIVEN
        Choix choix = new Choix (1,1,1);

        choixServices.getChoix(1);
        Mockito.when(choixDao.read(1)).thenReturn(choix);

        //WHEN
        Choix result = choixServices.getChoix(1);

        //THEN
        Assertions.assertThat(result).isEqualTo(choix);

    }


    @Test(expected = ChoixNotFoundException.class)
    public void ShouldNotGetChoixAndThrowChoixNotFoundException() throws ChoixNotFoundException {
        //GIVEN
        Integer id_eleve=1;
        Mockito.when(choixDao.read(id_eleve)).thenReturn(null);
        Exception result = null;

        //WHEN
        try{
            choixServices.getChoix(id_eleve);
        }catch(Exception e){
            result = e;
        }
        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(ChoixNotFoundException.class);
        Mockito.verify(choixServices,Mockito.never()).getChoix(Mockito.anyInt());
    }


// ----------------------- TEST Modify------------------------------------------------------

    @Test
    public void ShouldModifyChoix() throws ChoixNotFoundException, ChoixAlreadyExistException {
        //GIVEN
        Choix choix1 = new Choix(12,12,12);
        choixServices.addChoix(choix1);
        //WHEN
        choixServices.modifyChoix(12);
        //THEN
        Mockito.verify(choixDao).modifyChoix(choix1.getId_eleve());
    }

    @Test
    public void ShouldNotModifyChoixAndThrowChoixNotFoundException(){

    }

// ----------------------- TEST Add------------------------------------------------------

    @Test
    public void ShouldAddChoix() throws ChoixAlreadyExistException {
        //GIVEN
        Choix choix1 = new Choix(12,12,12);
        Mockito.when(choixDao.addChoix(choix1)).thenReturn(choix1);
        //WHEN
        Choix result = choixDao.addChoix(choix1);
        //THEN
        Assertions.assertThat(result).isEqualTo(choix1);
    }

    @Test
    public void ShouldNotAddChoixAndThrowChoixAlreadyExistException() {
        //GIVEN
        List<Choix> choixes = new ArrayList<Choix>();
        Choix c1= new Choix(1,1,1);
        choixes.add(c1);
        Choix c2= new Choix(1,1,1);
        Mockito.when(choixDao.listAll()).thenReturn(choixes);

        //WHEN
        choixes.add(c2);

        //THEN
        fail("Choix already exist exception");
    }

// ----------------------- TEST Count------------------------------------------------------

    @Test
    public void ShouldCountChoixByIdDestination() throws DestinationNotFoundException {
        //GIVEN
        Choix choix1 = new Choix(12,12,12);
        Choix choix2 = new Choix(10,10,12);
        Mockito.when(choixDao.addChoix(choix2)).thenReturn(choix2);
        Mockito.when(choixDao.addChoix(choix1)).thenReturn(choix1);
        Integer NumOfEleveOnId12=2;
        //WHEN
        Integer result=choixDao.countChoixByIdDestination(12);
        //THEN
        Assertions.assertThat(result).isEqualTo(NumOfEleveOnId12);
    }

}
