package hei.projets7.mobiliti.servlets;

import hei.projets7.mobiliti.exception.DestinationAlreadyExistException;
import hei.projets7.mobiliti.exception.EleveAlreadyExistException;
import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.pojos.Destination;
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
import java.util.List;

@WebServlet("/liste")
public class ListeServlet extends UtilsServlet {
    private static final Logger LOGGER = LogManager.getLogger();
    private EleveServices eleveServices=new EleveServices();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        List<Destination> listOfDestination = DestinationServices.getInstance().destinationList();

        context.setVariable("destinationList",listOfDestination);


        String utilisateurConnecte=(String) req.getSession().getAttribute("utilisateurConnecte");
        if(utilisateurConnecte != null){
            LOGGER.info("J'ai récupéré " + utilisateurConnecte + " dans la session");
            try {
                Eleve eleve = eleveServices.getInstance().getEleve(utilisateurConnecte);
                context.setVariable("eleveConnecte",eleve);
            } catch (EleveNotFoundException e) {
                LOGGER.error(e.getMessage());
           }
        }


        templateEngine.process("Liste", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //GET PARAMETERS
        String name = req.getParameter("univ");
        String ville = req.getParameter("ville");
        String pays = req.getParameter("pays");
        String desc = req.getParameter("description");
        String domaine = req.getParameter("domaine");
        Integer nbre=Integer.parseInt(req.getParameter("nombre"));


        //CREATE DESTINATION
        Destination newDestination=new Destination(null, name, ville, pays, desc, domaine, nbre);
        try {
            Destination createdDestination=DestinationServices.getInstance().addDestination(newDestination);
        } catch (DestinationAlreadyExistException e) {
            e.printStackTrace();
        }

        //REDIRECT TO LIST
        resp.sendRedirect("liste");
    }
}
