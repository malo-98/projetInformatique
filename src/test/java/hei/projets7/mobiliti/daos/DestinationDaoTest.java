package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.daos.impl.DataSourceProvider;
import hei.projets7.mobiliti.daos.impl.DestinationDaoImpl;
import hei.projets7.mobiliti.pojos.Destination;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class DestinationDaoTest  {
    private DestinationDaoImpl destinationDao=new DestinationDaoImpl();

    @Before
    public void initDb() throws Exception{
        try(Connection connection= DataSourceProvider.getDataSource().getConnection();
            Statement statement=connection.createStatement()){
            statement.executeUpdate("DELETE FROM destination");
            statement.executeUpdate(
                    "INSERT INTO destination(id_destination, Nom, Ville, Pays, Domaine, Nombre_de_place )"
                        +"VALUES(1, 'testDestination1','testNom1','testVille1','testPays1', 'testDomaine1', 'testNombre_de_place1')");
            statement.executeUpdate(
                    "INSERT INTO destination(id_destination, Nom, Ville, Pays, Domaine, Nombre_de_place )"
                            +"VALUES(2, 'testDestination2','testNom2','testVille2','testPays2', 'testDomaine2', 'testNombre_de_place2')");
        }
    }

    @Test
    public void shouldListDestination(){
        List<Destination> destinations=destinationDao.listDestinations();

        //THEN
        assertThat(destinations).hasSize(2);
        assertThat(destinations).extracting(
                Destination::getId,
                Destination::getName,
                Destination::getCity,
                Destination::getCountry,
                Destination::getDomaine,
                Destination::getPlace).containsOnly(
                        tuple(1, "testDestination1","testNom1","testVille1","testPays1", "testDomaine1", "testNombre_de_place1"),
                        tuple(2, "testDestination2","testNom2","testVille2","testPays2", "testDomaine2", "testNombre_de_place2")
        );
    }
}
