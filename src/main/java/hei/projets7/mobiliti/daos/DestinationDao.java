package hei.projets7.mobiliti.daos;

import hei.projets7.mobiliti.pojos.Destination;

import java.util.List;

public interface DestinationDao {
    Destination read(Integer id);

    List<Destination> listDestinations();

    Destination addDestination(Destination destination);

    void modifyNbrePlace(int idDestination, int nbrePlace);

    void modifyDescription(int idDestination, String description);
}
