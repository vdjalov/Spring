package app.web.filter;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import app.session.MySession;


@WebFilter(urlPatterns = {"/heroes",
						  "/users", 
						  "",
						  ""})
public class UserFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		Object username = MySession.mySession.get(0).getAttribute("username");
		
			if(username == null) {
				resp.sendRedirect("/");
			} else {
				chain.doFilter(request, response);	
			}
		
	
    }
}
