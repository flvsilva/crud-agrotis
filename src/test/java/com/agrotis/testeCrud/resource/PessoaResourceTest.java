package com.agrotis.testeCrud.resource;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.agrotis.testeCrud.models.Pessoa;
import com.agrotis.testeCrud.repositories.PessoaRepository;

@RunWith( SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PessoaResourceTest {
	
    @Autowired
    private TestRestTemplate testTemplate;

    @MockBean
    private PessoaRepository pessoaRepository;

    @TestConfiguration
    static class Config{
        @Bean
        public RestTemplateBuilder restTemplateBuilder(){
            return new RestTemplateBuilder().basicAuthentication("", "");
        }
    }

   @Test
   public void listPessoas(){
       Pessoa Pessoa = new Pessoa("Felipe", Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(99999999)), "Usuario cadastrado automaticamente");
       BDDMockito.when(pessoaRepository.findAll()).thenReturn(Collections.singletonList(Pessoa));
       testTemplate = testTemplate.withBasicAuth("1", "1");
       ResponseEntity<String> response = testTemplate.getForEntity("/Pessoas", String.class);
       Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
   }

   @Test
   public void getHello(){
       testTemplate = testTemplate.withBasicAuth("1", "1");
       ResponseEntity<String> response = testTemplate.getForEntity("/", String.class);
       Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
       Assertions.assertThat(response.getBody()).isEqualTo("API de teste - CRUD - Agrotis");
   }

}
