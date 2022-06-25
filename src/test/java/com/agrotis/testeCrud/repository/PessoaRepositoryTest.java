package com.agrotis.testeCrud.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Instant;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.agrotis.testeCrud.models.Pessoa;
import com.agrotis.testeCrud.repositories.PessoaRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PessoaRepositoryTest {

	@Autowired
    private PessoaRepository pessoaRepository;

    @Test
    public void save(){
        Pessoa Pessoa = new Pessoa("Felipe", Date.from(Instant.now()), Date.from(Instant.now().plusSeconds(99999999)), "Pessoa com pedidos recorrentes");
        assertThat(pessoaRepository.findAll().size()).isEqualTo(0);
        
        
        pessoaRepository.save(Pessoa);
        assertThat(Pessoa.getId()).isNotNull();
        assertThat(Pessoa.getNome()).isEqualTo("Felipe");
        assertThat(pessoaRepository.findAll().size()).isEqualTo(1);
    }

}
