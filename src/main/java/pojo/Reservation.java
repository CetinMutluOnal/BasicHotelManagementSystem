package pojo;

import java.util.*;

public class Reservation {
	
	private int id;
	
	private Date startDate;
	
	private Date endDate;
	
	private int customerId;

	public Reservation(Date startDate, Date endDate, int customerId) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.customerId= customerId;
	}
	public Reservation(int id, Date startDate, Date endDate, int customerId) {
		super();
		this.id=id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.customerId= customerId;
	}
	public Reservation() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	@Override
	public String toString() {
		return "Reservation [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", customerId="
				+ customerId + "]";
	}
	

	
	
	
	

}

