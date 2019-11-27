package hei.projets7.mobiliti.servlets;

import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.pojos.Eleve;
import hei.projets7.mobiliti.pojos.Favoris;
import hei.projets7.mobiliti.services.EleveServices;
import hei.projets7.mobiliti.services.FavorisServices;
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

@WebServlet("/favoris")
public class FavorisServlet extends UtilsServlet {

    private static final Logger LOGGER = LogManager.getLogger();
    private EleveServices eleveServices = new EleveServices();
    private FavorisServices favorisServices=new FavorisServices();

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
        List<Favoris> favorisList=FavorisServices.getInstance().favorisListByID(eleve.getId_eleve());
        context.setVariable("listOfFavoris", favorisList);



        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
        templateEngine.process("prive/Favoris", context, resp.getWriter());
    }
}
