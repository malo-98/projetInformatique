package hei.projets7.mobiliti.servlets;


import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.pojos.Eleve;
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

@WebServlet("/accueil")
public class AccueilServlet extends UtilsServlet {

    private static final Logger LOGGER = LogManager.getLogger();
    private EleveServices eleveServices = new EleveServices();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String utilisateurConnecte=(String) req.getSession().getAttribute("utilisateurConnecte");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        if (utilisateurConnecte!=null){
        Eleve eleve= null;
        try {
            eleve = eleveServices.getInstance().getEleve(utilisateurConnecte);
        } catch (EleveNotFoundException e) {
            e.printStackTrace();
        }
        context.setVariable("eleveConnecte",eleve);
        }

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("Accueil", context, resp.getWriter());

    }
}

