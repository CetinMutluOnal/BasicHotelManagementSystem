package dal.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import pojo.User;
import pojo.userHotel;
import pojo.userRole;

public class UserDAO implements BaseDAO<User> {
	
	
	private PreparedStatement statement = null;
	
	private ResultSet result= null;
	
	public User findbyEmail( String mail) {
		
		String findByEmailQuery = ("SELECT * from kullanici where email = ? ");
		
		User user = null;
		
				
		try {
			
			statement=con.prepareStatement(findByEmailQuery);
			statement.setString(1, mail);
			result= statement.executeQuery();
			
			if(result.next()) {
			user = new User();
			
			int userid= result.getInt("id");
			String name = result.getString("isim");
			String password = result.getString("sifre");
			String email =result.getString("email");
			
			user.setId(userid);
			user.setUsername(name);
			user.setPassword(password);
			user.setMail(email);
			}
			
	
		}	catch(SQLException e ){
			e.printStackTrace();
		}
		
		
		return user;
		
		
	}
	
	
	public User findById(int id) {
		
	
		String findByIdQuery = ("SELECT * from kullanici where id=?");
		
		User user = null;
		try {
		
			statement=con.prepareStatement(findByIdQuery);
			statement.setInt(1, id);
			result= statement.executeQuery();
			
			if(result.next()) {
			user = new User();
			
			int userid= result.getInt("id");
			String name = result.getString("isim");
			String password = result.getString("sifre");
			String mail =result.getString("email");
			
			user.setId(userid);
			user.setUsername(name);
			user.setPassword(password);
			user.setMail(mail);
			}
			
	
		}	catch(SQLException e ){
			e.printStackTrace();
		}
		
		
		return user;
		
	}
	
	
	public HashSet<User> findAll() {
			
		HashSet<User> users = new HashSet<User>();
		
		try {
			String findAllQuery = "SELECT * from kullanici";
			
			
			statement=con.prepareStatement(findAllQuery);
			result = statement.executeQuery();
			
			User user;
			
			while (result.next()) {
				user = new User();
				int id = result.getInt("id");
				String name = result.getString("isim");
				String password = result.getString("sifre");
				String mail = result.getString("email");
				
				user.setId(id);
				user.setUsername(name);
				user.setPassword(password);
				user.setMail(mail);
				
				
				users.add(user);
				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return users;
	}

		public userRole findRole(int id) {
			
			userRole user = null;
			
			String findRoleQuery="select * from kullanicirol where kullanici_id= ?";
			
			try {
			
			statement=con.prepareStatement(findRoleQuery);
			statement.setObject(1, id);
			result = statement.executeQuery();
			
			if(result.next()) {
				
				user = new userRole();
				
				int userId=result.getInt("kullanici_id");
				
				int roleId= result.getInt("rol_id");
				
				user.setUserId(userId);
				user.setRoleID(roleId);
				
				
			}
		
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return user;
		}
		
		public userHotel findUserHotel(int userId) {
			
			userHotel user = null;
			
			String query = "select * from calisanotel where calisan_id=?";
			
			try {
				statement=con.prepareStatement(query);
				statement.setObject(1, userId);
				
				result = statement.executeQuery();
				
				if(result.next()) {
					
					user = new userHotel();
					int usersId = result.getInt("calisan_id");
					int hotelId=result.getInt("otel_id");
					
					user.setUserId(usersId);
					user.setHotelId(hotelId);
				}
				
				
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return user;
			
		}

	@Override
	public boolean  insert(User user) {
		
	
		
		try {
			String insertQuery="INSERT INTO kullanici (isim,sifre,email) values (?,?,?)";
					
					
			
			statement=con.prepareStatement(insertQuery);
			statement.setObject(1, user.getUsername());
			statement.setObject(2, user.getPassword());
			statement.setObject(3, user.getMail());
			
			statement.execute();
			
			return true;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			return false;
			
		}
		
	}


	@Override
	public boolean update(User user) {
			try {
			String updateQuery="UPDATE kullanici SET isim=?,sifre=?,email=? WHERE id=? ";
			
			statement=con.prepareStatement(updateQuery);
			
			statement.setObject(1, user.getUsername());
			statement.setObject(2, user.getPassword() );
			statement.setObject(3, user.getMail());
			statement.setObject(4, user.getId());
			statement.execute();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public boolean delete(int id) {
		try {
			String deleteQuery="DELETE from kullanici where id=?";
			
			statement=con.prepareStatement(deleteQuery);
			statement.setInt(1, id);
			statement.execute();
			
			return true;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
			
		}
	}


}
