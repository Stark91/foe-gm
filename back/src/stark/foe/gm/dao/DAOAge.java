package stark.foe.gm.dao;

import java.util.List;

import stark.foe.gm.beans.Age;

public interface DAOAge {

	List<Age> readAll() throws DAOException;

	Age readOnlyAge(Long idAge) throws DAOException;
	
}
