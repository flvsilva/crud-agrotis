package com.agrotis.testeCrud.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrotis.testeCrud.repositories.PessoaRepository;

@RestController
@RequestMapping(value = "/")
public class IndexResource {
	
	@Autowired
	PessoaRepository pr;
	
	@GetMapping
	public String getIndex() {
		return "API de teste - CRUD - Agrotis";
	}
	
}
