package hei.projets7.mobiliti.daos.impl;

import hei.projets7.mobiliti.daos.ChoixDao;
import hei.projets7.mobiliti.exception.ChoixAlreadyExistException;
import hei.projets7.mobiliti.pojos.Choix;
import hei.projets7.mobiliti.pojos.Eleve;


import java.sql.*;

public class ChoixDaoImpl implements ChoixDao {

    private static Choix resultSetToChoix(ResultSet resultSet) throws SQLException {
        return new Choix(resultSet.getInt("id_choix"), resultSet.getInt("id_eleve"),resultSet.getInt("id_destination"));
    }

    @Override
    public Choix read(Integer id_eleve) {
        Choix choix = null;
        String query="SELECT id_choix, id_eleve, id_destination FROM choix WHERE id_eleve=?";
        try {
            Connection connection = DataSourceProvider.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id_eleve);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                choix = resultSetToChoix(resultSet);
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return choix;
    }

    @Override
    public void modifyChoix(Integer id_eleve) {
        String deleteQuery="DELETE FROM choix where id_eleve=?;";
        try{
            Connection connection=DataSourceProvider.getConnection();
            PreparedStatement statement=connection.prepareStatement(deleteQuery);
            statement.setInt(1,id_eleve);
            statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }



    @Override
    public Choix addChoix(Choix choix) throws SQLException {
        try(Connection connection=DataSourceProvider.getDataSource().getConnection()){
            String sqlQuery="INSERT INTO choix(id_eleve, id_destination) VALUE(?, ?);";
            try(PreparedStatement statement=connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                //statement.setInt(1, choix.getId_choix());
                statement.setInt(1, choix.getId_eleve());
                statement.setInt(2,choix.getId_destination());
                statement.executeUpdate();

                try (ResultSet resultSet = statement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        int ChoixId = resultSet.getInt("id_choix");
                        choix.setId_choix(ChoixId);
                        return choix;
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        throw new RuntimeException("problème pour l'exécution de cette requête");
    }


}
