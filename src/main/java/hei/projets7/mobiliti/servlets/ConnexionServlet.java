package hei.projets7.mobiliti.servlets;

import hei.projets7.mobiliti.exception.EleveNotFoundException;
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
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



@WebServlet("/connexion")
public class ConnexionServlet extends UtilsServlet {

    private static final Logger LOGGER = LogManager.getLogger();
    private EleveServices eleveServices = new EleveServices();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String utilisateurConnecte = (String) req.getSession().getAttribute("utilisateurConnecte");

        //System.out.println("J'ai récupéré " + utilisateurConnecte + " dans la session");
        LOGGER.info("J'ai récupéré " + utilisateurConnecte + " dans la session");

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
        //System.out.println("J'ai récupéré " + email + " en email de la requête.");
        LOGGER.info("J'ai récupéré " + email + " en email de la requête.");

        String mdp = req.getParameter("mdp");
        //System.out.println("J'ai récupéré " + mdp + " en mot de passe de la requête.");
        LOGGER.info("J'ai récupéré " + mdp + " en mot de passe de la requête.");

        // CREATE ELEVE

        Eleve newEleve = null;
        try {
            newEleve = eleveServices.getEleve(email);
        } catch (EleveNotFoundException e) {
            e.printStackTrace();
        }

        // CRYPTER MDP
        String mdpArgon = MotDePasseUtils.genererMotDePasse(mdp);


        //COMPARE WITH BDD
        try {
            if(newEleve != null && newEleve.getEmail().equals(email) && eleveServices.checkPassword(email, mdp)){

                if(MotDePasseUtils.validerMotDePasse(mdp, mdpArgon)){
                    //System.out.println("J'ai " +email+ " et "+ mdp + " en paramètres");
                    LOGGER.info("J'ai " +email+ " et "+ mdp + " en paramètres");
                    req.getSession().setAttribute("utilisateurConnecte", email);

                }else{
                    //System.out.println("Probleme mot de passe Argon");
                    LOGGER.info("Probleme mot de mot de passe Argon");
                }

            }else{
                //System.out.println("Identifiants inconnus");
                LOGGER.error("Identifiants inconnus");
            }
        } catch (EleveNotFoundException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("accueil");




    }
}
