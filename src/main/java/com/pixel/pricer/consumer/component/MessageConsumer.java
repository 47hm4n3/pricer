package com.pixel.pricer.consumer.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.pixel.pricer.model.EuroOption;
import com.pixel.pricer.model.SystemMessage;

@Component
public class MessageConsumer {
	
	private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class);
	
	@JmsListener(destination = "pricer-queue")
	public void messageListener(SystemMessage systemMessage) {
		log.info("Message received. {}", systemMessage);
		log.info((new EuroOption()).toString());
	}

}
