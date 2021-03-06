package com.mastercart.model.dto;

public class ShopDTO {
	private String id;
	private String name;
    private byte[] imageResource;
    private String location;
    private String lat;
    private String lng;
    private String phone;
    private String email;
    private String sellerEmail;
    
    public ShopDTO() {}
    
	public ShopDTO(String id, String name, byte[] imageResource, String location, String lat, String lng, String phone,
                   String email, String sellerEmail) {
		super();
		this.id = id;
		this.name = name;
		this.imageResource = imageResource;
		this.location = location;
		this.lat = lat;
		this.lng = lng;
		this.phone = phone;
		this.email = email;
		this.sellerEmail = sellerEmail;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getImageResource() {
		return imageResource;
	}

	public void setImageResource(byte[] imageResource) {
		this.imageResource = imageResource;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
