package com.provp.datatransfer.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.provp.datatransfer.client.PubsubOutboundGateway;
import com.provp.datatransfer.model.MessageData;

@RestController
public class DataTransferResource {
	
	@Autowired
	private PubsubOutboundGateway messagingGateway;

	private static final Logger logger = LoggerFactory.getLogger(DataTransferResource.class);
	
	@RequestMapping(value = "/publishData", method = RequestMethod.PUT)
	public ResponseEntity<?> createMessage(@RequestBody MessageData messageData)
	{
		logger.info("Inside DataTransferResource,publishData processing the data of name {} ", messageData.getDataName());
		messagingGateway.sentToPubsub(messageData.toString());
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
