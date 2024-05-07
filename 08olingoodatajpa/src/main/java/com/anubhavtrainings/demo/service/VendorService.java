package com.anubhavtrainings.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.anubhavtrainings.demo.entities.Vendor;

@Component
public class VendorService {
	
	//this is like a internal table which contains multiple records
	HashMap<String, Vendor> vendors = new HashMap<String, Vendor>();
	
	@Autowired
	IVendorPersistence vendor;
	
//	@Autowired
//	Vendor ven1;
//	@Autowired
//	Vendor ven2;
//	@Autowired
//	Vendor ven3;
	
	private void fillVendors() {
		//append wa to itab
//		vendors.put("1", ven1);
//		vendors.put("2", ven2);
//		vendors.put("3", ven3);
	}
	
	public void VendorService() {
		fillVendors();
	}
	
	public List<Vendor> getAllVendors(){
		//fillVendors();
		
		//Spring JPA will fire a SELECT query
		return vendor.findAll();
		//return vendors;
	}
	public Optional<Vendor> getVendorByKey(String vendorId) {
		
		//Send SELECT SINGLE * to database 
		return vendor.findById(vendorId);
		
		//Read table itab into wa with key ve=id
//		fillVendors();
//		return (Vendor)vendors.get(vendorId);
	}
	public Vendor createVendor(Vendor vendorObject) {
		
		//Spring JPA will fire INSERT INTO dbtable
		return vendor.save(vendorObject);
		
		
//		fillVendors();
//		vendors.put("4", vendor);
//		return vendor;
	}
	public Vendor updateVendor(Vendor vendorObject) {
		
		//Handle the update call
		Optional<Vendor> searchedRecord = vendor.findById(vendorObject.getCode());
		if(!searchedRecord.isPresent()) {
			return new Vendor();
		}
		return vendor.save(vendorObject);
		
//		fillVendors();
//		return vendor;
	}
	
	public List<Vendor> searchByCompanyName(String company){
		return vendor.findByCompanyName(company);
	}
	
	public List<Vendor> lookupByFirstName(String firstname){
		return vendor.lookupByFirstName(firstname);
	}
	
	public String deleteVendor(String vendorId) {
		
		vendor.deleteById(vendorId);
		return "Deleted Successfully";
		
//		fillVendors();
//		vendors.remove(vendorId);
	}
	
}