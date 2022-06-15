package pojo;

public class Hotel {
	
	private int id;
	
	private String name;
	
	private String sehir;
	
	private String ilce;
	
	private String aciklama;
	
	
	public String getSehir() {
		return sehir;
	}
	public void setSehir(String sehir) {
		this.sehir = sehir;
	}
	public String getIlce() {
		return ilce;
	}
	public void setIlce(String ilce) {
		this.ilce = ilce;
	}
	public String getAciklama() {
		return aciklama;
	}
	public void setAciklama(String aciklama) {
		this.aciklama = aciklama;
	}
	public Hotel(int id, String name, String sehir, String ilce, String aciklama) {
		this.id=id;
		this.name=name;
		this.sehir=sehir;
		this.ilce=ilce;
		this.aciklama=aciklama;

		
	}
	public Hotel (String name,String sehir, String ilce, String aciklama) {
		this.name=name;
		this.sehir=sehir;
		this.ilce=ilce;
		this.aciklama=aciklama;

	}
	
	public Hotel () {
		
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Hotel [id=" + id + ", name=" + name + ", sehir=" + sehir + ", ilce=" + ilce + ", aciklama=" + aciklama
				+ "]";
	}



	
	
	

}
