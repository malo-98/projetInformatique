package hei.projets7.mobiliti.daos.impl;

import hei.projets7.mobiliti.daos.FavorisDao;
import hei.projets7.mobiliti.pojos.Destination;
import hei.projets7.mobiliti.pojos.Favoris;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FavorisDaoImpl implements FavorisDao {
    @Override
    public List<Favoris> listFavorisByIdEleve(Integer id) {
       List<Favoris> favorisList=new ArrayList<>();
       String SQLquery="SELECT * FROM favoris WHERE id_eleve=?";
       try{
           Connection connection=DataSourceProvider.getConnection();
           PreparedStatement statement=connection.prepareStatement(SQLquery);
           statement.setInt(1,id);
           ResultSet resultSet=statement.executeQuery();
           while (resultSet.next()){
               Favoris favoris=new Favoris(
                       resultSet.getInt("id_favoris"),
                       resultSet.getString("Nom"),
                       resultSet.getString("Ville"),
                       resultSet.getString("Pays"),
                       resultSet.getString("Description"),
                       resultSet.getString("Domaine"),
                       resultSet.getInt("Nombre_de_place"),
                       resultSet.getInt("id_eleve")
               );
               favorisList.add(favoris);
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
       return favorisList;
    }

    @Override
    public Favoris addFavoris(Favoris favoris) {
        try(Connection connection=DataSourceProvider.getDataSource().getConnection()){
            String sqlQuery="INSERT INTO favoris(Nom, Ville, Pays, Description, Domaine, Nombre_de_place, id_eleve) VALUE(?, ?, ?, ?, ?, ?,?);";
            try(PreparedStatement statement=connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)){
                statement.setString(1, favoris.getName());
                statement.setString(2, favoris.getCity());
                statement.setString(3,favoris.getCountry());
                statement.setString(4,favoris.getDescription());
                statement.setString(5,favoris.getDomaine());
                statement.setInt(6,favoris.getPlace() );
                statement.setInt(6,favoris.getIdEleve() );
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
    public void deleteFavoris(Integer id) {
        String SQLquery="DELETE FROM favoris where id=?";
        try{
            Connection connection=DataSourceProvider.getConnection();
            PreparedStatement statement=connection.prepareStatement(SQLquery);
            statement.setInt(1,id);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
