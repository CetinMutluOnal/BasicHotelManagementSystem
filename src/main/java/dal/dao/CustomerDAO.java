package dal.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import pojo.Customer;
public class CustomerDAO implements BaseDAO<Customer>{
	
	private PreparedStatement statement = null;
	
	
	private ResultSet result = null;
	
	
public Customer findbyEmail( String mail) {
		
		String findByEmailQuery = ("SELECT * from musteri where mail = ? ");
		
		Customer customer = null;
		
				
		try {
			
			statement=con.prepareStatement(findByEmailQuery);
			statement.setString(1, mail);
			result= statement.executeQuery();
			
			if(result.next()) {
			customer = new Customer();
			
			int customerId = result.getInt("id");
			String name= result.getString("isim");
			String password = result.getString("sifre");
			String email = result.getString("mail");
			String phoneNo= result.getString("telefon_no");
			String identityNo= result.getString("kimlik_no");
			String adress= result.getString("adres");
			
			customer.setId(customerId);
			customer.setName(name);
			customer.setPassword(password);
			customer.setMail(email);
			customer.setPhoneNo(phoneNo);
			customer.setIdNo(identityNo);
			customer.setAdress(adress);
			}
			
	
		}	catch(SQLException e ){
			e.printStackTrace();
		}
		
		
		return customer;
		
		
	}
	

	@Override
	public Customer findById(int id) {
		
		Customer customer = null;
		
		try {
			 String findByIdQuery="SELECT * FROM musteri where id=?";
			 
			statement=con.prepareStatement(findByIdQuery);
			statement.setInt(1, id);
			result = statement.executeQuery();
			
			if(result.next()) {
				customer= new Customer();
				int customerId = result.getInt("id");
				String name= result.getString("isim");
				String password = result.getString("sifre");
				String mail = result.getString("mail");
				String phoneNo= result.getString("telefon_no");
				String identityNo= result.getString("kimlik_no");
				String adress= result.getString("adres");
				
				customer.setId(customerId);
				customer.setName(name);
				customer.setPassword(password);
				customer.setMail(mail);
				customer.setPhoneNo(phoneNo);
				customer.setIdNo(identityNo);
				customer.setAdress(adress);
				
			}
			
		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return customer;
	}
		
	@Override
	public HashSet<Customer> findAll() {
			HashSet<Customer> customers = new HashSet<Customer>();
		try {
			Customer customer;
			
			String findAllQuery = "SELECT * from musteri";
			statement=con.prepareStatement(findAllQuery);
			result=statement.executeQuery();
			
			while(result.next()) {
				customer = new Customer();
				 
				int id = result.getInt("id");
				String name= result.getString("isim");
				String password = result.getString("sifre");
				String mail = result.getString("mail");
				String phoneNo= result.getString("telefon_no");
				String identityNo= result.getString("kimlik_no");
				String adress= result.getString("adres");
				
				customer.setId(id);
				customer.setName(name);
				customer.setPassword(password);
				customer.setMail(mail);
				customer.setPhoneNo(phoneNo);
				customer.setIdNo(identityNo);
				customer.setAdress(adress);
				
				customers.add(customer);
				
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		
		
		
		
		
		return customers;
	}

	@Override
	public boolean insert(Customer customer) {
			
		try {
			
			String insertQuery = "INSERT INTO musteri (isim,sifre,mail,telefon_no,kimlik_no,adres) values (?,?,?,?,?,?)";
			
			statement = con.prepareStatement(insertQuery);
			statement.setObject(1, customer.getName());
			statement.setObject(2, customer.getPassword());
			statement.setObject(3, customer.getMail());
			statement.setObject(4, customer.getPhoneNo());
			statement.setObject(5, customer.getIdNo());
			statement.setObject(6, customer.getAdress());
			
			statement.execute();
			
			
			return true;
		
		} catch (SQLException e) {
		
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Customer customer) {
		
		try {
			
			String updateQuery="UPDATE  musteri  SET isim = ? , sifre= ?, mail = ?, telefon_no= ?,kimlik_no= ?, adres= ? WHERE id=?";
			statement = con.prepareStatement(updateQuery);
			
			statement.setObject(1, customer.getName());
			statement.setObject(2, customer.getPassword());
			statement.setObject(3, customer.getMail());
			statement.setObject(4, customer.getPhoneNo());
			statement.setObject(5, customer.getIdNo());
			statement.setObject(6, customer.getAdress());
			statement.setObject(7, customer.getId());
			
			
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
			String deleteQuery = "DELETE from musteri where id=?";
			
			statement= con.prepareStatement(deleteQuery);
			statement.setInt(1, id);
			statement.execute();
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
			
		}
		
	}

	
	
}
