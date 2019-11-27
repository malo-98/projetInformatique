package hei.projets7.mobiliti.servlets;

import hei.projets7.mobiliti.exception.DonneIllegalFormatException;
import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.pojos.Eleve;
import hei.projets7.mobiliti.services.EleveServices;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/modification")
public class ModificationProfilServlet extends UtilsServlet {

    private EleveServices eleveServices = new EleveServices();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String utilisateurConnecte=(String) req.getSession().getAttribute("utilisateurConnecte");
        WebContext context = new WebContext(req, resp, req.getServletContext());

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
        String emailActuel =(String) req.getSession().getAttribute("utilisateurConnecte");
        try {
            Eleve eleveActuel= EleveServices.getInstance().getEleve(emailActuel);
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

        resp.sendRedirect("profil");
    }

}
