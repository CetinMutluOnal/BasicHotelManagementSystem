package dal.dao;

import java.sql.Connection;
import java.util.HashSet;


public interface BaseDAO<T>  {
	
	
	
	public Connection con = DBConnection.getConnection();
	
	public T findById(int id );
	
	public HashSet<T> findAll();
	
	public boolean insert(T t);
	
	public boolean update(T t);
	
	public boolean delete(int id );
	
	

}
