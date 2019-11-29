package hei.projets7.mobiliti.servlets;

import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.pojos.Destination;
import hei.projets7.mobiliti.pojos.Eleve;
import hei.projets7.mobiliti.services.DestinationServices;
import hei.projets7.mobiliti.services.EleveServices;
import hei.projets7.mobiliti.utils.MotDePasseUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.channels.CancelledKeyException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/connexion")
public class ConnexionServlet extends UtilsServlet {

    private static final Logger LOGGER = LogManager.getLogger();
    private EleveServices eleveServices = new EleveServices();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("Connexion", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());
        boolean erreurconnexion = true;
        String redirectPath = "connexion";

        //GET PARAMETERS
        String email = req.getParameter("email");
        LOGGER.trace("email : {}", email);

        String mdp = req.getParameter("mdp");
        LOGGER.info("mdp : {}", mdp);

        //COMPARE WITH BDD
        try {
            if (EleveServices.getInstance().checkPassword(email, mdp)) {
                erreurconnexion = false;
                redirectPath = "accueil";
                req.getSession().setAttribute("utilisateurConnecte", email);
            }
        } catch (EleveNotFoundException e) {
            LOGGER.debug(e.getMessage());
        }

        req.getSession().setAttribute("erreurconnexion", erreurconnexion);
        resp.sendRedirect(redirectPath);
    }

}

