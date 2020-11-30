package model.dao;

import com.mysql.jdbc.Connection;

import db.DB;
import model.dao.impl.CorretorDaoJDBC;

public class DaoFactory {

	public static CorretorDaoJDBC createCorretorDao() {
		return new CorretorDaoJDBC((Connection) DB.getConnection());
	}
	
}
