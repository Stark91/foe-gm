package stark.foe.gm.dao;

import static stark.foe.gm.dao.DAOUtilities.close;
import static stark.foe.gm.dao.DAOUtilities.initPreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import stark.foe.gm.beans.CompetenceGM;

public class DAOCompetenceGMImpl implements DAOCompetenceGM {
	
	private static final String SQL_SELECT = "SELECT * FROM competencegm;";
	
	private DAOFactory daoFactory;
	
	DAOCompetenceGMImpl (DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	public List<CompetenceGM> readAll() throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<CompetenceGM> listCompetenceGM = new ArrayList<CompetenceGM>();
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, SQL_SELECT, false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				listCompetenceGM.add(map(resultSet));
			}
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
		
		return listCompetenceGM;
	}
	
	public CompetenceGM readOnlyCompetenceGM(Long idCompetenceGM) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		CompetenceGM competenceGM = new CompetenceGM();
		String request = SQL_SELECT.replace(";", "") + " WHERE competencegm.idCompetenceGM = " + idCompetenceGM + ";";
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, request, false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				competenceGM = (map(resultSet));
			}
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
		
		return competenceGM;
	}
	
	private static CompetenceGM map(ResultSet resultSet) throws SQLException {
		CompetenceGM competenceGM = new CompetenceGM();
		competenceGM.setId(resultSet.getLong("idCompetenceGM"));
		competenceGM.setName(resultSet.getString("nomCompetenceGM"));
		competenceGM.setDescription(resultSet.getString("descriptionCompetenceGM"));
		competenceGM.setImage(resultSet.getString("imageCompetenceGM"));
		return competenceGM;
	}

}
