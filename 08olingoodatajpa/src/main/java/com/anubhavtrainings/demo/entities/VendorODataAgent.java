package com.anubhavtrainings.demo.entities;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.springframework.beans.factory.annotation.Autowired;

import com.anubhavtrainings.demo.annotation.processor.ODataInterface;
import com.anubhavtrainings.demo.service.IVendorPersistence;

public class VendorODataAgent implements ODataInterface {
	
	@Autowired
	IVendorPersistence vendorAPI;
	
	@Override
	public List<?> getEntitySet() {
		// TODO Auto-generated method stub
		System.out.println("VENDORSET_GET_ENTITYSET was called");
		return vendorAPI.findAll();

	}

	@Override
	public Object getEntity(Map<String, ?> keys) {
		// TODO Auto-generated method stub
		System.out.println("VENDORSET_GET_ENTITY was called");
		return vendorAPI.findById((String) keys.get("Code")).get();
	}

	@Override
	public List<?> getRelatedEntity(Object source, String relatedEntityName, Map<String, Object> keys,
			Field sourceField) {
		// TODO Auto-generated method stub
		Vendor p = null;
		try {
			Vendor targetKey = (Vendor) source;
			String vendorId = targetKey.getCode();
			p = vendorAPI.findById(vendorId).get();
		}catch(Exception e) {
			
		}
		
		if(relatedEntityName.equalsIgnoreCase("AddressSet")) {
			return p.getAddressess();
		}
		
		return new ArrayList<>();
		
	}

	@Override
	public void createEntity(Object dataToCreate) {
		// TODO Auto-generated method stub
		System.out.println("VENDORSET_CREATE_ENTITY was called");
		vendorAPI.save((Vendor) dataToCreate);
	}

	@Override
	public void deleteEntity(Map<String, ?> keys) {
		// TODO Auto-generated method stub
		System.out.println("VENDORSET_DELETE_ENTITY was called");
		vendorAPI.deleteById((String) keys.get("Id"));
	}

	@Override
	public void updateEntity(Object dataToUpdate) {
		// TODO Auto-generated method stub
		System.out.println("VENDORSET_UPDATE_ENTITY was called");
		vendorAPI.save((Vendor) dataToUpdate);
	}

	@Override
	public void writeRelation(EdmEntitySet sourceEntitySet, Object sourceData, EdmEntitySet targetEntitySet,
			Map<String, Object> targetKeys) {
		// TODO Auto-generated method stub

	}

}
