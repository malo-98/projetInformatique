package hei.projets7.mobiliti.servlets;

import hei.projets7.mobiliti.pojos.Destination;
import hei.projets7.mobiliti.pojos.Eleve;
import hei.projets7.mobiliti.services.DestinationServices;
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
import java.util.List;
import java.util.Map;

@WebServlet("/connexion")
public class ConnexionServlet extends UtilsServlet {

    /*
    private Eleve eleve = new Eleve(5,"TEST","test", "test@gmail.com","1234","ITI");
    private Map<String, String> utilisateurs;

    @Override
    public void init() throws ServletException {
        utilisateurs = new HashMap<>();
        utilisateurs.put(eleve.getEmail(),eleve.getPassword());

    }

     */

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String utilisateurConnecte = (String) req.getSession().getAttribute("utilisateurConnecte");

        System.out.println("J'ai récupéré " + utilisateurConnecte + " dans la session");

        WebContext context = new WebContext(req, resp, req.getServletContext());
        List<Eleve> listOfEleve = EleveServices.getInstance().listEleve();
        context.setVariable("ListEleve",listOfEleve);


        if(utilisateurConnecte==null || "".equals(utilisateurConnecte)) {

            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("Connexion", context, resp.getWriter());
        }
        else{
            TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
            templateEngine.process("Liste", context, resp.getWriter());

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //GET PARAMETERS
        String email = req.getParameter("email");
        System.out.println("J'ai récupéré " + email + " en email de la requête.");

        String mdp = req.getParameter("mdp");
        System.out.println("J'ai récupéré " + mdp + " en mot de passe de la requête.");

        // CREATE ELEVE
        Eleve newEleve =new Eleve(null, null, null, email, mdp, null);

        //COMPARE WITH BDD
        if(EleveServices.getInstance().listEleve().contains(newEleve.getEmail())
                    && EleveServices.getInstance().listEleve().contains(newEleve.getPassword())){
               // && MotDePasseUtils.validerMotDePasse(Mdp, utilisateurs.get(email)))
            //&& mdp==eleve.getPassword()){


            System.out.println("J'ai " +email+ " et "+ mdp + " en paramètres de la requete.");
            req.getSession().setAttribute("utilisateurConnecte", email);
        }

        resp.sendRedirect("connexion");




    }
}
