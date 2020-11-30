package model.dao;

import java.util.List;

import model.entities.AtuationZone;
import model.entities.Corretor;

public interface CorretorDao {

	void insert(Corretor obj);
	void update(Corretor obj);
	void deleteById(Integer id);
	Corretor findById(Integer id);
	List<Corretor> findAll();
	List<Corretor> findByDepartment(AtuationZone atuationZone);
	
}
