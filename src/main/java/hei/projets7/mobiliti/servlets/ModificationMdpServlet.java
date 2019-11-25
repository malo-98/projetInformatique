package hei.projets7.mobiliti.servlets;

import hei.projets7.mobiliti.exception.EleveNotFoundException;
import hei.projets7.mobiliti.exception.PasswordIllegalFormatException;
import hei.projets7.mobiliti.services.EleveServices;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ModificationMdp")
public class ModificationMdpServlet extends UtilsServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    WebContext context = new WebContext(req, resp, req.getServletContext());
    TemplateEngine templateEngine = createTemplateEngine(req.getServletContext());
    templateEngine.process("prive/ModificationMdp", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String password=req.getParameter("NouveauMdp");
        String email =(String) req.getSession().getAttribute("utilisateurConnecte");

        try {
            EleveServices.getInstance().modifyPassword(email,password);
        } catch (EleveNotFoundException e) {
            e.printStackTrace();
        } catch (PasswordIllegalFormatException e) {
            e.printStackTrace();
        }

        resp.sendRedirect("profil");
    }


}
