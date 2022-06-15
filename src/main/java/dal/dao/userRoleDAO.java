package dal.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import pojo.*;



public class userRoleDAO  implements BaseDAO<userRole>{
	
	private PreparedStatement statement =null;
	
	ResultSet result = null;

	@Override
	public userRole findById(int id) {
		
		userRole userRole = null;
		
		String findByIdQuery = "select * from kullanicirol where kullanici_id=?";
		
		try {
			statement=con.prepareStatement(findByIdQuery);
			statement.setObject(1, id);
			result = statement.executeQuery();
			
			if(result.next()) {
				userRole = new userRole();
				
				int userId = result.getInt("kullanici_id");
				
				int roleId=result.getInt("rol_id");
				
				userRole.setUserId(userId);
				
				userRole.setRoleID(roleId);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return userRole;
	}

	@Override
	public HashSet<userRole> findAll() {
		
		String findAllQuery="select * from kullanicirol";
		
		HashSet<userRole> userRoles = new HashSet<userRole>();
		
		userRole userRole = null;
		
		try {
			statement = con.prepareStatement(findAllQuery);
			result = statement.executeQuery();
			
			
			
			if(result.next()) {
				
				
				
				int userId = result.getInt("kullanici_id");
				int roleId=result.getInt("rol_id");
				
				userRole.setUserId(userId);
				userRole.setRoleID(roleId);
				
				userRoles.add(userRole);
				
			}
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return userRoles;
	}

	@Override
	public boolean insert(userRole userrole) {
		
		String insertQuery= "insert into kullanicirol (kullanici_id,rol_id) values (?,?) ";
		
		try {
			statement = con.prepareStatement(insertQuery);
			statement.setObject(1, userrole.getUserId());
			statement.setObject(2, userrole.getRoleID());
			
			statement.execute();
			
			return true;
		}catch(SQLException e ) {
			e.printStackTrace();
			return false;
			
		}

	}

	@Override
	public boolean update(userRole userrole) {
		
		String updateQuery= "update kullanicirol set rol_id=? where kullanici_id=?";
		
		try {
			statement = con.prepareStatement(updateQuery);
			statement.setObject(1, userrole.getRoleID());
			statement.setObject(2, userrole.getUserId());
			
			statement.execute();
			
			return true;
		}catch(SQLException e ) {
			e.printStackTrace();
			return false;
			
		}

	}

	@Override
	public boolean delete(int id) {
	String deleteQuery= "delete from kullanicirol where kullanici_id=?";
		
		try {
			statement = con.prepareStatement(deleteQuery);
			statement.setObject(1, id);
			
			statement.execute();
			
			return true;
		}catch(SQLException e ) {
			e.printStackTrace();
			return false;
			
		}

	}

}
