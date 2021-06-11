package com.pixel.pricer.publisher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pixel.pricer.model.SystemMessage;

@RestController
public class PublishController {

	@Autowired
	private JmsTemplate jmsTemplate;
	
	@PostMapping("/publishMessage")
	public ResponseEntity<String> publishMessage(@RequestBody SystemMessage systemMessage) {
		
		try {
			jmsTemplate.convertAndSend("pricer-queue", systemMessage);
			
			return new ResponseEntity<String>("Sent", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Sent", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
}
