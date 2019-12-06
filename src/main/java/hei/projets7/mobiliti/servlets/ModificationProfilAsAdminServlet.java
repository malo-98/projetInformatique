package hei.projets7.mobiliti.servlets;

import hei.projets7.mobiliti.exception.DonneIllegalFormatException;
import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.pojos.Destination;
import hei.projets7.mobiliti.pojos.Eleve;
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

@WebServlet("/modificationProfilAsAdmin")
public class ModificationProfilAsAdminServlet extends UtilsServlet {

    private String emailEleve=null;
    private EleveServices eleveServices = new EleveServices();
    private DestinationServices destinationServices=new DestinationServices();
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String eleveEmail= (String) req.getParameter("email");
        emailEleve=eleveEmail;

        Eleve eleve= null;
        try {
            eleve = eleveServices.getInstance().getEleve(emailEleve);
        } catch (EleveNotFoundException e) {
           LOGGER.warn("eleve not found");
        }
        context.setVariable("eleveConnecte",eleve);

        templateEngine.process("ModificationProfilAsAdmin", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String nom = req.getParameter("NouveauNom");
        String prenom= req.getParameter("NouveauPrenom");
        String domaine=req.getParameter("NouveauDomaine");
        String destination=req.getParameter("NouvelleDestination");

        try {
            Eleve eleveActuel= EleveServices.getInstance().getEleve(emailEleve);
            Integer id_eleve = eleveActuel.getId_eleve();
        } catch (EleveNotFoundException e) {
            e.printStackTrace();
        }

        if (nom!=null || nom != " "){
            try {
                EleveServices.getInstance().modifyNom(emailEleve,nom);
            } catch (EleveNotFoundException | DonneIllegalFormatException e) {
                e.printStackTrace();
            }
        }
        if (prenom!=null || prenom != " "){
            try {
                EleveServices.getInstance().modifyPrenom(emailEleve,prenom);
            } catch (EleveNotFoundException | DonneIllegalFormatException e) {
                e.printStackTrace();
            }
        }
        if (domaine!=null || domaine != " "){
            try {
                EleveServices.getInstance().modifyDomaine(emailEleve,domaine);
            } catch (EleveNotFoundException | DonneIllegalFormatException e) {
                e.printStackTrace();
            }
        }

        resp.sendRedirect("listEleve");
    }
}
