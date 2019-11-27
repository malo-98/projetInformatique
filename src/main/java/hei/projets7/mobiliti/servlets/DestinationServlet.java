package hei.projets7.mobiliti.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import hei.projets7.mobiliti.exception.DestinationNotFoundException;
import hei.projets7.mobiliti.pojos.Destination;
import hei.projets7.mobiliti.services.DestinationServices;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/destination")
public class DestinationServlet extends UtilsServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int destinationId = Integer.parseInt(req.getParameter("id"));

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        try {
            webContext.setVariable("destination", DestinationServices.getInstance().getDestination(destinationId));
        } catch (DestinationNotFoundException e) {
            e.printStackTrace();
        }

        templateEngine.process("destination", webContext, resp.getWriter());
    }
}
