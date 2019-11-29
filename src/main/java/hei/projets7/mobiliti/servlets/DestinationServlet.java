package hei.projets7.mobiliti.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import hei.projets7.mobiliti.exception.ChoixAlreadyExistException;
import hei.projets7.mobiliti.exception.DestinationNotFoundException;
import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.pojos.Choix;
import hei.projets7.mobiliti.pojos.Destination;
import hei.projets7.mobiliti.pojos.Eleve;
import hei.projets7.mobiliti.services.ChoixServices;
import hei.projets7.mobiliti.services.DestinationServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import hei.projets7.mobiliti.services.EleveServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/destination")
public class DestinationServlet extends UtilsServlet {
    private EleveServices eleveServices = new EleveServices();
    private Integer destinationId=null;
    private String utilisateurConnecte=null;

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        destinationId = Integer.parseInt(req.getParameter("id"));
        utilisateurConnecte=(String) req.getSession().getAttribute("utilisateurConnecte");

        LOGGER.info("destination detail" + req.getParameter("id"));
        int destinationId = Integer.parseInt(req.getParameter("id"));
        String utilisateurConnecte=(String) req.getSession().getAttribute("utilisateurConnecte");

        WebContext webContext = new WebContext(req, resp, req.getServletContext());


        if (utilisateurConnecte!=null){
        Eleve eleve= null;
        try {
            eleve = eleveServices.getInstance().getEleve(utilisateurConnecte);
        } catch (EleveNotFoundException e) {
            e.printStackTrace();
        }
        webContext.setVariable("eleveConnecte",eleve);
        }
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        try {
            webContext.setVariable("destination", DestinationServices.getInstance().getDestination(destinationId));
        } catch (DestinationNotFoundException e) {
            LOGGER.warn(e);
        }

        templateEngine.process("Destination", webContext, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String email=(String) req.getSession().getAttribute("utilisateurConnecte");
        //CREATE ELEVE
        Eleve newEleve = null;
        try {
            newEleve = eleveServices.getInstance().getEleve(utilisateurConnecte);
        } catch (EleveNotFoundException e) {
            e.printStackTrace();
        }

        LOGGER.trace("eleve créé");

        //GET PARAMETERS
        Integer id_eleve = newEleve.getId_eleve();

        LOGGER.trace("J'ai récupéré "+ id_eleve+"comme id eleve");

        LOGGER.trace("J'ai récupéré "+destinationId+" comme id destination");


        //CREATE CHOIX

        Choix newChoix = new Choix(null,id_eleve,destinationId);

        try {
            Choix createdChoix=ChoixServices.getInstance().addChoix(newChoix);
        } catch (ChoixAlreadyExistException | SQLException e) {
            e.printStackTrace();
        }

        //REDIRECT
        resp.sendRedirect("profil");


    }


}
