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
import java.util.HashMap;
import java.util.Map;

@WebServlet("/connexion")
public class ConnexionServlet extends UtilsServlet {

    private Eleve eleve = new Eleve(5,"TEST","test", "test@gmail.com","1234","ITI");
    private Map<String, String> utilisateurs;

    @Override
    public void init() throws ServletException {
        utilisateurs = new HashMap<>();
        utilisateurs.put(eleve.getEmail(), MotDePasseUtils.genererMotDePasse(eleve.getPassword()));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String utilisateurConnecte = (String) req.getSession().getAttribute("utilisateurConnecte");

        System.out.println("J'ai récupéré " + utilisateurConnecte + " dans la session");

        if(utilisateurConnecte==null || "".equals(utilisateurConnecte)) {
            WebContext context = new WebContext(req, resp, req.getServletContext());
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("Connexion", context, resp.getWriter());
        }
        else{
            WebContext context = new WebContext(req, resp, req.getServletContext());
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("Liste", context, resp.getWriter());

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String Mdp = req.getParameter("Mdp");
        System.out.println("J'ai récupéré " + email + " en paramètre de la requête.");

       if(utilisateurs.containsKey(email)
                && MotDePasseUtils.validerMotDePasse(Mdp, utilisateurs.get(email))) {

            req.getSession().setAttribute("utilisateurConnecte", email);
        }

        resp.sendRedirect("connexion");




    }
}
