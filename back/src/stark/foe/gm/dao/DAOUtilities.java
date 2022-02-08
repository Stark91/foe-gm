package stark.foe.gm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class DAOUtilities {
	
	private DAOUtilities() {
		
	}

	public static void close(ResultSet resultSet) {
		if(resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				System.out.println("Echec de la fermeture du ResultSet : " + e.getMessage());
			}
		}
	}
	
	public static void close(Statement statement) {
		if(statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				System.out.println("Echec de la fermeture du Statement : " + e.getMessage());
			}
		}
	}
	
	public static void close(Connection connection) {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				System.out.println("Echec de la fermeture du Connection : " + e.getMessage());
			}
		}
	}
	
	public static void close(Statement statement, Connection connection) {
		close(statement);
		close(connection);
	}
	
	public static void close(ResultSet resultSet, Statement statement, Connection connection) {
		close(resultSet);
		close(statement);
		close(connection);
	}
	
	public static PreparedStatement initPreparedStatement(Connection connection, String sql, boolean returnGenaratedKeys, Object... objects) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(sql, returnGenaratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
		for(int i=0; i<objects.length; i++) {
			preparedStatement.setObject(i+1, objects[i]);
		}
		return preparedStatement;
	}
}
