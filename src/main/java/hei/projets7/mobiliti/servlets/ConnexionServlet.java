package hei.projets7.mobiliti.servlets;

import hei.projets7.mobiliti.pojos.Eleve;
import hei.projets7.mobiliti.services.EleveServices;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/connexion")
public class ConnexionServlet extends UtilsServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        templateEngine.process("Connexion", context, resp.getWriter());

        //System.out.println("J'ai récepere "+req.getSession().getAttribute("utilisateurConnecte")+" dans la session");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String identifiant = req.getParameter("identifiant");

        //System.out.println("J'ai récupéré "+identifiant+" en paramètre de la requete ");

        req.getSession().setAttribute("utilisateurConnecte", identifiant);

        resp.sendRedirect("liste");




    }
}
