package com.provp.datatransfer.model;

import java.util.HashMap;

public class MessageData {
	
	public String dataName;
	
	public HashMap<String, Object> fields;

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public HashMap<String, Object> getFields() {
		return fields;
	}

	public void setFields(HashMap<String, Object> fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		return "MessageData [dataName=" + dataName + ", fields=" + fields + "]";
	}
	
		
		

}
