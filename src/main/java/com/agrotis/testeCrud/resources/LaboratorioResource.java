package com.agrotis.testeCrud.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrotis.testeCrud.models.Laboratorio;
import com.agrotis.testeCrud.repositories.LaboratorioRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping(path="/laboratorios")
public class LaboratorioResource {

	@Autowired
	private LaboratorioRepository laboratorioRepository;

	@ApiOperation("Consulta todos os laboratorios cadastrados.")
	@GetMapping(path="/retornaTodos")
	public ResponseEntity<List<Laboratorio>> getTodos(){
		List<Laboratorio> labCadastrados = laboratorioRepository.findAll();
		return new ResponseEntity<List<Laboratorio>>(labCadastrados, HttpStatus.OK);
	}
		
	@ApiOperation("Adiciona um novo laboratorio.") 
	@PostMapping(path="/cadastro")	
	public ResponseEntity<String> cadastraPessoa (@RequestBody Laboratorio lab) {
		laboratorioRepository.save(lab);
		//FIXME Não foi tratado o caso do cadastro vir "com problema"
		return new ResponseEntity<String>("Laboratorio cadastrado com sucesso.", HttpStatus.OK);
	}
	
	
}
