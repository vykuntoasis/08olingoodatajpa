package com.anubhavtrainings.demo.annotation.processor;

import org.apache.olingo.odata2.annotation.processor.core.datasource.DataSource;
import org.apache.olingo.odata2.api.edm.EdmEntitySet;
import org.apache.olingo.odata2.api.edm.EdmException;
import org.apache.olingo.odata2.api.exception.ODataApplicationException;
import org.apache.olingo.odata2.api.exception.ODataNotImplementedException;

public interface MyODataSource extends DataSource{
	void updateData(EdmEntitySet entitySet, Object data) throws ODataNotImplementedException, EdmException,
    ODataApplicationException;
}