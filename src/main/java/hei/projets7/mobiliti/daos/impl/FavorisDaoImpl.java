package hei.projets7.mobiliti.daos.impl;

import hei.projets7.mobiliti.daos.FavorisDao;
import hei.projets7.mobiliti.exception.ChoixAlreadyExistException;
import hei.projets7.mobiliti.pojos.Choix;
import hei.projets7.mobiliti.pojos.Destination;
import hei.projets7.mobiliti.pojos.Favoris;
import hei.projets7.mobiliti.services.DestinationServices;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavorisDaoImpl implements FavorisDao {
    @Override
    public List<Destination> listFavorisByIdEleve(Integer id) {
       List<Favoris> favorisList=new ArrayList<>();
       List<Destination> destinationList=new ArrayList<>();
       String SQLquery="SELECT * FROM favoris WHERE id_eleve=?";
       try{
           Connection connection=DataSourceProvider.getConnection();
           PreparedStatement statement=connection.prepareStatement(SQLquery);
           statement.setInt(1,id);
           ResultSet resultSet=statement.executeQuery();
           while (resultSet.next()){
               Favoris favoris=new Favoris(
                       resultSet.getInt("id_favoris"),
                       resultSet.getInt("id_eleve"),
                       resultSet.getInt("id_destination")
               );
               favorisList.add(favoris);
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
        for (Favoris favoris:favorisList) {
            Integer id_destination=favoris.getIdDestination();
            Destination destination=DestinationServices.getInstance().read(id_destination);
            destinationList.add(destination);
        }
        return destinationList;
    }

    @Override
    public Favoris addFavoris(Favoris favoris) {
        try(Connection connection=DataSourceProvider.getDataSource().getConnection()){
            String sqlQuery="INSERT INTO favoris( id_eleve, id_destination) VALUE(?, ?);";
            try(PreparedStatement statement=connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)){
                statement.setInt(1, favoris.getIdEleve());
                statement.setInt(2, favoris.getIdDestination());
                statement.executeUpdate();

                try(ResultSet resultSet=statement.getGeneratedKeys()){
                    if(resultSet.next()){
                        int favorisId=resultSet.getInt("id_favoris");
                        favoris.setId(favorisId);
                        return favoris;
                    }
                }

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        throw new RuntimeException("problème pour l'exécution de cette requête");
    }


    @Override
    public void modifyFavoris(Integer id_destination, Integer id_user) {
        String SQLquery="DELETE FROM favoris where id_eleve=? and id_destination=? ";
        try{
            Connection connection=DataSourceProvider.getConnection();
            PreparedStatement statement=connection.prepareStatement(SQLquery);
            statement.setInt(1,id_user);
            statement.setInt(2,id_destination);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
