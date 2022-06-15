package pojo;

public class userHotel {
	
	int userId;
	
	int hotelId;
	
	
	public userHotel(int userId, int hotelID) {
		
		this.userId=userId;
		
		this.hotelId=hotelID;
		
	}
	
	public userHotel() {
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	@Override
	public String toString() {
		return "userHotel [userId=" + userId + ", hotelId=" + hotelId + "]";
	}
	
	

}
