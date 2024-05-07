package com.anubhavtrainings.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.apache.olingo.odata2.api.annotation.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.annotation.edm.EdmEntityType;
import org.apache.olingo.odata2.api.annotation.edm.EdmKey;
import org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty;
import org.apache.olingo.odata2.api.annotation.edm.EdmNavigationProperty.Multiplicity;
import org.apache.olingo.odata2.api.annotation.edm.EdmProperty;
import org.hibernate.annotations.GenericGenerator;

@Entity
@EdmEntityType
@EdmEntitySet
public class Address {
	
	@Id
	@Column(nullable=false, name="id")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
	@EdmKey
	@EdmProperty
	private String addressId;
	@Column(nullable=false, name="address_type")
	@EdmProperty
	private String addressType;
	@Column(nullable=true, name="street")
	@EdmProperty
	private String street;
	@Column(nullable=true, name="city")
	@EdmProperty
	private String city;
	@Column(nullable=true, name="country")
	@EdmProperty
	private String country;
	@Column(nullable=true, name="region")
	@EdmProperty
	private String region;
	
	@ManyToOne
	@JoinColumn(name="vendor_id", referencedColumnName="id")
	@EdmNavigationProperty(toType = Vendor.class, toMultiplicity = Multiplicity.ONE)
	public Vendor vendor;
	
	

	public Address() {
		super();
	}
	
	public Address(String addressId, String addressType, String street, String city, String country, String region) {
		super();
		this.addressId = addressId;
		this.addressType = addressType;
		this.street = street;
		this.city = city;
		this.country = country;
		this.region = region;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}

	
	
}
