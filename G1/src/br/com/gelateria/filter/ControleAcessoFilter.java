package br.com.gelateria.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ControleAcessoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest httpReq = (HttpServletRequest) request;
            HttpServletResponse httpRes = (HttpServletResponse) response;
            HttpSession session = httpReq.getSession(true);
            String url = httpReq.getRequestURL().toString();
            if (session.getAttribute("usuarioLogado") == null && precisaAutenticar(url)) {
                httpRes.sendRedirect(httpReq.getContextPath() + "/paginas/login.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
    }

    private boolean precisaAutenticar(String url) {
        return !url.contains("login.xhtml") && !url.contains("javax.faces.resource");
    }
}
