package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.daos.impl.DataSourceProvider;
import hei.projets7.mobiliti.daos.impl.DestinationDaoImpl;
import hei.projets7.mobiliti.pojos.Destination;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
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
                        +"VALUES(1,'testNom1','testVille1','testPays1', 'testDomaine1', 4)");
            statement.executeUpdate(
                    "INSERT INTO destination(id_destination, Nom, Ville, Pays, Domaine, Nombre_de_place )"
                            +"VALUES(2,'testNom2','testVille2','testPays2', 'testDomaine2', 11)");
        }
    }

    @Test
    public void shouldListDestination(){
        //WHEN
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
                        tuple(1,"testNom1","testVille1","testPays1", "testDomaine1", 4),
                        tuple(2,"testNom2","testVille2","testPays2", "testDomaine2", 11)
        );
    }


    @Test
    public void shouldAddDestination(){
        //GIVEN
        Destination destinationAcreer=new Destination(null, "UnivTest","Londres","England","BFA",4);

        //WHEN
        Destination destinationCree=destinationDao.addDestination(destinationAcreer);

        //THEN
        try(Connection connection=DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement=connection.prepareStatement("SELECT * FROM destination WHERE id_destination=?")){
            statement.setInt(1,destinationCree.getId());
            try(ResultSet resultSet=statement.executeQuery()){
                assertThat(resultSet.next()).isTrue();
                assertThat(resultSet.getInt("id_destination")).isEqualTo(destinationCree.getId());
                assertThat(resultSet.getString("Nom")).isEqualTo(destinationCree.getName());
                assertThat(resultSet.getString("Ville")).isEqualTo(destinationCree.getCity());
                assertThat(resultSet.getString("Pays")).isEqualTo(destinationCree.getCountry());
                assertThat(resultSet.getString("Domaine")).isEqualTo(destinationCree.getDomaine());
                assertThat(resultSet.getInt("Nombre_de_place")).isEqualTo(destinationCree.getPlace());
                assertThat(resultSet.next()).isFalse();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}
