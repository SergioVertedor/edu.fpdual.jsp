package edu.fpdual.jsp.web.filter;


import edu.fpdual.jsp.web.dto.Usuario;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName="FiltroSesion", urlPatterns={"/comun/*"}, dispatcherTypes= {DispatcherType.REQUEST,DispatcherType.FORWARD})
public class SessionFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig)
		throws
		ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
		throws
		IOException,
		ServletException {

		HttpServletRequest req = (HttpServletRequest)servletRequest;

		Usuario usuario = (Usuario)req.getSession().getAttribute("usuarioSesion");

		if(usuario == null){
			((HttpServletResponse)servletResponse).sendRedirect("/jsp/login/loginJSP.jsp");
		} else {
			System.out.println("Antes de pasar filtro");
			filterChain.doFilter(servletRequest, servletResponse);
			System.out.println("Despues de pasar filtro");
		}
	}
}
