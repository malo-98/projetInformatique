package hei.projets7.mobiliti.daos.impl;

import hei.projets7.mobiliti.pojos.Eleve;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnexionEleveDaoImpl implements ConnexionEleveDao {

    private Eleve resultSetToUser(ResultSet resultSet) throws SQLException {
        return new Eleve(resultSet.getInt("id_eleve"), resultSet.getString("Nom"),resultSet.getString("Prenom"), resultSet.getString("Email"), resultSet.getString("Mdp"),resultSet.getString("Domaine"));
    }

    @Override
    public Eleve read(String email) {
        Eleve eleve = null;
        String query="SELECT id, Email, Mdp FROM eleve WHERE Email=?";
        try {
            Connection connection = DataSourceProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                eleve = resultSetToUser(resultSet);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return eleve;
    }

    @Override
    public List<Eleve> read() {
        List<Eleve> eleves = new ArrayList<Eleve>();
        String query="SELECT id, Email, Mdp FROM eleve";
        try {
            Connection connection = DataSourceProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                eleves.add(resultSetToUser(resultSet));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return eleves;
    }
}
