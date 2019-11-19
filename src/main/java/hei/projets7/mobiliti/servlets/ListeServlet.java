package hei.projets7.mobiliti.servlets;

import hei.projets7.mobiliti.exception.DestinationAlreadyExistException;
import hei.projets7.mobiliti.pojos.Destination;
import hei.projets7.mobiliti.services.DestinationServices;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/accueil")
public class ListeServlet extends UtilsServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        List<Destination> listOfDestination = DestinationServices.getInstance().destinationList();
        context.setVariable("destinationList",listOfDestination);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
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
        resp.sendRedirect("accueil");
    }
}
