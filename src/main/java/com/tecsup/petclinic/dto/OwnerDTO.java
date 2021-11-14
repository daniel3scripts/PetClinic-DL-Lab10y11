package com.tecsup.petclinic.dto;

public class OwnerDTO {
	private long id;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String telephone;


	
	public OwnerDTO( String firstName, String lastName, String address, String city, String telephone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.telephone = telephone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String first_name) {
		this.firstName = first_name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String last_name) {
		this.lastName = last_name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	@Override
	public String toString() {
		return "Owner [id=" + id + ", first_name=" + firstName + ", last_name=" + lastName + ", "
				+ "address=" + address + ", city= "+ city + ", telephone=" + telephone + "]";
	}
}
