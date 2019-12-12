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
public class ModificationDestinationServlet extends UtilsServlet{

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
        String place=req.getParameter("NouvellePlace");


        try {
            DestinationServices.getInstance().modifyNom(idDestination,nom);
            DestinationServices.getInstance().modifyVille(idDestination,ville);
            DestinationServices.getInstance().modifyPays(idDestination,pays);
            DestinationServices.getInstance().modifyDescription(idDestination,description);
            DestinationServices.getInstance().modifyDomaine(idDestination,domaine);
            DestinationServices.getInstance().modifyNbrePlace(idDestination,Integer.parseInt(place));
            resp.sendRedirect("liste");
        } catch (DonneIllegalFormatException e) {
            LOGGER.warn(e);
            req.getSession().setAttribute("errorMessage", e.getMessage());
            resp.sendRedirect("modifDestination?id="+idDestination);
        }catch (DestinationNotFoundException e) {
            LOGGER.warn(e);
            req.getSession().setAttribute("errorMessage", e.getMessage());
            resp.sendRedirect("modifDestination?id="+idDestination);
        }

    }
}
