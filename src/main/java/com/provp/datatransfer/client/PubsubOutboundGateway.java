package com.provp.datatransfer.client;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "pubsubOutputChannel")
public interface PubsubOutboundGateway {
	void sentToPubsub(String text);
	
}
