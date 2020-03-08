package com.bizmora.webserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MessageRouting_Controller implements CommandLineRunner {
 
 @Autowired
 private RestTemplate template;
 
 @Override
 public void run(String... args) throws Exception {
 ResponseEntity<String> response = template.getForEntity("http://localhost:8081/mobile",
 String.class);
 System.out.println(response.getBody());
 }
}


