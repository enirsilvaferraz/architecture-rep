package com.sgv.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.archtecture.control.models.ModelAb;

@Entity
@Table(name = "TBFORNECEDOR")
public class FornecedorModel extends ModelAb {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1491334691805906025L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_FORNECEDOR", length = 11, nullable = false, unique = true)
	private Long codigo;

	@Column(name = "NM_FORNECEDOR", length = 100, nullable = false)
	private String nome;

	@Column(name = "NR_TELEFONE", length = 20)
	private String telefone;

	@Column(name = "EMAIL", length = 150)
	private String email;

	@Column(name = "ENDERECO", length = 400)
	private String endereco;

	@Override
	public String[] getAtributosOrdencao() {
		return new String[] { "codigo" };
	}

	@Override
	public Long getCodigo() {
		return codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

}
