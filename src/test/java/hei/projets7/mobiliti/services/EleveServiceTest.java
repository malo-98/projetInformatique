package hei.projets7.mobiliti.services;

import hei.projets7.mobiliti.daos.impl.InscriptionEleveDaoImpl;
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

@RunWith(MockitoJUnitRunner.class)
public class EleveServiceTest  {

    @Mock
    InscriptionEleveDaoImpl inscriptionEleveDao;

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


}
