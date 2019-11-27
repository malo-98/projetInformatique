package hei.projets7.mobiliti.services;

import hei.projets7.mobiliti.daos.DestinationDao;
import hei.projets7.mobiliti.daos.impl.DestinationDaoImpl;
import hei.projets7.mobiliti.exception.DestinationAlreadyExistException;
import hei.projets7.mobiliti.exception.DestinationNotFoundException;
import hei.projets7.mobiliti.exception.EleveAlreadyExistException;
import hei.projets7.mobiliti.pojos.Destination;

import java.util.ArrayList;
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

    public Destination addDestination(Destination destination) throws DestinationAlreadyExistException {

        List<Destination> destinations = destinationDao.listDestinations();

        List<String> names=new ArrayList<String>();

        for (int i=0;i<destinations.size();i++){
            names.add(destinations.get(i).getName());
        }

        if(names.contains(destination.getName())) {
            throw new DestinationAlreadyExistException(destination);
        }

        if(destination == null) {
            throw new IllegalArgumentException("The destination can not be null.");
        }
        if(destination.getName() == null || " ".equals(destination.getName())){
            throw new IllegalArgumentException("The Name can not be null.");
        }
        if(destination.getCity() == null || " ".equals(destination.getCity())){
            throw new IllegalArgumentException("The City can not be null.");
        }
        if(destination.getCountry() == null || " ".equals(destination.getCountry())){
            throw new IllegalArgumentException("The Country can not be null.");
        }

        return destinationDao.addDestination(destination);
    }


    public void deleteDestination(Integer id){destinationDao.deleteDestinationByID(id);}

    public Destination getDestination(Integer id) throws DestinationNotFoundException {
        Destination destination = destinationDao.read(id);
        if (destination!=null){
            return destination;
        }
        throw new DestinationNotFoundException(id);
    }

}

