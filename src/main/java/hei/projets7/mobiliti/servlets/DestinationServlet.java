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
    private Integer count=null;
    private String utilisateurConnecte=null;

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        destinationId = Integer.parseInt(req.getParameter("id"));
        utilisateurConnecte=(String) req.getSession().getAttribute("utilisateurConnecte");

        LOGGER.info("destination detail" + req.getParameter("id"));
        int destinationId = Integer.parseInt(req.getParameter("id"));
        String utilisateurConnecte=(String) req.getSession().getAttribute("utilisateurConnecte");
        count=ChoixServices.getInstance().countChoixByIdDestination(destinationId);
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

        webContext.setVariable("count",count);
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

        //CREATE ELEVE
        Eleve newEleve = null;
        try {
            newEleve = eleveServices.getInstance().getEleve(utilisateurConnecte);
        } catch (EleveNotFoundException e) {
            e.printStackTrace();
        }
        //GET PARAMETERS
        Integer id_eleve = newEleve.getId_eleve();

        //CREATE CHOIX
        ChoixServices.getInstance().modifyChoix(id_eleve);
        Choix newChoix = new Choix(null,id_eleve,destinationId);

        try {
            Choix createdChoix=ChoixServices.getInstance().addChoix(newChoix);
        } catch (SQLException e) {
            e.printStackTrace();
        }



        //REDIRECT
        resp.sendRedirect("profil");


    }


}
