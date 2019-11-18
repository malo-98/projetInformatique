package hei.projets7.mobiliti.servlets;

import hei.projets7.mobiliti.pojos.Eleve;
import hei.projets7.mobiliti.services.EleveServices;
import hei.projets7.mobiliti.utils.MotDePasseUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/connexion")
public class ConnexionServlet extends UtilsServlet {

    private Eleve test = new Eleve(5,"TEST","test", "test@gmail.com","1234","ITI");

    @Override
    public void init() throws ServletException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String utilisateurConnecte = (String) req.getSession().getAttribute("utilisateurConnecte");


        if(utilisateurConnecte==null || "".equals(utilisateurConnecte)) {
            WebContext context = new WebContext(req, resp, req.getServletContext());
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("Connexion", context, resp.getWriter());
        }
        else{
            WebContext context = new WebContext(req, resp, req.getServletContext());
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("Liste", context, resp.getWriter());
            resp.sendRedirect("/accueil");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String Mdp = req.getParameter("Mdp");

        if(test.getEmail()==email
               && MotDePasseUtils.validerMotDePasse(Mdp, test.getEmail())) {

            req.getSession().setAttribute("utilisateurConnecte", email);
        }

        resp.sendRedirect("connexion");




    }
}
