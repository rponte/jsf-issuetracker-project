package br.com.triadworks.issuetracker.security;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.triadworks.issuetracker.controller.UsuarioWeb;

@WebFilter(urlPatterns="/*")
public class SecurityFilter implements Filter {

	private static final String LOGIN_PAGE = "/pages/login.xhtml";
	private static final String FACES_RESOURCES = "/javax.faces.resource";
	
	@Override
	public void init(FilterConfig config) throws ServletException {}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        if (isUrlPermitida(request)
        			|| isUsuarioLogado(request)) {
        		chain.doFilter(req, res);
        } else {
        		response.sendRedirect(request.getContextPath() + LOGIN_PAGE);
        }
	}
	
	private boolean isUsuarioLogado(HttpServletRequest request) {
		HttpSession session = request.getSession();
		UsuarioWeb usuarioWeb = (UsuarioWeb) session.getAttribute("usuarioWeb");
		return usuarioWeb != null
        			&& usuarioWeb.isLogado();
	}

	private boolean isUrlPermitida(HttpServletRequest request) {
		String path = request.getServletPath();
		return path.equals(LOGIN_PAGE)
				|| path.startsWith(FACES_RESOURCES);
	}

	@Override
	public void destroy() {}

}
