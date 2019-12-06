package hei.projets7.mobiliti.services;

import hei.projets7.mobiliti.daos.ConnexionEleveDao;
import hei.projets7.mobiliti.daos.impl.ConnexionEleveDaoImpl;
import hei.projets7.mobiliti.daos.impl.InscriptionEleveDaoImpl;
import hei.projets7.mobiliti.exception.DonneIllegalFormatException;
import hei.projets7.mobiliti.exception.EleveAlreadyExistException;
import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.exception.PasswordIllegalFormatException;
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

    @Mock
    ConnexionEleveDaoImpl connexionEleveDao;

    @InjectMocks
    EleveServices eleveServices;


    // Test sur liste
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

    ///////////////////// Test sur Add ////////////////////////
    @Test
    public void shouldAddEleve() throws EleveAlreadyExistException, DonneIllegalFormatException {
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
    public void shouldNotAddEleveAndThrowEleveAlreadyExistException() throws EleveAlreadyExistException, DonneIllegalFormatException {
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

    @Test(expected = DonneIllegalFormatException.class)
    public void shouldNotAddEleveAndThrowDonneIllegalFormatException() throws EleveAlreadyExistException, DonneIllegalFormatException {
        //GIVEN
        Eleve e2= new Eleve(1," "," "," "," "," ");
        Mockito.lenient().when(inscriptionEleveDao.addEleve(e2)).thenReturn(e2);

        //WHEN
        eleveServices.addEleve(e2);

        //THEN
        fail("illegal format exception");


    }

    //////////////////////////////////////////////////////////////
    //////// Test sur modifyPassword ////////////////////////////
    @Test
    public void shouldModifyPassword() throws EleveNotFoundException, PasswordIllegalFormatException {
        //GIVEN
        Eleve e1= new Eleve(1,"testNom1","testPrenom1","testEmail1","newpassword","testdomaine1");
        String newPassword= "newpassword";
        Mockito.when(connexionEleveDao.read(e1.getEmail())).thenReturn(e1);

        //WHEN
        eleveServices.modifyPassword(e1.getEmail(),newPassword);

        //THEN
        Mockito.verify(connexionEleveDao,Mockito.times(1)).modifyPassword(e1.getId_eleve(),e1.getPassword());
    }

    @Test
    public void shouldNotModifyPasswordAndThrowEleveNotFoundException() throws EleveNotFoundException {
        //GIVEN
        String email="email";
        String newPassword= "newpassword";
        Mockito.when(connexionEleveDao.read(email)).thenReturn(null);
        Exception result=null;

        //WHEN
        try{
            eleveServices.modifyPassword(email,newPassword);
        }catch(Exception e){
            result=e;
        }
        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(EleveNotFoundException.class);
        Mockito.verify(connexionEleveDao,Mockito.never()).modifyPassword(Mockito.anyInt(),Mockito.anyString());

    }

    @Test
    public void shouldNotModifyPasswordAndThrowIllegalArgumentException() throws PasswordIllegalFormatException{
        //GIVEN
        Eleve e1= new Eleve(1,"testNom1","testPrenom1","testEmail1",null,"testdomaine1");
        Mockito.when(connexionEleveDao.read(e1.getEmail())).thenReturn(e1);
        Exception result=null;

        //WHEN
        try{
            eleveServices.modifyPassword(e1.getEmail(), null);
        }catch(Exception e){
            result=e;
        }

        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(PasswordIllegalFormatException.class);
        Mockito.verify(connexionEleveDao,Mockito.never()).modifyPassword(Mockito.anyInt(),Mockito.anyString());
    }

    //////////////////////////////////////////////////////////////////
    /////////// Test sur delete /////////////////////////////////////

    @Test
    public void shouldDeleteUser() throws EleveNotFoundException {
        //GIVEN
        Eleve e1= new Eleve(1,"testNom1","testPrenom1","testEmail1","testMdp1","testdomaine1");
        Mockito.when(connexionEleveDao.read(e1.getEmail())).thenReturn(e1);

        //WHEN
        eleveServices.deleteUser(e1.getEmail());

        //THEN
        Mockito.verify(connexionEleveDao,Mockito.times(1)).deleteEleve(e1.getId_eleve());

    }

    @Test
    public void shouldNotDeleteUserAndThrowEleveNotFoundException() throws EleveNotFoundException {
        //GIVEN
        String email="testEmail1";
        Mockito.when(connexionEleveDao.read(email)).thenReturn(null);
        Exception result=null;
        //WHEN
        try{
            eleveServices.deleteUser(email);
        }catch(Exception e){
            result=e;
        }
        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(EleveNotFoundException.class);
        Mockito.verify(connexionEleveDao,Mockito.never()).deleteEleve(Mockito.anyInt());
    }

    ////////////////////////////////////////////////////////////////////
    ////////// Test sur CHeckPassword /////////////////////////////////

    @Test
    public void shouldCheckPasswordAndReturnTrue() throws EleveNotFoundException {
        //GIVEN
        Eleve e1= new Eleve(1,"testNom1","testPrenom1","testEmail1","testMdp1","testDomaine1");
        String mdpTest="testMdp1";
        Mockito.when(connexionEleveDao.read(e1.getEmail())).thenReturn(e1);

        //WHEN
        boolean result=eleveServices.checkPassword(e1.getEmail(),mdpTest);

        //THEN
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void shouldCheckPasswordAndReturnFalse() throws EleveNotFoundException {
        //GIVEN
        Eleve e1= new Eleve(1,"testNom1","testPrenom1","testDomaine1","testEmail1","testMdp1");
        String mdpTest="testMdp";
        Mockito.when(connexionEleveDao.read(e1.getEmail())).thenReturn(e1);

        //WHEN
        boolean result=eleveServices.checkPassword(e1.getEmail(),mdpTest);

        //THEN
        Assertions.assertThat(result).isFalse();
    }

    @Test(expected = EleveNotFoundException.class)
    public void shouldNotCheckPasswordAndThrowEleveNotFoundException() throws EleveNotFoundException {
        //GIVEN
        String email="email";
        Mockito.when(connexionEleveDao.read(email)).thenReturn(null);

        //WHEN
        boolean result=eleveServices.checkPassword(email,Mockito.anyString());

        //THEN
        fail("eleve not found");

    }

    //////////////////////////////////////////////////////////////////////
    ///////////////// Test su GetPasswordByEmail /////////////////////////

    @Test
    public void shouldGetPasswordByEmail() throws EleveNotFoundException {
        //GIVEN
        Eleve e1= new Eleve(1,"testNom1","testPrenom1","testEmail1","testMdp1","testDomaine1");
        String mdp="testMdp1";
        Mockito.when(connexionEleveDao.read(e1.getEmail())).thenReturn(e1);

        //WHEN
        String result=eleveServices.getPasswordByEmail(e1.getEmail());

        //THEN
        Assertions.assertThat(result).isEqualTo(mdp);
    }

    @Test(expected = EleveNotFoundException.class)
    public void shouldNotGetPasswordByEmailAndThrowEleveNotFoundException() throws EleveNotFoundException {
        //GIVEN
        String email="email";
        String mdp="testMdp1";
        Mockito.when(connexionEleveDao.read(email)).thenReturn(null);

        //WHEN
        String result =eleveServices.getPasswordByEmail(email);

        //THEN
        fail("user not found");
    }

    //////////////////////////////////////////////////////////////////////
    ////////// Test sur Modify Nom /////////////////////////////////////
    @Test
    public void shouldModifyNom() throws EleveNotFoundException, PasswordIllegalFormatException, DonneIllegalFormatException {
        //GIVEN
        Eleve e1= new Eleve(1,"newnom","testPrenom1","testEmail1","newpassword","testdomaine1");
        String newNom= "newnom";
        Mockito.when(connexionEleveDao.read(e1.getEmail())).thenReturn(e1);

        //WHEN
        eleveServices.modifyNom(e1.getEmail(),newNom);

        //THEN
        Mockito.verify(connexionEleveDao,Mockito.times(1)).modifyNom(e1.getId_eleve(),e1.getNom());
    }

    @Test
    public void shouldNotModifyNomAndThrowEleveNotFoundException() throws EleveNotFoundException {
        //GIVEN
        String email="email";
        String newNom= "newnom";
        Mockito.when(connexionEleveDao.read(email)).thenReturn(null);
        Exception result=null;

        //WHEN
        try{
            eleveServices.modifyNom(email,newNom);
        }catch(Exception e){
            result=e;
        }
        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(EleveNotFoundException.class);
        Mockito.verify(connexionEleveDao,Mockito.never()).modifyNom(Mockito.anyInt(),Mockito.anyString());

    }

    @Test
    public void shouldNotModifyNomAndThrowDonneIllegalFormatException() throws DonneIllegalFormatException{
        //GIVEN
        Eleve e1= new Eleve(1,"testNom1","testPrenom1","testEmail1","mdp","testdomaine1");
        Mockito.when(connexionEleveDao.read(e1.getEmail())).thenReturn(e1);
        Exception result=null;

        //WHEN
        try{
            eleveServices.modifyNom(e1.getEmail(), null);
        }catch(Exception e){
            result=e;
        }

        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(DonneIllegalFormatException.class);
        Mockito.verify(connexionEleveDao,Mockito.never()).modifyNom(Mockito.anyInt(),Mockito.anyString());
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////// Modify Prenom //////////////////////////////////////////////////////////

    @Test
    public void shouldModifyPrenom() throws EleveNotFoundException, PasswordIllegalFormatException, DonneIllegalFormatException {
        //GIVEN
        Eleve e1= new Eleve(1,"newnom","newprenom","testEmail1","newpassword","testdomaine1");
        String newPrenom= "newprenom";
        Mockito.when(connexionEleveDao.read(e1.getEmail())).thenReturn(e1);

        //WHEN
        eleveServices.modifyPrenom(e1.getEmail(),newPrenom);

        //THEN
        Mockito.verify(connexionEleveDao,Mockito.times(1)).modifyPrenom(e1.getId_eleve(),e1.getPrenom());
    }

    @Test
    public void shouldNotModifyPrenomAndThrowEleveNotFoundException() throws EleveNotFoundException {
        //GIVEN
        String email="email";
        String newPrenom= "newprenom";
        Mockito.when(connexionEleveDao.read(email)).thenReturn(null);
        Exception result=null;

        //WHEN
        try{
            eleveServices.modifyNom(email,newPrenom);
        }catch(Exception e){
            result=e;
        }
        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(EleveNotFoundException.class);
        Mockito.verify(connexionEleveDao,Mockito.never()).modifyPrenom(Mockito.anyInt(),Mockito.anyString());

    }

    @Test
    public void shouldNotModifyPrenomAndThrowDonneIllegalFormatException() throws DonneIllegalFormatException{
        //GIVEN
        Eleve e1= new Eleve(1,"testNom1","testPrenom1","testEmail1","mdp","testdomaine1");
        Mockito.when(connexionEleveDao.read(e1.getEmail())).thenReturn(e1);
        Exception result=null;

        //WHEN
        try{
            eleveServices.modifyPrenom(e1.getEmail(), null);
        }catch(Exception e){
            result=e;
        }

        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(DonneIllegalFormatException.class);
        Mockito.verify(connexionEleveDao,Mockito.never()).modifyPrenom(Mockito.anyInt(),Mockito.anyString());
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////// Modify Domaine /////////////////////////////////////////////////////////////////////
    @Test
    public void shouldModifyDomaine() throws EleveNotFoundException, PasswordIllegalFormatException, DonneIllegalFormatException {
        //GIVEN
        Eleve e1= new Eleve(1,"newnom","testPrenom1","testEmail1","newpassword","newdomaine");
        String newDomaine= "newdomaine";
        Mockito.when(connexionEleveDao.read(e1.getEmail())).thenReturn(e1);

        //WHEN
        eleveServices.modifyDomaine(e1.getEmail(),newDomaine);

        //THEN
        Mockito.verify(connexionEleveDao,Mockito.times(1)).modifyDomaine(e1.getId_eleve(),e1.getDomaine());
    }

    @Test
    public void shouldNotModifyDomaineAndThrowEleveNotFoundException() throws EleveNotFoundException {
        //GIVEN
        String email="email";
        String newDomaine= "newdomaine";
        Mockito.when(connexionEleveDao.read(email)).thenReturn(null);
        Exception result=null;

        //WHEN
        try{
            eleveServices.modifyDomaine(email,newDomaine);
        }catch(Exception e){
            result=e;
        }
        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(EleveNotFoundException.class);
        Mockito.verify(connexionEleveDao,Mockito.never()).modifyDomaine(Mockito.anyInt(),Mockito.anyString());

    }

    @Test
    public void shouldNotModifyDomaineAndThrowDonneIllegalFormatException() throws DonneIllegalFormatException{
        //GIVEN
        Eleve e1= new Eleve(1,"testNom1","testPrenom1","testEmail1","mdp","testdomaine1");
        Mockito.when(connexionEleveDao.read(e1.getEmail())).thenReturn(e1);
        Exception result=null;

        //WHEN
        try{
            eleveServices.modifyDomaine(e1.getEmail(), null);
        }catch(Exception e){
            result=e;
        }

        //THEN
        Assertions.assertThat(result).isNotNull().isInstanceOf(DonneIllegalFormatException.class);
        Mockito.verify(connexionEleveDao,Mockito.never()).modifyDomaine(Mockito.anyInt(),Mockito.anyString());
    }
}
