package com.provp.datatransfer.processor;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("fileagefilter")
public class FileAgeFilter implements Processor {
	@Value("${inputData.expire.date}")
	private int logExpireDays;
	
	//private static final Logger log = LoggerFactory.getLogger(FileAgeFilter.class);


	@Override
	public void process(Exchange exchange) throws Exception {
		File file = exchange.getIn().getBody(File.class);
		Date timestamp = new Date(file.lastModified());
		Calendar now = Calendar.getInstance();
		now.add(Calendar.DATE, -logExpireDays);
		
		if (timestamp.compareTo(now.getTime()) < 0)
			exchange.getIn().setHeader("isOld", "true");

		else
			exchange.getIn().setHeader("isOld", "false");
	}
}
