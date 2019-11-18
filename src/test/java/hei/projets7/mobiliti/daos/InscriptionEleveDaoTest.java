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

public class InscriptionEleveDaoTest  {

    private InscriptionEleveDaoImpl inscriptionEleveDao=new InscriptionEleveDaoImpl();

    @Before
    public void initDb() throws Exception{
        try(Connection connection= DataSourceProvider.getDataSource().getConnection();
            Statement statement=connection.createStatement()){
            statement.executeUpdate("DELETE FROM Eleve");
            statement.executeUpdate(
                    "INSERT INTO eleve(id_eleve,Nom,Prenom,Domaine,Email,Mdp)"
                            +"VALUES(1,'testNom1','testPrenom1','testDomaine1','testEmail1','testMdp1')");
            statement.executeUpdate(
                    "INSERT INTO eleve(id_eleve,Nom,Prenom,Domaine,Email,Mdp)"
                            +"VALUES(2,'testNom2','testPrenom2','testDomaine2','testEmail2','testMdp2')");
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
                Eleve::getDomaine,
                Eleve::getEmail,
                Eleve::getPassword).containsOnly(
                tuple(1,"testNom1","testPrenom1","testDomaine1","testEmail1","testMdp1"),
                tuple(2,"testNom2","testPrenom2","testDomaine2","testEmail2","testMdp2")
        );

    }

    @Test
    public void shouldAddEleve(){
        //GIVEN
        Eleve eleveACreer=new Eleve(null, "nom_test","prenom_test","email_test","mdp_test", "dom_test");

        //WHEN
        Eleve eleveCree=inscriptionEleveDao.addEleve(eleveACreer);

        //THEN
        try(Connection connection=DataSourceProvider.getDataSource().getConnection();
            PreparedStatement statement=connection.prepareStatement("SELECT * FROM eleve WHERE id_eleve=?")){
            statement.setInt(1, eleveCree.getId_eleve());
            try(ResultSet resultSet=statement.executeQuery()){
                assertThat(resultSet.next()).isTrue();
                assertThat(resultSet.getInt("id_eleve")).isEqualTo(eleveCree.getId_eleve());
                assertThat(resultSet.getString("Nom")).isEqualTo(eleveCree.getNom());
                assertThat(resultSet.getString("Prenom")).isEqualTo(eleveCree.getPrenom());
                assertThat(resultSet.getString("Domaine")).isEqualTo(eleveCree.getDomaine());
                assertThat(resultSet.getString("Email")).isEqualTo(eleveCree.getEmail());
                assertThat(resultSet.getString("mdp")).isEqualTo(eleveCree.getPassword());
                assertThat(resultSet.next()).isFalse();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}



