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

public class DAOAgeImpl implements DAOAge {
	
	private static final String SQL_SELECT = "SELECT * FROM age;";
	
	private DAOFactory daoFactory;
	
	DAOAgeImpl (DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Age> readAll() throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Age> ages = new ArrayList<Age>();
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, SQL_SELECT, false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ages.add(map(resultSet));
			}
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
		
		return ages;
	}
	
	public Age readOnlyAge(Long idAge) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Age age = new Age();
		String request = SQL_SELECT.replace(";", "") + " WHERE age.idAge = " + idAge + ";";
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, request, false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				age = map(resultSet);
			}
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
		
		return age;
	}
	
	private static Age map(ResultSet resultSet) throws SQLException {
		Age age = new Age();
		age.setId(resultSet.getLong("idAge"));
		age.setName(resultSet.getString("nomAge"));
		return age;
	}

}
