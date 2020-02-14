package app.service.session;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl implements SessionService{

	private HttpSession session;
	
	
	@Autowired
	public SessionServiceImpl(HttpSession session) {
		this.session = session;
	}


	@Override
	public Object getSessionAttribute(String attribute) {
		return this.session.getAttribute(attribute);
	}

	

}
