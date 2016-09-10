package ua.nure.starodubets.SummaryTask4.controller.filters;

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

import ua.nure.starodubets.SummaryTask4.model.User;
import ua.nure.starodubets.SummaryTask4.service.AuthorizationService;
import ua.nure.starodubets.SummaryTask4.service.impl.AuthorizationServiceImpl;

/**
 * Servlet Filter implementation class TeacherRoleCheckFilter
 */
public class TeacherRoleCheckFilter implements Filter {

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		AuthorizationService authorization = new AuthorizationServiceImpl();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user != null && authorization.isTeacher(user)) {
			filterChain.doFilter(request, response);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/permitError");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
