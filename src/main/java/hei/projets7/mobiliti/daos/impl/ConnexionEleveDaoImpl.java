package hei.projets7.mobiliti.daos.impl;

import hei.projets7.mobiliti.daos.ConnexionEleveDao;
import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.pojos.Eleve;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnexionEleveDaoImpl implements ConnexionEleveDao {

    private static Eleve resultSetToEleve(ResultSet resultSet) throws SQLException {
        return new Eleve(resultSet.getInt("id_eleve"), resultSet.getString("Nom"),resultSet.getString("Prenom"), resultSet.getString("Email"), resultSet.getString("Mdp"),resultSet.getString("Domaine"));
    }

    public Eleve read(String email) {
        Eleve eleve = null;
        String query="SELECT id_eleve, Nom, Prenom, Domaine, Email, Mdp FROM eleve WHERE Email=?";
        try {
            Connection connection = DataSourceProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                eleve = resultSetToEleve(resultSet);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return eleve;
    }

    @Override
    public List<Eleve> read() {
        List<Eleve> eleves = new ArrayList<Eleve>();
        String query="SELECT id_eleve, Nom, Prenom, Domaine, Email, Mdp FROM eleve";
        try {
            Connection connection = DataSourceProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                eleves.add(resultSetToEleve(resultSet));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return eleves;
    }




    @Override
    public void modifyPassword(Integer id, String Password) {
        String sqlQuery="UPDATE eleve SET Mdp=? WHERE id_eleve=?;";
        try(Connection connection=DataSourceProvider.getDataSource().getConnection()){
            try(PreparedStatement statement=connection.prepareStatement(sqlQuery)){
                statement.setString(1, Password);
                statement.setInt(2, id);
                statement.executeUpdate();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void modifyNom(Integer id, String nom) {
        String sqlQuery="UPDATE eleve SET Nom=? WHERE id_eleve=?;";
        try(Connection connection=DataSourceProvider.getDataSource().getConnection()){
            try(PreparedStatement statement=connection.prepareStatement(sqlQuery)){
                statement.setString(1, nom);
                statement.setInt(2, id);
                statement.executeUpdate();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void modifyPrenom(Integer id, String prenom) {
        String sqlQuery="UPDATE eleve SET Prenom=? WHERE id_eleve=?;";
        try(Connection connection=DataSourceProvider.getDataSource().getConnection()){
            try(PreparedStatement statement=connection.prepareStatement(sqlQuery)){
                statement.setString(1, prenom);
                statement.setInt(2, id);
                statement.executeUpdate();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void modifyEmail(Integer id, String email) {
        String sqlQuery="UPDATE eleve SET Email=? WHERE id_eleve=?;";
        try(Connection connection=DataSourceProvider.getDataSource().getConnection()){
            try(PreparedStatement statement=connection.prepareStatement(sqlQuery)){
                statement.setString(1, email);
                statement.setInt(2, id);
                statement.executeUpdate();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void modifyDomaine(Integer id, String domaine) {
        String sqlQuery="UPDATE eleve SET Domaine=? WHERE id_eleve=?;";
        try(Connection connection=DataSourceProvider.getDataSource().getConnection()){
            try(PreparedStatement statement=connection.prepareStatement(sqlQuery)){
                statement.setString(1, domaine);
                statement.setInt(2, id);
                statement.executeUpdate();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEleve(Integer id) {
        String query="DELETE FROM eleve where id_eleve=?";
        try {
            Connection connection = DataSourceProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


