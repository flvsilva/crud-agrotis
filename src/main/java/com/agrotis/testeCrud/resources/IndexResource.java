package com.agrotis.testeCrud.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class IndexResource {
	
	@GetMapping
	public String getIndex() {
		return "API de teste - CRUD - Agrotis";
	}

}
