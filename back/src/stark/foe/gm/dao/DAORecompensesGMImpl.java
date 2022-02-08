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
import stark.foe.gm.beans.RecompensesGM;

public class DAORecompensesGMImpl implements DAORecompensesGM {

	private static final String SQL_SELECT = "SELECT * FROM recompensesgm JOIN age ON recompensesgm.idAge = age.idAge";
	private static final String SQL_ORDER_BY = " ORDER BY recompensesgm.niveau;";
	
	private DAOFactory daoFactory;
	
	DAORecompensesGMImpl (DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public List<RecompensesGM> readAll() throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<RecompensesGM> listGM = new ArrayList<RecompensesGM>();
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, SQL_SELECT + SQL_ORDER_BY, false);
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
	
	public List<RecompensesGM> readRushGM(Long idAge, int niveauDepart, int niveauFin) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<RecompensesGM> listRecompensesGM = new ArrayList<RecompensesGM>();
		String request = SQL_SELECT + " WHERE recompensesgm.idAge = " + idAge + " AND recompensesgm.niveau BETWEEN " + (niveauDepart + 1) + " AND " + niveauFin + SQL_ORDER_BY;
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, request, false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				listRecompensesGM.add(map(resultSet));
			}
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
		
		return listRecompensesGM;
	}
	
	public RecompensesGM readRecompensesGM(Long idAge, int niveau) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		RecompensesGM recompensesGM = new RecompensesGM();
		String request = SQL_SELECT + " WHERE recompensesgm.idAge = " + idAge + " AND recompensesgm.niveau = " + niveau + SQL_ORDER_BY;
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, request, false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				recompensesGM = map(resultSet);
			}
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
		
		return recompensesGM;
	}
	
	public int readNiveauMax(Long idAge) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int niveauMax = 0;
		String request = "SELECT MAX(recompensesgm.niveau) FROM recompensesgm JOIN age ON recompensesgm.idAge = age.idAge WHERE recompensesgm.idAge = " + idAge + ";";
		
		try {
			connection = daoFactory.getConnection();
			preparedStatement = initPreparedStatement(connection, request, false);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				niveauMax = resultSet.getInt("MAX(recompensesgm.niveau)");
			}
		} catch (Exception e) {
			throw new DAOException(e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
		
		return niveauMax;
	}
	
	private static RecompensesGM map(ResultSet resultSet) throws SQLException {
		Age age = new Age();
		RecompensesGM recompensesGM = new RecompensesGM();
		age.setId(resultSet.getLong("idAge"));
		age.setName(resultSet.getString("nomAge"));
		recompensesGM.setId(resultSet.getLong("idRecompensesGM"));
		recompensesGM.setAge(age);
		recompensesGM.setNiveau(resultSet.getInt("niveau"));
		recompensesGM.setTotal(resultSet.getInt("total"));
		recompensesGM.setP1(resultSet.getInt("p1"));
		recompensesGM.setP2(resultSet.getInt("p2"));
		recompensesGM.setP3(resultSet.getInt("p3"));
		recompensesGM.setP4(resultSet.getInt("p4"));
		recompensesGM.setP5(resultSet.getInt("p5"));
		return recompensesGM;
	}
}
