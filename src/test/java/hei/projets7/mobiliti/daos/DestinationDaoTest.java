package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.daos.impl.DataSourceProvider;
import hei.projets7.mobiliti.daos.impl.DestinationDaoImpl;
import hei.projets7.mobiliti.daos.impl.InscriptionEleveDaoImpl;
import hei.projets7.mobiliti.pojos.Destination;
import hei.projets7.mobiliti.pojos.Eleve;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class DestinationDaoTest  {
    private DestinationDaoImpl destinationDao=new DestinationDaoImpl();
    private InscriptionEleveDaoImpl inscriptionEleveDao=new InscriptionEleveDaoImpl();

    @Before
    public void initDb() throws Exception{
        try(Connection connection= DataSourceProvider.getDataSource().getConnection();
            Statement statement=connection.createStatement()){
            statement.executeUpdate("DELETE FROM destination");
            statement.executeUpdate("DELETE FROM Eleve");
            statement.executeUpdate(
                    "INSERT INTO destination(id_destination, Nom, Ville, Pays, Description, Domaine, Nombre_de_place )"
                        +"VALUES(1,'testNom1','testVille1','testPays1', 'test description 1', 'testDomaine1', 4)");
            statement.executeUpdate(
                    "INSERT INTO destination(id_destination, Nom, Ville, Pays, Description, Domaine, Nombre_de_place )"
                            +"VALUES(2,'testNom2','testVille2','testPays2', 'test description 2', 'testDomaine2', 11)");
            statement.executeUpdate(
                    "INSERT INTO eleve(id_eleve,Nom,Prenom,Domaine,Email,Mdp)"
                            +"VALUES(1,'testNom1','testPrenom1','testDomaine1','testEmail1','testMdp1')");
            statement.executeUpdate(
                    "INSERT INTO eleve(id_eleve,Nom,Prenom,Domaine,Email,Mdp)"
                            +"VALUES(2,'testNom2','testPrenom2','testDomaine2','testEmail2','testMdp2')");
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
                Destination::getDescription,
                Destination::getDomaine,
                Destination::getPlace).containsOnly(
                        tuple(1,"testNom1","testVille1","testPays1", "test description 1", "testDomaine1", 4),
                        tuple(2,"testNom2","testVille2","testPays2", "test description 2", "testDomaine2", 11)
        );
    }

    @Test
    public void shouldAddDestination(){
        //GIVEN
        Destination destinationAcreer=new Destination(null, "UnivTest","Londres","England","Il s'agit de a de la description test", "BFA",4);

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
                assertThat(resultSet.getString("Description")).isEqualTo(destinationCree.getDescription());
                assertThat(resultSet.getString("Domaine")).isEqualTo(destinationCree.getDomaine());
                assertThat(resultSet.getInt("Nombre_de_place")).isEqualTo(destinationCree.getPlace());
                assertThat(resultSet.next()).isFalse();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Test
    public void shouldModifyPlace(){
        //WHEN
        destinationDao.modifyNbrePlace(1, 13);

        //THEN
        try (Connection connection=DataSourceProvider.getDataSource().getConnection();
        PreparedStatement statement=connection.prepareStatement("SELECT * FROM destination WHERE id_destination=1")){
            try (ResultSet resultSet=statement.executeQuery()){
                assertThat(resultSet.getInt("Nombre_de_place")).isEqualTo(13);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void shouldModifyDescription(){
        //WHEN
        destinationDao.modifyDescription(1, "LA vies est belle");

        //THEN
        try (Connection connection=DataSourceProvider.getDataSource().getConnection();
             PreparedStatement statement=connection.prepareStatement("SELECT * FROM destination WHERE id_destination=1")){
            try (ResultSet resultSet=statement.executeQuery()){
                assertThat(resultSet.getString("Descritpion")).isEqualTo("LA vies est belle");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    
    @Test
    public void shouldListEleve(){
        //WHEN
        List<Eleve> eleves=inscriptionEleveDao.listEleve();

        //THEN
        assertThat(eleves).hasSize(2);
        assertThat(eleves).extracting(
                Eleve::getId_eleve,
                Eleve::getNom,
                Eleve::getPrenom,
                Eleve::getEmail,
                Eleve::getDomaine,
                Eleve::getPassword).containsOnly(
                tuple(1,"testNom1","testPrenom1","testDomaine1","testEmail1","testMdp1"),
                tuple(2,"testNom2","testPrenom2","testDomaine2","testEmail2","testMdp2")
        );

    }
}
