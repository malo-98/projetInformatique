package hei.projets7.mobiliti.controllers.servlets;

import hei.projets7.mobiliti.exception.DonneIllegalFormatException;
import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.entity.Destination;
import hei.projets7.mobiliti.entity.Eleve;
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

@WebServlet("/modification")
public class ModificationProfilServlet extends UtilsServlet {

    private EleveServices eleveServices = new EleveServices();
    private static final Logger LOGGER = LogManager.getLogger();
    private DestinationServices destinationServices=new DestinationServices();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String utilisateurConnecte=(String) req.getSession().getAttribute("utilisateurConnecte");
        WebContext context = new WebContext(req, resp, req.getServletContext());
        List<Destination> listOfDestination = DestinationServices.getInstance().destinationList();
        context.setVariable("destinationList",listOfDestination);

        Eleve eleve= null;
        try {
            eleve = eleveServices.getInstance().getEleve(utilisateurConnecte);
        } catch (EleveNotFoundException e) {
            LOGGER.warn("eleve not found");
        }
        context.setVariable("eleveConnecte",eleve);

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        templateEngine.process("prive/Modification", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String nom = req.getParameter("NouveauNom");
        String prenom= req.getParameter("NouveauPrenom");
        String domaine=req.getParameter("NouveauDomaine");
        String destination=req.getParameter("NouvelleDestination");
        String emailActuel =(String) req.getSession().getAttribute("utilisateurConnecte");
        try {
            Eleve eleveActuel= EleveServices.getInstance().getEleve(emailActuel);
            Integer id_eleve = eleveActuel.getId_eleve();
        } catch (EleveNotFoundException e) {
            LOGGER.warn("eleve not found");
        }

        if (nom!=null || nom != " "){
            try {
                EleveServices.getInstance().modifyNom(emailActuel,nom);
            } catch (EleveNotFoundException | DonneIllegalFormatException e) {
                LOGGER.warn("error while modifying eleve unknwon or unvalid data");
            }
        }
        if (prenom!=null || prenom != " "){
            try {
                EleveServices.getInstance().modifyPrenom(emailActuel,prenom);
            } catch (EleveNotFoundException | DonneIllegalFormatException e) {
                LOGGER.warn("error while modifying eleve unknwon or unvalid data");
            }
        }
        if (domaine!=null || domaine != " "){
            try {
                EleveServices.getInstance().modifyDomaine(emailActuel,domaine);
            } catch (EleveNotFoundException | DonneIllegalFormatException e) {
                LOGGER.warn("error while modifying eleve unknwon or unvalid data");
            }
        }




        resp.sendRedirect("profil");
    }

}
