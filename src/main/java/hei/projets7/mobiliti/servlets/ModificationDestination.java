package hei.projets7.mobiliti.servlets;

import hei.projets7.mobiliti.exception.DestinationNotFoundException;
import hei.projets7.mobiliti.exception.DonneIllegalFormatException;
import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.pojos.Eleve;
import hei.projets7.mobiliti.services.DestinationServices;
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

@WebServlet("/modifDestination")
public class ModificationDestination extends UtilsServlet{

    private Integer idDestination=null;
    private static final Logger LOGGER = LogManager.getLogger();
    private DestinationServices destinationServices = new DestinationServices();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("modif destination " + req.getParameter("id"));
        int destinationId = Integer.parseInt(req.getParameter("id"));
        idDestination=destinationId;
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        try {
            webContext.setVariable("destination", DestinationServices.getInstance().getDestination(destinationId));
        } catch (DestinationNotFoundException e) {
            LOGGER.warn(e);
        }

        templateEngine.process("ModificationDestination", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String nom = req.getParameter("NouveauNom");
        String ville= req.getParameter("NouvelleVille");
        String pays= req.getParameter("NouveauPays");
        String description= req.getParameter("NouvelleDescription");
        String domaine=req.getParameter("NouveauDomaine");
        Integer place=Integer.parseInt(req.getParameter("NouvellePlace"));


        try {
            DestinationServices.getInstance().modifyNom(idDestination,nom);
        } catch (DestinationNotFoundException e) {
            LOGGER.warn(e);
        } catch (DonneIllegalFormatException e) {
            LOGGER.warn(e);
        }

        try {
            DestinationServices.getInstance().modifyVille(idDestination,ville);
        } catch (DestinationNotFoundException e) {
            LOGGER.warn(e);
        } catch (DonneIllegalFormatException e) {
            LOGGER.warn(e);
        }

        try {
            DestinationServices.getInstance().modifyPays(idDestination,pays);
        } catch (DestinationNotFoundException e) {
            LOGGER.warn(e);
        } catch (DonneIllegalFormatException e) {
            LOGGER.warn(e);
        }

        try {
            DestinationServices.getInstance().modifyDescription(idDestination,description);
        } catch (DestinationNotFoundException e) {
            LOGGER.warn(e);
        } catch (DonneIllegalFormatException e) {
            LOGGER.warn(e);
        }

        try {
            DestinationServices.getInstance().modifyDomaine(idDestination,domaine);
        } catch (DestinationNotFoundException e) {
            LOGGER.warn(e);
        } catch (DonneIllegalFormatException e) {
            LOGGER.warn(e);
        }

        try {
            DestinationServices.getInstance().modifyNbrePlace(idDestination,place);
        } catch (DestinationNotFoundException e) {
            LOGGER.warn(e);
        } catch (DonneIllegalFormatException e) {
            LOGGER.warn(e);
        }

        resp.sendRedirect("accueil");
    }

}
