package com.agrotis.testeCrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotis.testeCrud.models.Propriedade;

@Repository
public interface PropriedadeRepository extends JpaRepository<Propriedade, Integer>{
	
}
