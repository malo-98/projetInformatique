package hei.projets7.mobiliti.servlets;

import hei.projets7.mobiliti.pojos.Eleve;
import hei.projets7.mobiliti.services.EleveServices;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/connexion")
public class ConnexionServlet extends UtilsServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());

        templateEngine.process("Connexion", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // GET PARAMETERS
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        //CREATE ELEVE
        Eleve neweleve = new Eleve(null, null,null,email,password,null);

        //CONNECT
        if (email== neweleve.getEmail() && password == neweleve.getPassword()){
            resp.sendRedirect("Map");

        }
        else{
            resp.sendError(1,"Identifiant ou mot de passe incorrect");
        }


    }
}
