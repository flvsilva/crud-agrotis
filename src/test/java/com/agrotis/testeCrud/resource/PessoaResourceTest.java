package com.agrotis.testeCrud.resource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.agrotis.testeCrud.models.Pessoa;
import com.agrotis.testeCrud.repositories.PessoaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
public class PessoaResourceTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
    ObjectMapper mapper;
	
	@MockBean
	private PessoaRepository pessoaRepository;
	

	Pessoa p1 = new Pessoa("Felipe", Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(99999999)), "Usuario de teste");
	Pessoa p2 = new Pessoa("Renata", Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(99999999)), "Usuario de teste");
	
	@Test
	public void listPessoas() throws Exception{
		List<Pessoa> cadastros = new ArrayList<Pessoa>();
		cadastros.add(p1);
		cadastros.add(p2);
		pessoaRepository.saveAll(cadastros);
		
		Mockito.when(pessoaRepository.findAll()).thenReturn(cadastros);
		
		
		mockMvc.perform(MockMvcRequestBuilders
		        .get("/pessoas/retornaTodos")
		        .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$", hasSize(2)))
		        .andExpect(jsonPath("$[1].nome", is("Renata")));
	}
	
	@Test
	public void removePessoaPorNome_ok() throws Exception {
		
		ArrayList<Pessoa> pp = new ArrayList<Pessoa>();
		pp.add(p2);
		
		Mockito.when(pessoaRepository.findByNome(p2.getNome())).thenReturn(pp);

	    mockMvc.perform(MockMvcRequestBuilders
	            .get("/pessoas/remover/Renata")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}

	@Test
	public void removePessoaPorNome_notFound() throws Exception {
		
		Mockito.when(pessoaRepository.findByNome(p2.getNome())).thenReturn(null);

	    mockMvc.perform(MockMvcRequestBuilders
	            .get("/pessoas/remover/Apolo")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isBadRequest());
	}
	
	@Test
	public void cadastrarPessoa() throws Exception {
	    Pessoa p = new Pessoa("John Rambo", Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(99999999)), "Cadastrado via teste unitário");

	    Mockito.when(pessoaRepository.save(p)).thenReturn(p);

	    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/pessoas/cadastro")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(this.mapper.writeValueAsString(p));

	    mockMvc.perform(mockRequest)
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$", notNullValue()));
	    }
	
	

}
