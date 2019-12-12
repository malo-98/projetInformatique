package hei.projets7.mobiliti.controllers.servlets;

import hei.projets7.mobiliti.exception.ChoixNotFoundException;
import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.entity.Choix;
import hei.projets7.mobiliti.entity.Destination;
import hei.projets7.mobiliti.entity.Eleve;
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

@WebServlet("/profil")
public class ProfilServlet extends UtilsServlet {

    private EleveServices eleveServices=new EleveServices();
    private DestinationServices destinationServices=new DestinationServices();
    Integer destinationId=1;

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
        try {
            if (ChoixServices.getInstance().getChoix(eleve.getId_eleve())!=null){
                Choix choix_eleve = ChoixServices.getInstance().getChoix(eleve.getId_eleve());
                destinationId = choix_eleve.getId_destination();
            }
        } catch ( ChoixNotFoundException e) {
            e.printStackTrace();
        }
        Destination destination= destinationServices.read(destinationId);
        context.setVariable("choix",destination);


        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("prive/Profil", context, resp.getWriter());
    }

}
