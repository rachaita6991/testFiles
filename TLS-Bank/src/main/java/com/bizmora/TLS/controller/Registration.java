package com.bizmora.TLS.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.InputSource;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class Registration<Customer> {
	@PostMapping("/registrationRequest")
	public String register(@RequestBody String cust_bank) {
		Document cust_bank_XML;
		cust_bank_XML = null;
		try {
			cust_bank_XML = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new InputSource(new StringReader(cust_bank)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		cust_bank_XML.getElementsByTagName("bankOutput").item(0).setTextContent("Register within Bank");
		try {
			DOMSource domSource = new DOMSource(cust_bank_XML);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);
			return writer.toString();
		} catch (TransformerException ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
