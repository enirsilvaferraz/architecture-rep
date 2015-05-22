package com.exemplo.controller;

import java.util.Date;
import java.util.List;

import com.archtecture.model.entities.ModelAb;

public class PessoaModel extends ModelAb {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long codigo;

	private String nome;

	private String login;

	private List<String> email;

	private TipoPessoa tipo;

	private Double salario;

	private Long idade;

	private Date dia;

	public PessoaModel() {
	}

	public PessoaModel(Integer id, String nome, String login, List<String> email) {
		super();
		this.codigo = id;
		this.nome = nome;
		this.login = login;
		this.email = email;
		this.tipo = TipoPessoa.PF;
		salario = 10.55;
		idade = 50L;
		dia = new Date();
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public TipoPessoa getTipo() {
		return tipo;
	}

	public void setTipo(TipoPessoa tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<String> getEmail() {
		return email;
	}

	public void setEmail(List<String> email) {
		this.email = email;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Long getIdade() {
		return idade;
	}

	public void setIdade(Long idade) {
		this.idade = idade;
	}

	@Override
	public String[] getAtributosOrdencao() {
		return null;
	}

	@Override
	public Long getCodigo() {
		return codigo;
	}

	@Override
	public void setCodigo(Long id) {
		this.codigo = codigo;
	}
}
