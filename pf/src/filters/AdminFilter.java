package filters;

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

import entidad.Persona;

@WebFilter(filterName = "AdminFilter")
public class AdminFilter implements Filter {

	public AdminFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		boolean isAdmin = false;

		if (request.getSession(false).getAttribute("usuario") != null) {
			if (((Persona) request.getSession(false).getAttribute("usuario")).getTipoPersona().getIdTipoPersona() == 1) {
				isAdmin = true;
			}

			if (isAdmin) {
				chain.doFilter(req, res);

			} else {
				response.sendRedirect(request.getContextPath() + "/user");
			}
		} else {

			response.sendRedirect(request.getContextPath() + "/login");
		}

	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}