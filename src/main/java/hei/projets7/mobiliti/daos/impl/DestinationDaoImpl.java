package hei.projets7.mobiliti.daos.impl;

import hei.projets7.mobiliti.daos.DestinationDao;
import hei.projets7.mobiliti.entity.Destination;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DestinationDaoImpl implements DestinationDao {

    private static Destination resultSetToDestination(ResultSet resultSet) throws SQLException {
        return new Destination(resultSet.getInt("id_destination"), resultSet.getString("Nom"),resultSet.getString("Ville"), resultSet.getString("Pays"), resultSet.getString("Description"),resultSet.getString("Domaine"), resultSet.getInt("Nombre_de_place"));
    }

    public Destination read(Integer id) {
        Destination destination = null;
        String query="SELECT id_destination, Nom, Ville, Pays, Description, Domaine, Nombre_de_place FROM destination WHERE id_destination=?";
        try {
            Connection connection = DataSourceProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                destination = resultSetToDestination(resultSet);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return destination;
    }

    public Integer getIdDestinationByName(String name){
        Integer id_destination=null;
        String sqlQuery="SELECT id_destination FROM destination where Nom=?;";
        try{
            Connection connection=DataSourceProvider.getConnection();
            PreparedStatement statement=connection.prepareStatement(sqlQuery);
            statement.setString(1,name);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                id_destination=resultSet.getInt("id_destination");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return id_destination;
    }

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
                statement.setString( 5,destination.getDomaine());
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
        String SQLquery="DELETE FROM destination where id_destination=?";
        String SQLquery2="DELETE FROM choix where id_destination=?";
        try{
           Connection connection=DataSourceProvider.getConnection();

            PreparedStatement statement2=connection.prepareStatement(SQLquery2);
            statement2.setInt(1,id);
            statement2.executeUpdate();

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

    public void modifyNom (int idDestination, String nom){
        String sqlQuery="UPDATE destination set Nom=? WHERE id_destination=?;";
        try(Connection connection=DataSourceProvider.getDataSource().getConnection()){
            try(PreparedStatement statement=connection.prepareStatement(sqlQuery)){
                statement.setString(1,nom);
                statement.setInt(2, idDestination);
                statement.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void modifyVille (int idDestination, String ville){
        String sqlQuery="UPDATE destination set Ville=? WHERE id_destination=?;";
        try(Connection connection=DataSourceProvider.getDataSource().getConnection()){
            try(PreparedStatement statement=connection.prepareStatement(sqlQuery)){
                statement.setString(1,ville);
                statement.setInt(2, idDestination);
                statement.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void modifyPays (int idDestination, String pays){
        String sqlQuery="UPDATE destination set Pays=? WHERE id_destination=?;";
        try(Connection connection=DataSourceProvider.getDataSource().getConnection()){
            try(PreparedStatement statement=connection.prepareStatement(sqlQuery)){
                statement.setString(1,pays);
                statement.setInt(2, idDestination);
                statement.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void modifyDomaine (int idDestination, String domaine){
        String sqlQuery="UPDATE destination set Domaine=? WHERE id_destination=?;";
        try(Connection connection=DataSourceProvider.getDataSource().getConnection()){
            try(PreparedStatement statement=connection.prepareStatement(sqlQuery)){
                statement.setString(1,domaine);
                statement.setInt(2, idDestination);
                statement.executeUpdate();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
