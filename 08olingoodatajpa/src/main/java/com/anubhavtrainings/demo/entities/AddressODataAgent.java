package com.anubhavtrainings.demo.entities;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.springframework.beans.factory.annotation.Autowired;

import com.anubhavtrainings.demo.annotation.processor.ODataInterface;
import com.anubhavtrainings.demo.service.IAddressPersistence;
import com.anubhavtrainings.demo.service.IVendorPersistence;

public class AddressODataAgent implements ODataInterface {
	
	@Autowired
	IAddressPersistence addressAPI;
	
	@Override
	public List<?> getEntitySet() {
		// TODO Auto-generated method stub
		System.out.println("ADDRESSSET_GET_ENTITYSET was called");
		return addressAPI.findAll();

	}

	@Override
	public Object getEntity(Map<String, ?> keys) {
		// TODO Auto-generated method stub
		System.out.println("ADDRESSSET_GET_ENTITY was called");
		return addressAPI.findById((String) keys.get("AddressId")).get();
	}

	@Override
	public List<?> getRelatedEntity(Object source, String relatedEntityName, Map<String, Object> keys,
			Field sourceField) {
		// TODO Auto-generated method stub
		Address p = null;
		try {
			Map<String, String> targetKey = (Map<String, String>) source;
			String vendorId = targetKey.get("AddressId");
			p = addressAPI.findById(vendorId).get();
		}catch(Exception e) {
			
		}
		
	
		return new ArrayList<>();
		
	}

	@Override
	public void createEntity(Object dataToCreate) {
		// TODO Auto-generated method stub
		System.out.println("ADDRESSSET_CREATE_ENTITY was called");
		addressAPI.save((Address) dataToCreate);
	}

	@Override
	public void deleteEntity(Map<String, ?> keys) {
		// TODO Auto-generated method stub
		System.out.println("ADDRESSSET_DELETE_ENTITY was called");
		addressAPI.deleteById((String) keys.get("AddressId"));
	}

	@Override
	public void updateEntity(Object dataToUpdate) {
		// TODO Auto-generated method stub
		System.out.println("ADDRESSSET_UPDATE_ENTITY was called");
		addressAPI.save((Address) dataToUpdate);
	}

	@Override
	public void writeRelation(EdmEntitySet sourceEntitySet, Object sourceData, EdmEntitySet targetEntitySet,
			Map<String, Object> targetKeys) {
		// TODO Auto-generated method stub
		Vendor vendor = (Vendor)sourceData;
		Optional<Address> exisitingAddr = addressAPI.findById((String) targetKeys.get("AddressId"));
		Address newAddr = exisitingAddr.get();
		newAddr.setVendor(vendor);
		addressAPI.save(newAddr);
	}

}
