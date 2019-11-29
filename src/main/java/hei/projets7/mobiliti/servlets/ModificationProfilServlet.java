package hei.projets7.mobiliti.servlets;

import hei.projets7.mobiliti.exception.DestinationNotFoundException;
import hei.projets7.mobiliti.exception.DonneIllegalFormatException;
import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.pojos.Choix;
import hei.projets7.mobiliti.pojos.Destination;
import hei.projets7.mobiliti.pojos.Eleve;
import hei.projets7.mobiliti.services.ChoixServices;
import hei.projets7.mobiliti.services.DestinationServices;
import hei.projets7.mobiliti.services.EleveServices;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/modification")
public class ModificationProfilServlet extends UtilsServlet {

    private EleveServices eleveServices = new EleveServices();
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
            e.printStackTrace();
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
            e.printStackTrace();
        }

        if (nom!=null || nom != " "){
            try {
                EleveServices.getInstance().modifyNom(emailActuel,nom);
            } catch (EleveNotFoundException | DonneIllegalFormatException e) {
                e.printStackTrace();
            }
        }
        if (prenom!=null || prenom != " "){
            try {
                EleveServices.getInstance().modifyPrenom(emailActuel,prenom);
            } catch (EleveNotFoundException | DonneIllegalFormatException e) {
                e.printStackTrace();
            }
        }
        if (domaine!=null || domaine != " "){
            try {
                EleveServices.getInstance().modifyDomaine(emailActuel,domaine);
            } catch (EleveNotFoundException | DonneIllegalFormatException e) {
                e.printStackTrace();
            }
        }

        Eleve eleveActuel= null;
        try {
            eleveActuel = EleveServices.getInstance().getEleve(emailActuel);
        } catch (EleveNotFoundException e) {
            e.printStackTrace();
        }
        Integer id_eleve = eleveActuel.getId_eleve();
        ChoixServices.getInstance().modifyChoix(id_eleve);
        Integer id_destination=destinationServices.getIdbyName(destination);
        Choix newChoix = new Choix(null,id_eleve,id_destination);
        try {
            ChoixServices.getInstance().addChoix(newChoix);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("destination modifiée");



        resp.sendRedirect("profil");
    }

}
