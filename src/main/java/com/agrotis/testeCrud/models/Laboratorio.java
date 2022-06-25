package com.agrotis.testeCrud.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Laboratorio {
	
	@GeneratedValue
	@Id
	private int id;

	@Column(name = "nome")
	private String nome;
		
	public Laboratorio() {
	}
	
	public Laboratorio(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
