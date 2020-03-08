package com.bizmora.WebServer.controller;

import java.io.StringReader;
import java.io.StringWriter;

/*
 * import javax.servlet.http.HttpServletRequest;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.CommandLineRunner; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.stereotype.Component; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.RestController; import
 * org.springframework.web.client.RestTemplate;
 */

import java.net.URI;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.springframework.web.bind.annotation.PostMapping;
import com.bizmora.WebServer.model.Customer;

@RestController
@RequestMapping(value = "/mobile", method = RequestMethod.POST)
public class MessageRouting_Controller {
	String bank_output;
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	Environment env;

	@PostMapping("/webserver")
	public Customer customerInformation(@RequestBody Customer cust) {
		cust.setCountry("Britian");
		return cust;
	}

	@PostMapping("/bank")
	public String bankInformation(@RequestBody Customer cust) {
		try {
			String msEndpoint = env.getProperty("endpoint.ms-service");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_XML);
			Customer cust_new = new Customer(100, "Adam", "Gilly", "Register");
			String bank_response = restTemplate.postForObject(new URI(msEndpoint), cust_new, String.class);
			Document bank_response_XML = null;
			bank_response_XML = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new InputSource(new StringReader(bank_response)));
			bank_response_XML.getElementsByTagName("country").item(0).setTextContent("India");
			DOMSource domSource = new DOMSource(bank_response_XML);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			return writer.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "Exception occurred.Returning default data";
	}
}
