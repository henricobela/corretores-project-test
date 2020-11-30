package model.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.Connection;

import db.DB;
import db.DbException;
import model.entities.AtuationZone;
import model.entities.Corretor;

public class CorretorDaoJDBC {
private Connection conn;
	
	public CorretorDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	

	public void insert(Corretor obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"INSERT INTO corretor "
					+ "(Name, Folga, AtuationZoneId) "
					+ "VALUES "
					+ "(?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getFolga());
			st.setInt(3, obj.getAtuationZone().getId());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	
	public void update(Corretor obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE corretor "
					+ "SET Name = ?, Folga = ?, AtuationZoneId = ? "
					+ "WHERE Id = ?");
			
			st.setString(1, obj.getName());
			st.setString(2, obj.getFolga());
			st.setInt(4, obj.getAtuationZone().getId());
			st.setInt(5, obj.getId());
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}


	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM corretor WHERE Id = ?");
			
			st.setInt(1, id);
			
			st.executeUpdate();
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
	}

	
	public Corretor findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT corretor.*,atuationZone.Name as zoneName "
					+ "FROM corretor INNER JOIN atuationZone "
					+ "ON corretor.AtuationZoneId = atuationZone.Id "
					+ "WHERE corretor.Id = ?");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				AtuationZone zone = instantiateAtuationZone(rs);
				Corretor obj = instantiateCorretor(rs, zone);
				return obj;
			}
			return null;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Corretor instantiateCorretor(ResultSet rs, AtuationZone zone) throws SQLException {
		Corretor obj = new Corretor();
		obj.setId(rs.getInt("Id"));
		obj.setName(rs.getString("Name"));
		obj.setFolga(rs.getString("Folga"));
		obj.setAtuationZone(zone);
		return obj;
	}

	private AtuationZone instantiateAtuationZone(ResultSet rs) throws SQLException {
		AtuationZone zone = new AtuationZone();
		zone.setId(rs.getInt("AtuationZoneId"));
		zone.setZoneName(rs.getString("zoneName"));
		return zone;
	}

	
	public List<Corretor> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT corretor.*,atuationZone.Name as DepName "
					+ "FROM corretor INNER JOIN atuationZone "
					+ "ON corretor.AtuationZoneId = atuationZone.Id "
					+ "ORDER BY Name");
			
			rs = st.executeQuery();
			
			List<Corretor> list = new ArrayList<>();
			Map<Integer, AtuationZone> map = new HashMap<>();
			
			while (rs.next()) {
				
				AtuationZone zone = map.get(rs.getInt("AtuationZoneId"));
				
				if (zone == null) {
					zone = instantiateAtuationZone(rs);
					map.put(rs.getInt("AtuationZoneId"), zone);
				}
				
				Corretor obj = instantiateCorretor(rs, zone);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	
	public List<Corretor> findByAtuationZone(AtuationZone atuationZone) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT corretor.*,atuationZone.Name as DepName "
					+ "FROM corretor INNER JOIN atuationZone "
					+ "ON corretor.AtuationZoneId = atuationZone.Id "
					+ "WHERE AtuationZoneId = ? "
					+ "ORDER BY Name");
			
			st.setInt(1, atuationZone.getId());
			
			rs = st.executeQuery();
			
			List<Corretor> list = new ArrayList<>();
			Map<Integer, AtuationZone> map = new HashMap<>();
			
			while (rs.next()) {
				
				AtuationZone zone = map.get(rs.getInt("AtuationZoneId"));
				
				if (zone == null) {
					zone = instantiateAtuationZone(rs);
					map.put(rs.getInt("AtuationZoneId"), zone);
				}
				
				Corretor obj = instantiateCorretor(rs, zone);
				list.add(obj);
			}
			return list;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}
