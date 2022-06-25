package com.agrotis.testeCrud.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pessoa {
	
	@GeneratedValue
	@Id
	private int id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "dataInicial")
	private Date dataInicial;
	
	@Column(name = "dataFinal")
	private Date dataFinal;
	
	@ManyToOne
	private Propriedade	infosPropriedade;
	
	@ManyToOne
	private Laboratorio laboratorio;
	
	@Column
	private String observacao;
	
	public Pessoa(String nome, Date dataInicial, Date dataFinal, String observacao) {
		super();
		this.nome = nome;
		this.dataInicial = dataInicial;
		this.dataFinal = dataFinal;
		this.observacao = observacao;
	}
	
	public Pessoa() {
	
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
	public Date getDataInicial() {
		return dataInicial;
	}
	public void setDataInicial(Date dataInicial) {
		this.dataInicial = dataInicial;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	public Propriedade getPropriedade() {
		return infosPropriedade;
	}
	public void setPropriedade(Propriedade propriedade) {
		this.infosPropriedade = propriedade;
	}
	public Laboratorio getLaboratorio() {
		return laboratorio;
	}
	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	
	
	
}
