package com.provp.datatransfer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;


@SpringBootApplication
public class DataTransferTransformApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataTransferTransformApiApplication.class, args);
	}

	@Value("${pubsub.topic}")
	private String topicName;
		
	  @Bean
	  @ServiceActivator(inputChannel = "pubsubOutputChannel")
	  public MessageHandler messageSender(PubSubTemplate pubsubTemplate) {
	    return new PubSubMessageHandler(pubsubTemplate, topicName);
	  }

}
