package com.provp.datatransfer.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class DataTransferRoute extends RouteBuilder {
	
	@Value("${directories.source}")
	private String backFillSourceStr;

	@Value("${directories.success}")
	private String successStr;

	@Value("${directories.reprocess.source}")
	private String retryStr;

	@Value("${directories.backfill.error}")
	private String failedStr;
	
	/*
	 * @Value("${destination.url}") private String destUrlStr;
	 * 
	 * @Value("${destination.key}") private String destKeyStr;
	 */

	@Value("${custom.backfill.pollrate}")
	private String pollrate;

	@Value("${custom.maxmessages}")
	private String maxmessages;
	
	
	 @Value("${backfillrouter.execution.quartz}")
	 public String backfillQuartz;
	 

	@Override
	public void configure() throws Exception {
		

		from("file:"
				.concat(backFillSourceStr)
				/*
				 * .concat("?scheduler=quartz2&scheduler.cron=") .concat(backfillQuartz)
				 */
				.concat("?filter=#minAgeFilter&delay=")
				.concat(pollrate)
				.concat("&maxMessagesPerPoll=")
				.concat(maxmessages)
				.concat("&move=")
				.concat(successStr)
				.concat("&moveFailed=")
				.concat(retryStr))
		.process("fileagefilter")
		.choice()
		.when(header("isOld").isEqualTo("true")).log("Rejecting older file ${header.CamelFileName} to fail folder").to("file:".concat(failedStr))
		.otherwise()
		
		.process("fileMessageProcessor")
		.end();
		
		
	}

}
