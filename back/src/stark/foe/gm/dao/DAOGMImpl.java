package stark.foe.gm.dao;

import static stark.foe.gm.dao.DAOUtilities.close;
import static stark.foe.gm.dao.DAOUtilities.initPreparedStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import stark.foe.gm.beans.Age;
import stark.foe.gm.beans.CompetenceGM;
import stark.foe.gm.beans.GM;

public class DAOGMImpl implements DAOGM {

	private static final String SQL_SELECT = "SELECT "
			+ "idGM, "
			+ "nomGM, "
			+ "imageGM, "
			+ "tailleGM, "
			+ "gm.idAge, "
			+ "age.nomAge, "
			+ "idCompetenceGM1, "
			+ "competencegm1.nomCompetenceGM AS nomCompetenceGM1, "
			+ "competencegm1.descriptionCompetenceGM AS descriptionCompetenceGM1, "
			+ "competencegm1.imageCompetenceGM AS imageCompetenceGM1, "
			+ "idCompetenceGM2, "
			+ "competencegm2.nomCompetenceGM AS nomCompetenceGM2, "
			+ "competencegm2.descriptionCompetenceGM AS descriptionCompetenceGM2, "
			+ "competencegm2.imageCompetenceGM AS imageCompetenceGM2 "
			+ "FROM gm "
			+ "JOIN age ON gm.idAge = age.idAge "
			+ "JOIN competencegm AS competencegm1 ON gm.idCompetenceGM1 = competencegm1.idCompetenceGM "
			+ "JOIN competencegm AS competencegm2 ON gm.idCompetenceGM2 = competencegm2.idCompetenceGM;";
	
	private DAOFactory daoFactory;
	
	DAOGMImpl (DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public List<GM> readAll() throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<GM> listGM = new ArrayList<GM>();
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, SQL_SELECT, false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				listGM.add(map(resultSet));
			}
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
		
		return listGM;
	}
	
	public List<GM> readOnlyAge(String idAge) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<GM> listGM = new ArrayList<GM>();
		String request = SQL_SELECT.replace(";", "") + " WHERE gm.idAge = " + idAge + ";";
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, request, false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				listGM.add(map(resultSet));
			}
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
		
		return listGM;
	}
	
	public GM readOnlyGM(Long idGM) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		GM gm = new GM();
		String request = SQL_SELECT.replace(";", "") + " WHERE gm.idGM = " + idGM + ";";
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, request, false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				gm=map(resultSet);
			}
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
		
		return gm;
	}
	
	private static GM map(ResultSet resultSet) throws SQLException {
		Age age = new Age();
		age.setId(resultSet.getLong("idAge"));
		age.setName(resultSet.getString("nomAge"));
		
		CompetenceGM competenceGM1 = new CompetenceGM();
		competenceGM1.setId(resultSet.getLong("idCompetenceGM1"));
		competenceGM1.setName(resultSet.getString("nomCompetenceGM1"));
		competenceGM1.setDescription(resultSet.getString("descriptionCompetenceGM1"));
		competenceGM1.setImage(resultSet.getString("imageCompetenceGM1"));
		
		CompetenceGM competenceGM2 = new CompetenceGM();
		competenceGM2.setId(resultSet.getLong("idCompetenceGM2"));
		competenceGM2.setName(resultSet.getString("nomCompetenceGM2"));
		competenceGM2.setDescription(resultSet.getString("descriptionCompetenceGM2"));
		competenceGM2.setImage(resultSet.getString("imageCompetenceGM2"));
		
		GM gm = new GM();
		gm.setId(resultSet.getLong("idGM"));
		gm.setName(resultSet.getString("nomGM"));
		gm.setAge(age);
		gm.setImage(resultSet.getString("imageGM"));
		
		String taille = resultSet.getString("tailleGM");
		Short tailleX = Short.parseShort(taille.split("x")[0]);
		Short tailleY = Short.parseShort(taille.split("x")[1]);
		gm.setX(tailleX);
		gm.setY(tailleY);
		gm.setCompetenceGM1(competenceGM1);
		gm.setCompetenceGM2(competenceGM2);
		
		return gm;
	}

}
