package hei.projets7.mobiliti.services;

import hei.projets7.mobiliti.daos.ChoixDao;
import hei.projets7.mobiliti.daos.impl.ChoixDaoImpl;
import hei.projets7.mobiliti.exception.ChoixAlreadyExistException;
import hei.projets7.mobiliti.exception.DonneIllegalFormatException;
import hei.projets7.mobiliti.pojos.Choix;
import hei.projets7.mobiliti.pojos.Destination;
import hei.projets7.mobiliti.pojos.Eleve;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ChoixServiceTest {

    @Mock
    ChoixDaoImpl choixDao;

    @InjectMocks
    ChoixServices choixServices;

    @Test
    public void SouldListAllChoix(){

    }

    @Test
    public void ShouldGetChoix(){
        //GIVEN
        Eleve eleve = new Eleve (1,"ELEVE","eleve","eleve@mail","test","ITI");
        Destination d1=new Destination(1, "name1", "city1", "country1", "des1", "dom1", 4);
        Choix choix = new Choix (1,1,1);

        choixServices.getChoix(1);
        Mockito.when(choixDao.read(1)).thenReturn(choix);

        //WHEN
        Choix result = choixServices.getChoix(1);

        //THEN
        Assertions.assertThat(result).isEqualTo(choix);

    }

    @Test
    public void ShouldAddChoix() throws ChoixAlreadyExistException, DonneIllegalFormatException, SQLException {
        //GIVEN
        Choix choix1 = new Choix(12,12,12);
        Mockito.when(choixDao.addChoix(choix1)).thenReturn(choix1);
        //WHEN
        Choix result = choixDao.addChoix(choix1);
        //THEN
        Assertions.assertThat(result).isEqualTo(choix1);
    }

    @Test
    public void ShouldModifyChoix() throws SQLException {
        //GIVEN
        Choix choix1 = new Choix(12,12,12);
        choixServices.addChoix(choix1);
        //WHEN
        choixServices.modifyChoix(12);
        //THEN
        Mockito.verify(choixDao).modifyChoix(choix1.getId_eleve());
    }

    /*@Test
    public void ShouldCountChoixByIdDestination() throws SQLException {
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
    }*/

}
