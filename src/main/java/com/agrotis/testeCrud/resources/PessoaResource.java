package com.agrotis.testeCrud.resources;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrotis.testeCrud.TesteCrudApplication;
import com.agrotis.testeCrud.models.Pessoa;
import com.agrotis.testeCrud.repositories.PessoaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api
@RestController
@RequestMapping(path="/pessoas")
@Service
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
			return new ResponseEntity<List<Pessoa>>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation("Consulta cadastro pelo nome.")
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
	
	@ApiOperation("Adiciona um novo cadastro.") 
	@PostMapping(path="/cadastro")	
	public ResponseEntity<String> cadastraPessoa (@RequestBody Pessoa p) {
		pessoaRepository.save(p);
		//FIXME Não foi tratado o caso do cadastro vir "com problema"
		return new ResponseEntity<String>("Pessoa com nome " + p.getNome() + " cadastrada com sucesso.", HttpStatus.OK);
	}
	
	@ApiOperation("Remove cadastro pelo nome.") 
	@GetMapping(path="/remover/{nome}")	
	public ResponseEntity<String> removePorNome(@PathVariable String nome){
		try {
			//FIXME tomei como verdade que podemos ter mais de um registro com o mesmo nome, o primeiro é removido, para simplificar a solução.
			//FIXME afim de simplificar, inclusive, os nomes são considerados por completo, i.e, Felipe <> Felipe Luiz.
			pessoaRepository.delete(pessoaRepository.findByNome(nome).get(0));
			logger.info("Pessoa com nome " + nome + " removida");
			return new ResponseEntity<String>("Pessoa com nome " + nome + " removida", HttpStatus.OK);
		}catch (IndexOutOfBoundsException noResult) {
			logger.info("Pessoa com nome " + nome + " não encontrada");
			return new ResponseEntity<String>("Pessoa com nome " + nome + " não encontrada.",HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation("Atualiza um cadastro existente.") 
	@PostMapping(path="/cadastro/atualizar")	
	public ResponseEntity<String> atualizaCadastro (@RequestBody Pessoa paramPessoa) {
		Pessoa pExistente = pessoaRepository.findByNome(paramPessoa.getNome()).get(0);
		pExistente.setDataFinal(paramPessoa.getDataFinal());
		pExistente.setDataInicial(paramPessoa.getDataInicial());
		pExistente.setObservacao(paramPessoa.getObservacao());
		pExistente.setLaboratorio(paramPessoa.getLaboratorio());
		pExistente.setInfosPropriedade(paramPessoa.getInfosPropriedade());
		
		pessoaRepository.save(pExistente);
		//FIXME Não foi tratado o caso da atualização de cadastro vir "com problema"
		return new ResponseEntity<String>("Cadastro atualizado com sucesso.", HttpStatus.OK);
	}

	@ApiOperation("Faz uma carga inicial de cadastros de pessoa.") 
	@GetMapping(path="/cadastro/cargaInicial")	
	public ResponseEntity<String> cargaInicial(){
		salvaDadosTeste();
		return new ResponseEntity<String>("Carga inicial de cadastros feita com sucesso.", HttpStatus.OK);
	}

	private void salvaDadosTeste() {
		pessoaRepository.save(new Pessoa("Felipe", Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(99999999)), "Usuario cadastrado automaticamente"));
		pessoaRepository.save(new Pessoa("Renata", Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(99999999)), "Usuario cadastrado automaticamente"));
		pessoaRepository.save(new Pessoa("Apolo", Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(99999999)), "Usuario cadastrado automaticamente"));
		pessoaRepository.save(new Pessoa("Nalah", Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(99999999)), "Usuario cadastrado automaticamente"));
		pessoaRepository.save(new Pessoa("Frajola", Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(99999999)), "Usuario cadastrado automaticamente"));
		pessoaRepository.save(new Pessoa("Rajada", Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(99999999)), "Usuario cadastrado automaticamente"));
	}


}
