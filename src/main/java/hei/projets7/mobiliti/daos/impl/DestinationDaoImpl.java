package hei.projets7.mobiliti.daos.impl;

import hei.projets7.mobiliti.daos.DestinationDao;
import hei.projets7.mobiliti.pojos.Destination;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DestinationDaoImpl implements DestinationDao {

    public List<Destination> listDestinations(){
        List<Destination> destinations=new ArrayList<>();
        try (Connection connection=DataSourceProvider.getDataSource().getConnection()){
            try(Statement statement=connection.createStatement()){
                try(ResultSet results=statement.executeQuery("SELECT * FROM destination ORDER BY Domaine;")){
                    while (results.next()){
                        Destination destination=new Destination(
                                results.getInt("id_destination"),
                                results.getString("Nom"),
                                results.getString("Ville"),
                                results.getString("Pays"),
                                results.getString("Description"),
                                results.getString("Domaine"),
                                results.getInt("Nombre_de_place")
                        );
                        destinations.add(destination);
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return destinations;
    }

    public Destination addDestination(Destination destination){
        try(Connection connection=DataSourceProvider.getDataSource().getConnection()){
            String sqlQuery="INSERT INTO destination(Nom, Ville, Pays, Description, Domaine, Nombre_de_place) VALUE(?, ?, ?, ?, ?, ?);";
            try(PreparedStatement statement=connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)){
                statement.setString(1, destination.getName());
                statement.setString(2, destination.getCity());
                statement.setString(3,destination.getCountry());
                statement.setString(4,destination.getDescription());
                statement.setString(5,destination.getDomaine());
                statement.setInt(6,destination.getPlace() );
                statement.executeUpdate();

                try(ResultSet resultSet=statement.getGeneratedKeys()){
                    if(resultSet.next()){
                        int DestinationId=resultSet.getInt("id_destination");
                        destination.setId(DestinationId);
                        return destination;
                    }
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        throw new RuntimeException("problème pour l'exécution de cette requête");
    }

    public void deleteDestinationByID(Integer id){
        String SQLquery="DELETE FROM destination where id=?";
        try{
            Connection connection=DataSourceProvider.getConnection();
            PreparedStatement statement=connection.prepareStatement(SQLquery);
            statement.setInt(1,id);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void modifyNbrePlace(int idDestination, int nbrePlace){
        String sqlQuery="UPDATE destination SET Nombre_de_place=? WHERE id_destination=?;";
        try(Connection connection=DataSourceProvider.getDataSource().getConnection()){
            try(PreparedStatement statement=connection.prepareStatement(sqlQuery)){
                statement.setInt(1, nbrePlace);
                statement.setInt(2, idDestination);
                statement.executeUpdate();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void modifyDescription (int idDestination, String description){
        String sqlQuery="UPDATE destination set Description=? WHERE id_destination=?;";
        try(Connection connection=DataSourceProvider.getDataSource().getConnection()){
            try(PreparedStatement statement=connection.prepareStatement(sqlQuery)){
                statement.setString(1,description);
                statement.setInt(2, idDestination);
                statement.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
