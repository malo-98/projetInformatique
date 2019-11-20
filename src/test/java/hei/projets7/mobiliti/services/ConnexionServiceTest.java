package hei.projets7.mobiliti.services;

import hei.projets7.mobiliti.daos.impl.ConnexionEleveDaoImpl;
import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.pojos.Eleve;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.fail;

public class ConnexionServiceTest {

    @Mock
    ConnexionEleveDaoImpl connexionEleveDao;

    @InjectMocks
    EleveServices eleveServices;

    @Test
    public void shouldGetPasswordByEmailAndReturnPassword() throws EleveNotFoundException {
        //Given
        long id=42L;
        String email="email_test";
        String password="password_test";
        Mockito.when(connexionEleveDao.read(email)).thenReturn(new Eleve(1,"nom","prenom", email, password, "ITI"));

        //when
        String result=eleveServices.getPasswordByEmail(email);
        //then
        Assertions.assertThat(result);
        Assertions.assertThat(result).isEqualTo(password);
    }

    @Test
    public String shouldGetPasswordByEmailAndThrowEleveNotFoundException() throws EleveNotFoundException {
        //Given
        String email="email_test";
        Mockito.when(connexionEleveDao.read(email)).thenReturn(null);
        //When
        eleveServices.getPasswordByEmail(email);
        //Then
        return fail("email not exist");
    }

    }



