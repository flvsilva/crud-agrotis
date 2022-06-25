package com.agrotis.testeCrud.resources;

import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrotis.testeCrud.TesteCrudApplication;
import com.agrotis.testeCrud.models.Pessoa;
import com.agrotis.testeCrud.repositories.PessoaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping(path="/Pessoas")
public class PessoaResource {

	private static Logger logger = LoggerFactory.getLogger(TesteCrudApplication.class);

	@Autowired
	private PessoaRepository pessoaRepository;

	@ApiOperation("Consulta todas as pessoas cadastradas.")
	@GetMapping(path="/retornaTodos")
	public ResponseEntity<List<Pessoa>> getTodos(){
		try {
			List<Pessoa> cadastrados = pessoaRepository.findAll();
			return new ResponseEntity<List<Pessoa>>(cadastrados, HttpStatus.OK);
		}catch (NoSuchElementException nsee) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation("Consulta pessoa pelo nome.")
	@GetMapping(path="/buscaPorNome/{nome}")
	public ResponseEntity<List<Pessoa>> buscaPorNome(@PathVariable String nome){
		List<Pessoa> pessoa = pessoaRepository.findByNome(nome);
		
		if (pessoa.size() > 0) {
			logger.info("Pessoa com nome: " + nome + " encontrada");			
			return new ResponseEntity<List<Pessoa>>(pessoa, HttpStatus.OK);
		} 

		logger.info("Pessoa com nome: " + nome + " não existe.");
		return new ResponseEntity<List<Pessoa>>(pessoa,HttpStatus.NOT_FOUND);
		
	}
	
	
	@ApiOperation("Remove pessoa pelo nome.")
	@GetMapping(path="/Remover/{nome}")	
	public ResponseEntity<String> removePorNome(@PathVariable String nome){
		try {
			//TODO tomei como verdade que podemos ter mais de um registro com o mesmo nome, o primeiro é removido, para simplificar a solução.
			pessoaRepository.delete(pessoaRepository.findByNome(nome).get(0));
			logger.info("Pessoa com nome " + nome + " removida");
			return new ResponseEntity<String>("Pessoa com nome " + nome + " removida", HttpStatus.OK);
		}catch (IndexOutOfBoundsException noResult) {
			logger.info("Pessoa com nome " + nome + " não encontrada");
			return new ResponseEntity<String>("Pessoa com nome " + nome + " não encontrada.",HttpStatus.NOT_FOUND);
		}
	}
	
	

	/*
	 *TODO: Insert, Update  	 
	 * 
	 * 
	 */
	 
}
