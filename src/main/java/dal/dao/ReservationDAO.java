package dal.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashSet;

import pojo.Reservation;

public class ReservationDAO implements BaseDAO<Reservation> {

	private PreparedStatement statement = null;
	
	private ResultSet result = null;
	

	@Override
	public Reservation findById(int id) {
		
		Reservation reservation=null;
		
		try {
			
			
			String findByIdQuery="SELECT * FROM rezervasyon where id=?";
			statement=con.prepareStatement(findByIdQuery);
			statement.setInt(1, id);
			result = statement.executeQuery();
			
			if(result.next()) {
			reservation = new Reservation();
			
			int reservationId = result.getInt("id");
			Date startDate= result.getDate("baslangic_tarihi");
			Date endDate= result.getDate("bitis_tarihi");
			int customerId= result.getInt("musteri_id");
			
			
			reservation.setId(reservationId);
			reservation.setStartDate(startDate);
			reservation.setEndDate(endDate);
			reservation.setCustomerId(customerId);
			
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return reservation;
	}
		
	@Override
	public HashSet<Reservation> findAll() {
		
		HashSet<Reservation> reservations = new HashSet<Reservation>();
		
		try {
			
			String findAllQuery = "SELECT * from rezervasyon ";
			statement=con.prepareStatement(findAllQuery);
			result= statement.executeQuery();
			
			Reservation reservation;
			
			
			while(result.next()) {
				reservation = new Reservation();
				
				int id = result.getInt("id");
				Date startDate= result.getDate("baslangic_tarihi");
				Date endDate= result.getDate("bitis_tarihi");
				int customerId= result.getInt("musteri_id");
				
				
				reservation.setId(id);
				reservation.setStartDate(startDate);
				reservation.setEndDate(endDate);
				reservation.setCustomerId(customerId);
				
				reservations.add(reservation);
				
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		
		
		
		
		return reservations;
	}

	@Override
	public boolean insert(Reservation reservation) {
			
		try {
			
			String insertQuery="INSERT INTO rezervasyon (baslangic_tarihi,bitis_tarihi,musteri_id) values (?,?,?)";
			statement=con.prepareStatement(insertQuery);
			statement.setObject(1, reservation.getStartDate());
			statement.setObject(2, reservation.getEndDate());
			statement.setObject(3, reservation.getCustomerId());
			
			statement.execute();
			
			return true;
		
		} catch (SQLException e) {
		
			e.printStackTrace();
			return false;
		}
	}
	
	public int insertt(Reservation reservation) {
			
		int generatedKey=0;
		
		try {
			
			String insertQuery="INSERT INTO rezervasyon (baslangic_tarihi,bitis_tarihi,musteri_id) values (?,?,?)";
			statement=con.prepareStatement(insertQuery,Statement.RETURN_GENERATED_KEYS);
			statement.setObject(1, reservation.getStartDate());
			statement.setObject(2, reservation.getEndDate());
			statement.setObject(3, reservation.getCustomerId());
			
			statement.execute();
			
			result=statement.getGeneratedKeys();
			
			if(result.next()) 
				generatedKey=result.getInt(1);

		
		} catch (SQLException e) {
		
			e.printStackTrace();
			
		}
		return generatedKey;
	}

	@Override
	public boolean update(Reservation reservation) {
		try {
			
			String updateQuery="UPDATE rezervasyon SET baslangic_tarihi=?,bitis_tarihi=?,musteri_id=? WHERE id=?";
			
			statement=con.prepareStatement(updateQuery);

			statement.setObject(1, reservation.getStartDate());
			statement.setObject(2, reservation.getEndDate());
			statement.setObject(3, reservation.getCustomerId());
			statement.setObject(4, reservation.getId());
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
			String deleteQuery = "DELETE from rezervasyon where id=?";
			
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
