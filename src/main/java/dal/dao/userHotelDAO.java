package dal.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import pojo.userHotel;


public class userHotelDAO  implements BaseDAO<userHotel>{
	
	private PreparedStatement statement =null;
	
	ResultSet result = null;
	
	userHotel userHotel;

	@Override
	public userHotel findById(int id) {
		
		String findByIdQuery = "select * from calisanotel where calisan_id=?";
		
		try {
			statement=con.prepareStatement(findByIdQuery);
			statement.setObject(1, id);
			result = statement.executeQuery();
			
			if(result.next()) {
				userHotel = new userHotel();
				
				int userId = result.getInt("calisan_id");
				
				int hotel_id=result.getInt("otel_id");
				
				userHotel.setUserId(userId);
				
				userHotel.setHotelId(hotel_id);
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return userHotel;
	}

	@Override
	public HashSet<userHotel> findAll() {
		
		String findAllQuery="select * from calisanotel";
		
		HashSet<userHotel> usersHotels = new HashSet<userHotel>();
		

		
		try {
			statement = con.prepareStatement(findAllQuery);
			result = statement.executeQuery();
			
			
			
			if(result.next()) {
				
				
				
				int userId = result.getInt("calisan_id");
				int hotelId=result.getInt("otel_id");
				
				userHotel.setUserId(userId);
				userHotel.setHotelId(hotelId);
				
				usersHotels.add(userHotel);
				
			}
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return usersHotels;
	}

	@Override
	public boolean insert(userHotel userhotel) {
		
		String insertQuery= "insert into calisanotel (calisan_id,otel_id) values (?,?) ";
		
		try {
			statement = con.prepareStatement(insertQuery);
			statement.setObject(1, userhotel.getUserId());
			statement.setObject(2, userhotel.getHotelId());
			
			statement.execute();
			
			return true;
		}catch(SQLException e ) {
			e.printStackTrace();
			return false;
			
		}

	}

	@Override
	public boolean update(userHotel userhotel) {
		
		String updateQuery= "update calisanotel set otel_id=? where calisa_id=?";
		
		try {
			statement = con.prepareStatement(updateQuery);
			statement.setObject(1, userhotel.getHotelId());
			statement.setObject(2, userhotel.getUserId());
			
			statement.execute();
			
			return true;
		}catch(SQLException e ) {
			e.printStackTrace();
			return false;
			
		}

	}

	@Override
	public boolean delete(int id) {
	String deleteQuery= "delete from calisanotel where calisan_id=?";
		
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
