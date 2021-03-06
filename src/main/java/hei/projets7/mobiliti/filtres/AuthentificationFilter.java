package hei.projets7.mobiliti.filtres;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.LogRecord;

@WebFilter("/prive/*")
public class AuthentificationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String email = (String) httpRequest.getSession().getAttribute("utilisateurConnecte");

        if (email == null || "".equals(email)) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
           if (httpRequest.getServletPath().contains("Favoris") || httpRequest.getServletPath().contains("Profil")) {

                System.out.println("Il faut être connecté pour accéder à cette page !");
                //httpResponse.sendRedirect("connexion");
           }
           else{
               httpResponse.sendRedirect(httpRequest.getContextPath()+"/connexion");
           }

        }
        else
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }



}
