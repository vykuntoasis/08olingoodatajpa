package com.anubhavtrainings.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anubhavtrainings.demo.entities.*;

@Component
public class AddressService {
	
	@Autowired
	IAddressPersistence address;
	
	public List<Address> getAddress(){
		return address.findAll();
	}
	
	public Address createAddress(Address payload) {
		return address.save(payload);
	}
	
}
