package hei.projets7.mobiliti.servlets;

import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.pojos.Choix;
import hei.projets7.mobiliti.pojos.Eleve;
import hei.projets7.mobiliti.services.ChoixServices;
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



@WebServlet("/listEleve")
public class ListEleveServlet extends UtilsServlet {

    private static final Logger LOGGER = LogManager.getLogger();
    private EleveServices eleveServices=new EleveServices();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        List<Eleve> eleves= EleveServices.getInstance().listEleve();
        context.setVariable("eleveList",eleves);

        List<Choix> choixs= ChoixServices.getInstance().listChoix();
        context.setVariable("choixList",choixs);

        String utilisateurConnecte=(String) req.getSession().getAttribute("utilisateurConnecte");
        if(utilisateurConnecte != null){
            LOGGER.info("J'ai récupéré " + utilisateurConnecte + " dans la session");
            try {
                Eleve eleve = eleveServices.getInstance().getEleve(utilisateurConnecte);
                context.setVariable("eleveConnecte",eleve);
            } catch (EleveNotFoundException e) {
                LOGGER.error(e.getMessage());
            }
        }

        templateEngine.process("ListEleve", context, resp.getWriter());
    }
}
