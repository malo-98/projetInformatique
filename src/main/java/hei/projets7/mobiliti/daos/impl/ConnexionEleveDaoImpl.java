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
<<<<<<< HEAD
    public String getPasswordByEmail(String email) throws EleveNotFoundException {
        return null;
    }

    /*@Override
    public String getPasswordByEmail(String email) {
        Eleve eleve = null;
        String query="SELECT Mdp FROM eleve WHERE Email=?";
        try {
            Connection connection = DataSourceProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            ResultSet

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }*/

    @Override
    public Eleve getEleve(String email) throws EleveNotFoundException {
        return null;
    }

    /*@Override
    public Eleve getEleve(String email) {
        String query="SELECT eleve FROM eleve WHERE Email=?";
        try {
            Connection connection = DataSourceProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                eleve = resultSetToEleve(resultSet);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }*/

=======
    public String getPasswordByEmail(String email) {
        return null;
    }

    @Override
    public Eleve getEleve(String email) {
        return null;
    }
>>>>>>> 4561af820c2925d257f8b336af00487ab728aea8

    @Override
    public void modifyPassword(String email, String Password) {
        String sqlQuery="UPDATE eleve SET Mdp=? WHERE email=?;";
        try(Connection connection=DataSourceProvider.getDataSource().getConnection()){
            try(PreparedStatement statement=connection.prepareStatement(sqlQuery)){
                statement.setString(1, Password);
                statement.setString(2, email);
                statement.executeUpdate();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteEleve(String email) {
        String query="DELETE FROM eleve where email=?";
        try {
            Connection connection = DataSourceProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,email);
            statement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
<<<<<<< HEAD

=======
>>>>>>> 4561af820c2925d257f8b336af00487ab728aea8
}


