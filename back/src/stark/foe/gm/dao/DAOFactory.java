package stark.foe.gm.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory {

	private static final String PROPERTIES_FILE = "/stark/foe/gm/dao/dao.properties";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_USER = "user";
	private static final String PROPERTY_PASSWORD = "password";
	
	private String url;
	private String user;
	private String password;
	
	DAOFactory(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	public static DAOFactory getInstance() throws DAOConfigurationException {
		Properties properties = new Properties();
		String url;
		String driver;
		String user;
		String password;
		
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);
		
		if(propertiesFile == null) {
			throw new DAOConfigurationException("Le fichier properties " + PROPERTIES_FILE + " est introuvable");
		}
		
		try {
			properties.load(propertiesFile);
			url = properties.getProperty(PROPERTY_URL);
			driver = properties.getProperty(PROPERTY_DRIVER);
			user = properties.getProperty(PROPERTY_USER);
			password = properties.getProperty(PROPERTY_PASSWORD);
		} catch (IOException e) {
			throw new DAOConfigurationException("Impossible de charger le fichier properties " + PROPERTIES_FILE, e);
		}
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new DAOConfigurationException("Le driver est introuvable dans le classpath.", e);
		}
		
		DAOFactory instance = new DAOFactory(url, user, password);
		return instance;
	}
	
	Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, user, password);
	}
	
	public DAOAge getDAOAge() {
		return new DAOAgeImpl(this);
	}
	
	public DAOGM getDAOGM() {
		return new DAOGMImpl(this);
	}
	
	public DAORecompensesGM getDAORecompensesGM() {
		return new DAORecompensesGMImpl(this);
	}
	
	public DAOCompetenceGM getDaoCompetenceGM() {
		return new DAOCompetenceGMImpl(this);
	}
	
}
