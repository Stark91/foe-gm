package stark.foe.gm.dao;

import java.util.List;

import stark.foe.gm.beans.GM;

public interface DAOGM {

	List<GM> readAll() throws DAOException;

	List<GM> readOnlyAge(String idAge) throws DAOException;

	GM readOnlyGM(Long idGM) throws DAOException;
	
}
