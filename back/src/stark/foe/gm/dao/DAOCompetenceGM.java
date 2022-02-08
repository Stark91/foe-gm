package stark.foe.gm.dao;

import java.util.List;

import stark.foe.gm.beans.CompetenceGM;

public interface DAOCompetenceGM {

	List<CompetenceGM> readAll() throws DAOException;
	
	CompetenceGM readOnlyCompetenceGM(Long idCompetenceGM) throws DAOException;
	
}
