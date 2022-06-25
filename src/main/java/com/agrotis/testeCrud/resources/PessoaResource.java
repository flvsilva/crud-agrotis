package com.agrotis.testeCrud.resources;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
	@GetMapping(path="/{nome}")
	public ResponseEntity<Optional<Pessoa>> getByNome(@PathVariable String nome){
		Optional<Pessoa> Pessoa;
		try {
			Pessoa = Optional.ofNullable(pessoaRepository.getByNome(nome));
			logger.info("Pessoa com nome: " + nome + " encontrada");
			return new ResponseEntity<Optional<Pessoa>>(Pessoa, HttpStatus.OK);
		}catch (NoSuchElementException nsee) {
			logger.info("Pessoa com nome: " + nome + " não encontrada");
			return new ResponseEntity<Optional<Pessoa>>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@ApiOperation("Remove pessoa pelo nome.")
	@GetMapping(path="/Remover/{nome}")
	public ResponseEntity<String> removePorNome(@PathVariable String nome){
		Pessoa pessoa;
		try {
			System.out.println("começou");
			pessoa = pessoaRepository.getByNome(nome);
			logger.info("Pessoa com nome: " + nome + " encontrada");
			pessoaRepository.delete(pessoa);
			logger.info("Pessoa com nome: " + nome + " removida");
			return new ResponseEntity(HttpStatus.OK);
		}catch (Exception nsee) {
			logger.info("Pessoa com nome: " + nome + " não encontrada");
			return new ResponseEntity("Pessoa com nome: " + nome + " não encontrada.",HttpStatus.OK);
		}
	}

	/*
	 *TODO: Insert, Update / Dados de terreno/laboratorio 	 
	 * 
	 * 
	 */
	 
}
