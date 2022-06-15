package dal.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import pojo.Hotel;

public class HotelDAO implements BaseDAO<Hotel> {

	private PreparedStatement statement = null;
	
	private ResultSet result = null;
	

	@Override
	public Hotel findById(int id) {
			Hotel hotel=null;
		try {
			 String findByIdQuery="SELECT * FROM otel where id=?";
			 statement=con.prepareStatement(findByIdQuery);
			 statement.setInt(1, id);
			 result=statement.executeQuery();
				
				while(result.next()) {
					
					hotel = new Hotel();
					
					int hotelId = result.getInt("id");
					String name = result.getString("otel_isim");
					String sehir =result.getString("sehir");
					String ilce = result.getString("ilce");
					String aciklama = result.getString("aciklama");
					
					hotel.setId(hotelId);
					hotel.setName(name);
					hotel.setSehir(sehir);
					hotel.setIlce(ilce);
					hotel.setAciklama(aciklama);
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return hotel;
	}
		
	@Override
	public HashSet<Hotel> findAll() {
		HashSet<Hotel> hotels = new HashSet<Hotel>();
		try {
			Hotel hotel;
			
			String findAllQuery = "SELECT * from otel";
			statement=con.prepareStatement(findAllQuery);
			result=statement.executeQuery();
			
			while(result.next()) {
				
				hotel = new Hotel();
				
				int id = result.getInt("id");
				String name = result.getString("otel_isim");
				String sehir =result.getString("sehir");
				String ilce = result.getString("ilce");
				String aciklama = result.getString("aciklama");
				
				hotel.setId(id);
				hotel.setName(name);
				hotel.setSehir(sehir);
				hotel.setIlce(ilce);
				hotel.setAciklama(aciklama);
				
				hotels.add(hotel);

			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		return hotels;
	}

	@Override
	public  boolean insert(Hotel hotel) {
			
		try {
			
			String insertQuery="INSERT INTO otel (otel_isim,sehir,ilce,aciklama) VALUES (?,?,?,?)";
			
			statement=con.prepareStatement(insertQuery);
			statement.setObject(1, hotel.getName());
			statement.setObject(2, hotel.getSehir());
			statement.setObject(3, hotel.getIlce());
			statement.setObject(4, hotel.getAciklama());
			statement.execute();
			
			
			return true;
		
		} catch (SQLException e) {
		
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Hotel hotel) {
		try {
			
			String updateQuery="UPDATE otel SET otel_isim=?,sehir=?, ilce=?,aciklama=? WHERE id=? ";
			
			statement=con.prepareStatement(updateQuery);
			statement.setObject(1, hotel.getName());
			statement.setObject(2, hotel.getSehir());
			statement.setObject(3, hotel.getIlce());
			statement.setObject(4, hotel.getAciklama());
			statement.setObject(5, hotel.getId());
			
			
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
			String deleteQuery = "DELETE from otel where id=?";
			
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
