package dal.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import pojo.Room;

public class RoomDAO implements BaseDAO<Room>{
	
	private PreparedStatement statement = null;
	
	private ResultSet result = null;
	

	@Override
	public Room findById(int id) {
		Room room=null;
		
		try {
			 String findByIdQuery="SELECT * FROM oda where id=?";
			 statement=con.prepareStatement(findByIdQuery);
			 statement.setInt(1, id);
			 result=statement.executeQuery();
			 
			 if (result.next()) {
				 room = new Room();
				 int roomId= result.getInt("id");
				 int number = result.getInt("numara");
				 int hotelId = result.getInt("otel_id");
				 int reservationId= result.getInt("rezervasyon_id");
				 int bedQuantity= result.getInt("yatak_sayisi");
				 int price = result.getInt("fiyat");
					
				 room.setId(roomId);
				 room.setNumber(number);
				 room.setHotelId(hotelId);
				 room.setReservationId(reservationId);
				 room.setBedQuantity(bedQuantity);
				 room.setPrice(price);
				 
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return room;
	}
	
	
	public Room findByHotelId(int id) {
		Room room=null;
		
		try {
			 String findByHotelIdQuery="SELECT * FROM oda where otel_id=?";
			 statement=con.prepareStatement(findByHotelIdQuery);
			 statement.setInt(1, id);
			 result=statement.executeQuery();
			 
			 if (result.next()) {
				 room = new Room();
				 int roomId= result.getInt("id");
				 int number = result.getInt("numara");
				 int hotelId = result.getInt("otel_id");
				 int reservationId= result.getInt("rezervasyon_id");
				 int bedQuantity = result.getInt("yatak_sayisi");
				 int price= result.getInt("fiyat");
					
				 room.setId(roomId);
				 room.setNumber(number);
				 room.setHotelId(hotelId);
				 room.setReservationId(reservationId);
				 room.setBedQuantity(bedQuantity);
				 room.setPrice(price);
				 
			 }
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return room;
	}
		
	@Override
	public HashSet<Room> findAll() {
		
		HashSet<Room> rooms = new HashSet<Room>();
		
		try {
			String findAllQuery = "SELECT * from oda";
			statement=con.prepareStatement(findAllQuery);
			result=statement.executeQuery();
			
			Room room;
			
			while(result.next()) {
				room = new Room();
				
				int id= result.getInt("id");
				int number = result.getInt("numara");
				int hotelId = result.getInt("otel_id");
				int reservationId= result.getInt("rezervasyon_id");
				int bedQuantity= result.getInt("yatak_sayisi");
				int price = result.getInt("fiyat");
				
				room.setId(id);
				room.setNumber(number);
				room.setHotelId(hotelId);
				room.setReservationId(reservationId);
				room.setBedQuantity(bedQuantity);
				room.setPrice(price);
				
				rooms.add(room);
				
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return rooms;
	}
	
public HashSet<Room> findHotelsAll(int hotelsId) {
		
		HashSet<Room> rooms = new HashSet<Room>();
		
		try {
			String findAllQuery = "SELECT * from oda where otel_id=?";
			statement=con.prepareStatement(findAllQuery);
			statement.setObject(1, hotelsId);
			result=statement.executeQuery();
			
			Room room;
			
			while(result.next()) {
				room = new Room();
				
				int id= result.getInt("id");
				int number = result.getInt("numara");
				int hotelId = result.getInt("otel_id");
				int reservationId= result.getInt("rezervasyon_id");
				int bedQuantity= result.getInt("yatak_sayisi");
				int price = result.getInt("fiyat");
				
				room.setId(id);
				room.setNumber(number);
				room.setHotelId(hotelId);
				room.setReservationId(reservationId);
				room.setBedQuantity(bedQuantity);
				room.setPrice(price);
				
				rooms.add(room);
				
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return rooms;
	}

	@Override
	public boolean insert(Room room) {
			
		try {
			
			String insertQuery="INSERT INTO oda (numara,otel_id,yatak_sayisi,fiyat) VALUES (?,?,?,?)";
			
			statement=con.prepareStatement(insertQuery);
			
			statement.setObject(1, room.getNumber());
			statement.setObject(2, room.getHotelId());
			statement.setObject(3, room.getBedQuantity());
			statement.setObject(4, room.getPrice());
			
			
			statement.execute();
			
			
			return true;
		
		} catch (SQLException e) {
		
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Room room) {
		try {
			
			String updateQuery="UPDATE oda SET numara=?,otel_id=?,yatak_sayisi=?, fiyat=? WHERE id=?";
			
			statement=con.prepareStatement(updateQuery);
			statement.setObject(1, room.getNumber());
			statement.setObject(2, room.getHotelId());
			statement.setObject(3, room.getBedQuantity());
			statement.setObject(4, room.getPrice());
			statement.setObject(5, room.getId());
			
			statement.execute();
			
			
			return true;
				} catch (SQLException e) {
					e.printStackTrace();
				return false;
		}
	}

	public boolean updateReservation(int reservationId,int roomId) {
		try {
			
			String updateQuery="UPDATE oda SET rezervasyon_id=? WHERE id=?";
			
			statement=con.prepareStatement(updateQuery);
			statement.setObject(1, reservationId);
			statement.setObject(2, roomId);
			statement.execute();
			
			
			return true;
				} catch (SQLException e) {
					e.printStackTrace();
				return false;
		}
	}
	public boolean updateReservationNull(String value,int roomId) {
		try {
			
			String updateQuery="UPDATE oda SET rezervasyon_id=? WHERE id=?";
			
			statement=con.prepareStatement(updateQuery);
			statement.setObject(1, value);
			statement.setObject(2, roomId);
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
			String deleteQuery = "DELETE from oda where id=?";
			
			statement=con.prepareStatement(deleteQuery);
			statement.setInt(1, id);
			statement.execute();
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
			
		}
		
	}
	
	
}
