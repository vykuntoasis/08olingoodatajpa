
package com.anubhavtrainings.demo.annotation.processor;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.apache.olingo.odata2.api.edm.EdmEntitySet;

public interface ODataInterface {
	public List<?> getEntitySet();
	
	public Object getEntity(Map<String, ?> keys);
	
	public List<?> getRelatedEntity(Object source, String relatedEntityName, Map<String, Object> keys, Field sourceField);
	
	public void createEntity(Object dataToCreate);
	
	public void deleteEntity(Map<String, ?> keys);
	
	public void updateEntity(Object dataToUpdate);
	
	public void writeRelation(EdmEntitySet sourceEntitySet, Object sourceData, EdmEntitySet targetEntitySet,
			Map<String, Object> targetKeys);
}