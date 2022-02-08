package stark.foe.gm.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stark.foe.gm.beans.Age;
import stark.foe.gm.dao.DAOAge;
import stark.foe.gm.dao.DAOFactory;

@SuppressWarnings("serial")
public class GestionAge extends HttpServlet {

	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_SESSION_AGES = "mapAge";
	public static final String VUE = "/WEB-INF/afficherAge.jsp";
	
	private DAOAge daoAge;
	
	public void init() throws ServletException {
		this.daoAge = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getDAOAge();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Age> listAges = daoAge.readAll();
		Map<Long,Age> mapAges = new HashMap<Long,Age>();
		for(Age age : listAges) {
			mapAges.put(age.getId(), age);
		}
		request.setAttribute(ATT_SESSION_AGES, mapAges);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
	
}
