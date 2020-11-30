package model.services;

import java.util.List;

import model.dao.CorretorDao;
import model.dao.DaoFactory;
import model.entities.Corretor;

public class CorretorService {

	private CorretorDao dao = (CorretorDao) DaoFactory.createCorretorDao();

	public List<Corretor> findAll() {
		return dao.findAll();
	}

	public void saveOrUpdate(Corretor obj) {
		if (obj.getId() == null) {
			dao.insert(obj);
		} else {
			dao.update(obj);
		}
	}
	public void remove(Corretor obj) {
		dao.deleteById(obj.getId());
	}
	
}
