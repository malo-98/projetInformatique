package hei.projets7.mobiliti.servlets;

import hei.projets7.mobiliti.daos.impl.ChoixDaoImpl;
import hei.projets7.mobiliti.exception.ChoixAlreadyExistException;
import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.pojos.Choix;
import hei.projets7.mobiliti.pojos.Eleve;
import hei.projets7.mobiliti.services.ChoixServices;
import hei.projets7.mobiliti.services.EleveServices;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/profil")
public class ProfilServlet extends UtilsServlet {

    private EleveServices eleveServices=new EleveServices();

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
        templateEngine.process("prive/Profil", context, resp.getWriter());
    }
/*
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email=(String) req.getSession().getAttribute("utilisateurConnecte");
        //CREATE ELEVE
        Eleve newEleve = null;
        try {
            newEleve = eleveServices.getInstance().getEleve(email);
        } catch (EleveNotFoundException e) {
            e.printStackTrace();
        }

        //GET PARAMETERS
        Integer id_eleve = newEleve.getId_eleve();
        Integer id_destination = Integer.parseInt(req.getParameter("id_destination"));


        //SET CHOIX
        Choix newChoix= ChoixServices.getInstance().getChoix(id_eleve);
        try {
            Choix createdChoix=ChoixServices.getInstance().addChoix(newChoix);
        } catch (ChoixAlreadyExistException | SQLException e) {
            e.printStackTrace();
        }

        //REDIRECT
    }

 */
}
