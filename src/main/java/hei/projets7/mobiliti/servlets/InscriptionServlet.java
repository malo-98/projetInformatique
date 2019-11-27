package hei.projets7.mobiliti.servlets;

import hei.projets7.mobiliti.exception.DonneIllegalFormatException;
import hei.projets7.mobiliti.exception.EleveAlreadyExistException;
import hei.projets7.mobiliti.pojos.Eleve;
import hei.projets7.mobiliti.services.EleveServices;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/inscription")
public class InscriptionServlet extends UtilsServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        templateEngine.process("Inscription", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // GET PARAMETERS
        String nom = req.getParameter("nom");
        String prenom = req.getParameter("prenom");
        String email = req.getParameter("email");
        String password=req.getParameter("password");
        String domaine=req.getParameter("domaine");


        // CREATE Eleve
        try {
            Eleve newEleve = new Eleve(null,nom,prenom,email,password,domaine);
            Eleve createdEleve = EleveServices.getInstance().addEleve(newEleve);

        } catch ( EleveAlreadyExistException | DonneIllegalFormatException e) {
            req.getSession().setAttribute("errorMessage", e.getMessage());
        }

        resp.sendRedirect("connexion");
    }
}
