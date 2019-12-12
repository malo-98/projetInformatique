package hei.projets7.mobiliti.services;

import hei.projets7.mobiliti.daos.impl.DestinationDaoImpl;
import hei.projets7.mobiliti.exception.DestinationAlreadyExistException;
import hei.projets7.mobiliti.exception.DestinationNotFoundException;
import hei.projets7.mobiliti.exception.DonneIllegalFormatException;
import hei.projets7.mobiliti.entity.Destination;

import java.util.ArrayList;
import java.util.List;

public class DestinationServices {
    private static  class  DestinationListHolder{
        private final static DestinationServices instance = new DestinationServices();
    }

    public static DestinationServices getInstance(){return DestinationListHolder.instance;}

    private DestinationDaoImpl destinationDao= new DestinationDaoImpl();

    public DestinationServices(){
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


    public void deleteDestination(Integer id) throws DonneIllegalFormatException {

        if (id==null || " ".equals(id)){
            throw new DonneIllegalFormatException();
        }
        destinationDao.deleteDestinationByID(id);
    }

    public Destination getDestination(Integer id) throws DestinationNotFoundException {
        Destination destination = destinationDao.read(id);
        if (destination!=null){
            return destination;
        }
        throw new DestinationNotFoundException(id);
    }

    public void modifyNom(Integer idDestination, String nom) throws DestinationNotFoundException, DonneIllegalFormatException {
        Destination destination = getDestination(idDestination);
        if (nom==null || " ".equals(nom)){
            throw new DonneIllegalFormatException();
        }
        destinationDao.modifyNom(idDestination, nom);
    }

    public void modifyVille(Integer idDestination, String ville) throws DestinationNotFoundException, DonneIllegalFormatException {
        Destination destination = getDestination(idDestination);
        if (ville==null || " ".equals(ville)){
            throw new DonneIllegalFormatException();
        }
        destinationDao.modifyVille(idDestination, ville);
    }

    public void modifyPays(Integer idDestination, String pays) throws DestinationNotFoundException, DonneIllegalFormatException {
        Destination destination = getDestination(idDestination);
        if (pays==null || " ".equals(pays)){
            throw new DonneIllegalFormatException();
        }
        destinationDao.modifyPays(idDestination, pays);
    }

    public void modifyDescription(Integer idDestination, String description) throws DestinationNotFoundException, DonneIllegalFormatException {
        Destination destination = getDestination(idDestination);
        if (destination==null || " ".equals(destination)){
            throw new DonneIllegalFormatException();
        }
        destinationDao.modifyDescription(idDestination, description);
    }

    public void modifyDomaine(Integer idDestination, String domaine) throws DestinationNotFoundException, DonneIllegalFormatException {
        Destination destination = getDestination(idDestination);
        if (domaine==null || " ".equals(domaine)){
            throw new DonneIllegalFormatException();
        }
        destinationDao.modifyDomaine(idDestination, domaine);
    }

    public void modifyNbrePlace(Integer idDestination, Integer place) throws DestinationNotFoundException, DonneIllegalFormatException {
        Destination destination = getDestination(idDestination);
        if (place==null){
            throw new DonneIllegalFormatException();
        }
        destinationDao.modifyNbrePlace(idDestination, place);
    }

    public Destination read (Integer id){
        return destinationDao.read(id);
    }

    public Integer getIdbyName(String name){
        return  destinationDao.getIdDestinationByName(name);
    }


}

