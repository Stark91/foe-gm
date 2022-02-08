package stark.foe.gm.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import stark.foe.gm.beans.GM;
import stark.foe.gm.dao.DAOGM;
import stark.foe.gm.dao.DAOFactory;

@SuppressWarnings("serial")
public class GestionGM extends HttpServlet {

	public static final String CONF_DAO_FACTORY = "daofactory";
	public static final String ATT_SESSION_GM = "mapGM";
	public static final String VUE = "/WEB-INF/afficherGM.jsp";
	
	private DAOGM daoGM;
	
	public void init() throws ServletException {
		this.daoGM = ((DAOFactory) getServletContext().getAttribute(CONF_DAO_FACTORY)).getDAOGM();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idAge = request.getParameter("idAge");
		List<GM> listGM;
		if(idAge == null) {
			listGM = daoGM.readAll();
		} else {
			listGM = daoGM.readOnlyAge(idAge);
		}
		Map<Long,GM> mapGM = new HashMap<Long,GM>();
		for(GM gm : listGM) {
			mapGM.put(gm.getId(), gm);
		}
		request.setAttribute(ATT_SESSION_GM, mapGM);
		this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
	}
	
}
