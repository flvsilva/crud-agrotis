package com.agrotis.testeCrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agrotis.testeCrud.models.Laboratorio;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, Integer>{
	
}
