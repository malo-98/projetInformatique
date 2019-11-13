package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.daos.impl.InscriptionEleveDaoImpl;
import hei.projets7.mobiliti.services.EleveServices;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class InscriptionEleveServiceTest {

    @Mock
    InscriptionEleveDaoImpl inscriptionEleveDao;

    @InjectMocks
    EleveServices eleveServices;



}
