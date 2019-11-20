package hei.projets7.mobiliti.services;

import hei.projets7.mobiliti.daos.ConnexionEleveDao;
import hei.projets7.mobiliti.daos.impl.ConnexionEleveDaoImpl;
import hei.projets7.mobiliti.daos.impl.InscriptionEleveDaoImpl;
import hei.projets7.mobiliti.exception.EleveAlreadyExistException;
import hei.projets7.mobiliti.pojos.Eleve;
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
public class EleveServiceTest  {

    @Mock
    InscriptionEleveDaoImpl inscriptionEleveDao;
    ConnexionEleveDaoImpl connexionEleveDao;

    @InjectMocks
    EleveServices eleveServices;


    @Test
    public void shouldListEleve(){
        //GIVEN
        List<Eleve> eleves = new ArrayList<Eleve>();
        Eleve e1= new Eleve(1,"testNom1","testPrenom1","testDomaine1","testEmail1","testMdp1");
        Eleve e2=new Eleve(2,"testNom2","testPrenom2","testDomaine2","testEmail2","testMdp2");
        eleves.add(e1);
        eleves.add(e2);
        Mockito.when(inscriptionEleveDao.listEleve()).thenReturn(eleves);

        //WHEN
        List<Eleve> results=eleveServices.listEleve();

        //THEN
        Assertions.assertThat(results).containsExactlyInAnyOrderElementsOf(eleves);

    }

    @Test
    public void shouldAddEleve() throws EleveAlreadyExistException {
        //GIVEN
        List<Eleve> eleves = new ArrayList<Eleve>();
        Eleve e1= new Eleve(1,"testNom1","testPrenom1","testDomaine1","testEmail1","testMdp1");
        eleves.add(e1);
        Eleve e2= new Eleve(2,"testNom2","testPrenom2","testDomaine2","testEmail2","testMdp2");
        Mockito.when(inscriptionEleveDao.addEleve(e2)).thenReturn(e2);

        //WHEN
        Eleve result = eleveServices.addEleve(e2);

        //THEN
        Assertions.assertThat(result).isEqualTo(e2);

    }

    @Test(expected = EleveAlreadyExistException.class)
    public void shouldNotAddEleveAndThrowEleveAlreadyExistException() throws EleveAlreadyExistException {
        //GIVEN
        List<Eleve> eleves = new ArrayList<Eleve>();
        Eleve e1= new Eleve(1,"testNom1","testPrenom1","testEmail1","testMdp1","testdomaine1");
        eleves.add(e1);
        Eleve e2= new Eleve(1,"testNom1","testPrenom1","testEmail1","testMdp1","testdomaine1");
        Mockito.when(inscriptionEleveDao.listEleve()).thenReturn(eleves);

        //WHEN
        eleveServices.addEleve(e2);

        //THEN
        fail("Eleve already exist exception");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotAddEleveAndThrowIllegalArgumentException() throws EleveAlreadyExistException {
        //GIVEN
        Eleve e2= new Eleve(1," "," "," "," "," ");
        Mockito.when(inscriptionEleveDao.addEleve(e2)).thenThrow(new IllegalArgumentException("The eleve can not be null."));

        //WHEN
        eleveServices.addEleve(e2);

        //THEN
        fail("illegal argument exception");


    }

    @Test
    public void shouldModifyPassword(){
        //GIVEN
        Eleve e1= new Eleve(1,"testNom1","testPrenom1","testEmail1","testMdp1","testdomaine1");
        String newPassword= "newpassword";

        //WHEN

        //THEN
    }


    @Test
    public void shouldDeleteUser(){
        //GIVEN
        List<Eleve> eleves = new ArrayList<Eleve>();
        Eleve e1= new Eleve(1,"testNom1","testPrenom1","testDomaine1","testEmail1","testMdp1");
        Eleve e2=new Eleve(2,"testNom2","testPrenom2","testDomaine2","testEmail2","testMdp2");
        eleves.add(e1);
        eleves.add(e2);
        Mockito.when(connexionEleveDao.read(e2.getEmail())).thenReturn(e2);

        //WHEN
        eleveServices.deleteUser(e2.getEmail());

        //THEN
        

    }
}
