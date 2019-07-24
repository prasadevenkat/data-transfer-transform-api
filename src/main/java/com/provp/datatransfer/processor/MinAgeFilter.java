package com.provp.datatransfer.processor;

import org.apache.camel.component.file.GenericFile;
import org.apache.camel.component.file.GenericFileFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class MinAgeFilter<T> implements GenericFileFilter<T> {
	@Value("${custom.minwait}")
	private String minwait;

	@Override
	public boolean accept(GenericFile<T> file) {
		return (file.getLastModified() < (System.currentTimeMillis() - Long.valueOf(minwait)));
	}

}
