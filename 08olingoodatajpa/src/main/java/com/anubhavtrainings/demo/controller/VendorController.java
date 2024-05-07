package com.anubhavtrainings.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.anubhavtrainings.demo.entities.Vendor;
import com.anubhavtrainings.demo.service.VendorService;

@RestController
public class VendorController {
	
	@Autowired
	VendorService vendorService;
	
	@RequestMapping("/vendor")
	public List<Vendor> getVendors(){
		return vendorService.getAllVendors();
	}
	
	@RequestMapping("/vendor/{vendorId}")
	public Optional<Vendor> getSingleVendor(@PathVariable("vendorId") String vendorId) {
		return vendorService.getVendorByKey(vendorId);
	}
	
	@PostMapping("/vendor")
	public Vendor createVendor(@RequestBody Vendor myVendorData) {
		return vendorService.createVendor(myVendorData);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/vendor")
	public Vendor updateVendor(@RequestBody Vendor myUpdateVendor) {
		return vendorService.updateVendor(myUpdateVendor);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/vendor/{vendorId}")
	public void deleteVendor(@PathVariable("vendorId") String vendorId) {
		vendorService.deleteVendor(vendorId);
	}
	
	//Test using /vendor/search?company=SAP
	@RequestMapping("/vendor/search")
	public List<Vendor> searchByCompany(@RequestParam String company){
		return vendorService.searchByCompanyName(company);
	}
	
	//Test using /vendor/lookup/A
	@RequestMapping("/vendor/lookup/{firstName}")
	public List<Vendor> searchByFirstName(@PathVariable("firstName") String firstName){
		return vendorService.lookupByFirstName(firstName);
	}
	
	
}
