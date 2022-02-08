package stark.foe.gm.dao;

import java.util.List;

import stark.foe.gm.beans.RecompensesGM;

public interface DAORecompensesGM {

	List<RecompensesGM> readAll() throws DAOException;
	
	List<RecompensesGM> readRushGM(Long idAge, int niveauDepart, int niveauFin) throws DAOException;
	
	RecompensesGM readRecompensesGM(Long idAge, int niveau) throws DAOException;
	
	int readNiveauMax(Long idAge) throws DAOException;
	
}
