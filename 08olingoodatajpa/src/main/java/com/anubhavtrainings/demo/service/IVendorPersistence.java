package com.anubhavtrainings.demo.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.anubhavtrainings.demo.entities.Vendor;

public interface IVendorPersistence extends JpaRepository<Vendor, String> {
	
	List <Vendor> findByCompanyName(String companyName);
	
	@Query(nativeQuery=true, value="SELECT * FROM public.vendor where lower(first_name) like %?1% ")
	List<Vendor> lookupByFirstName(String firstName);
	
}
