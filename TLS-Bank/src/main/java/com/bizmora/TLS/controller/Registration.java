package com.bizmora.TLS.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registrationRequest")
public class Registration {
	@GetMapping
	public String register() {
		return "Registration";
	}
	

}
