package masi_green.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping(value = "/api/hello")
	public String hello() {
		final String hello = "Hello!";
		return hello;
	}
}
