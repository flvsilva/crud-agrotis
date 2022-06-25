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

import com.agrotis.testeCrud.models.Propriedade;
import com.agrotis.testeCrud.repositories.PropriedadeRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping(path="/propriedades")
public class PropriedadeResource {

	@Autowired
	private PropriedadeRepository propriedadeRepository;

	@ApiOperation("Consulta todos as Propriedades cadastradas.")
	@GetMapping(path="/retornaTodas")
	public ResponseEntity<List<Propriedade>> getTodas(){
		List<Propriedade> prpCadastradas = propriedadeRepository.findAll();
		return new ResponseEntity<List<Propriedade>>(prpCadastradas, HttpStatus.OK);
	}
		
	@ApiOperation("Adiciona um novo Propriedade.") 
	@PostMapping(path="/cadastro")	
	public ResponseEntity<String> cadastraPropriedade (@RequestBody Propriedade prp) {
		propriedadeRepository.save(prp);
		//FIXME Não foi tratado o caso do cadastro vir "com problema"
		return new ResponseEntity<String>("Propriedade cadastrada com sucesso.", HttpStatus.OK);
	}
	
	
}
