package com.provp.datatransfer.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.provp.datatransfer.client.PubsubOutboundGateway;

@Component("fileMessageProcessor")
public class MessageProcessor implements  Processor{
	
	@Autowired
	private PubsubOutboundGateway messagingGateway;
	
	private static Logger logger = LoggerFactory.getLogger(MessageProcessor.class);


	@Override
	public void process(Exchange exchange) throws Exception {
		File file = exchange.getIn().getBody(File.class);
		
		Object obj = new JSONParser(new FileReader(file));
		
		String metadataString = new BufferedReader(new InputStreamReader(new FileInputStream(file))).lines()
				.collect(Collectors.joining("\n"));
		logger.info(metadataString);
		messagingGateway.sentToPubsub(metadataString);
		
	}

}
