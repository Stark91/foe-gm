package stark.foe.gm.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import stark.foe.gm.dao.DAOFactory;

public class InitDAOFactory implements ServletContextListener {

	private static final String ATT_DAO_FACTORY = "daofactory";
	
	private DAOFactory daoFactory;
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext servletContext = event.getServletContext();
		this.daoFactory = DAOFactory.getInstance();
		servletContext.setAttribute(ATT_DAO_FACTORY, this.daoFactory);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
	}
}
