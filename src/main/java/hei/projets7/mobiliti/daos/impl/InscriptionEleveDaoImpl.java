package hei.projets7.mobiliti.daos.impl;

import hei.projets7.mobiliti.daos.InscriptionEleveDao;
import hei.projets7.mobiliti.pojos.Eleve;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscriptionEleveDaoImpl implements InscriptionEleveDao {
    @Override
    public Eleve addEleve(Eleve eleve) {
        try(Connection connection=DataSourceProvider.getDataSource().getConnection()){
            String sqlQuery="INSERT INTO Eleve(Nom,Prenom,Email,Mdp,Domaine) VALUE(?, ?, ?, ?, ?);";
            try(PreparedStatement statement=connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)){
                statement.setString(1,eleve.getNom());
                statement.setString(2, eleve.getPrenom());
                statement.setString(3,eleve.getEmail());
                statement.setString(4,eleve.getPassword());
                statement.setString(5,eleve.getDomaine() );
                statement.executeUpdate();

                try(ResultSet resultSet=statement.getGeneratedKeys()){
                    if(resultSet.next()){
                        int DestinationId=resultSet.getInt("id_eleve");
                        eleve.setId_eleve(DestinationId);
                        return eleve;
                    }
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("problème pour l'exécution de la requête inscription");
    }



    @Override
    public List<Eleve> listEleve(){
        List<Eleve> eleves=new ArrayList<>();
        try (Connection connection=DataSourceProvider.getDataSource().getConnection()){
            try(Statement statement=connection.createStatement()){
                try(ResultSet results=statement.executeQuery("SELECT * FROM eleve ;")){
                    while (results.next()){
                        Eleve eleve=new Eleve(
                                results.getInt("id_eleve"),
                                results.getString("Nom"),
                                results.getString("Prenom"),
                                results.getString("Email"),
                                results.getString("Mdp"),
                                results.getString("Domaine")
                        );
                        eleves.add(eleve);
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return eleves;
    }








}
