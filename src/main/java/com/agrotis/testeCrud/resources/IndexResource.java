package com.agrotis.testeCrud.resources;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrotis.testeCrud.models.Pessoa;
import com.agrotis.testeCrud.repositories.PessoaRepository;

@RestController
@RequestMapping(value = "/")
public class IndexResource {
	
	@Autowired
	PessoaRepository pr;
	
	@GetMapping
	public String getIndex() {
		salvaDadosTeste();
		return "API de teste - CRUD - Agrotis";
	}

	private void salvaDadosTeste() {
		pr.save(new Pessoa("Felipe", Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(99999999)), "Usuario cadastrado automaticamente"));
		pr.save(new Pessoa("Renata", Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(99999999)), "Usuario cadastrado automaticamente"));
		pr.save(new Pessoa("Apolo", Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(99999999)), "Usuario cadastrado automaticamente"));
		pr.save(new Pessoa("Nalah", Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(99999999)), "Usuario cadastrado automaticamente"));
		pr.save(new Pessoa("Frajola", Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(99999999)), "Usuario cadastrado automaticamente"));
		pr.save(new Pessoa("Rajada", Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(99999999)), "Usuario cadastrado automaticamente"));
	}

}
