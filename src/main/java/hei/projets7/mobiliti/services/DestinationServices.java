package hei.projets7.mobiliti.services;

import hei.projets7.mobiliti.daos.impl.DestinationDaoImpl;
import hei.projets7.mobiliti.pojos.Destination;

import java.util.List;

public class DestinationServices {
    private static  class  DestinationListHolder{
        private final static DestinationServices instance = new DestinationServices();
    }

    public static DestinationServices getInstance(){return DestinationListHolder.instance;}

    private DestinationDaoImpl destinationDao= new DestinationDaoImpl();

    private DestinationServices(){
    }

    public List<Destination> destinationList(){return destinationDao.listDestinations();}

    public Destination addDestination(Destination destination){return destinationDao.addDestination(destination);}

    public void deleteDestination(Integer id){destinationDao.deleteDestinationByID(id);}

}

