package pojo;

public class Room {
	
	private int id;
	
	private int number;
	
	private int hotelId;
	
	private int reservationId;
	
	private int bedQuantity;
	
	private int price;

	public Room(int id, int number, int hotelId,int bedQuantity,int price) {
		super();
		this.id = id;
		this.number = number;
		this.hotelId=hotelId;
		this.bedQuantity=bedQuantity;
		this.price=price;
	}
/*	public Room(int number, int hotelId, int reservationId) {
		super();
		this.number = number;
		this.hotelId=hotelId;
		this.reservationId = reservationId;
	}
	*/
	public Room(int number,int hotelId,int bedQuantity,int price) {
		super();
		this.number = number;
		this.hotelId=hotelId;
		this.bedQuantity=bedQuantity;
		this.price=price;
	}
	
	public Room() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getHotelId() {
		return hotelId;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public int getBedQuantity() {
		return bedQuantity;
	}
	public void setBedQuantity(int bedQuantity) {
		this.bedQuantity = bedQuantity;
	}
	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Room [id=" + id + ", number=" + number + ", hotelId=" + hotelId + ", reservationId=" + reservationId
				+ ", bedQuantity=" + bedQuantity + ", price=" + price + "]";
	}
	
	


}
