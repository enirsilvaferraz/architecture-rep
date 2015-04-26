package com.sgv.model.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.archtecture.model.entities.ModelAb;

@Entity
@Table(name = "TBUSUARIO", schema="sgvdev")
public class UsuarioModel extends ModelAb {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8730866048394302913L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CD_USUARIO")
	private Long codigo;

	@Column(name = "DS_LOGIN")
	private String login;

	@Column(name = "NM_USUARIO")
	private String nome;

	@ManyToMany
	@JoinTable(name = "TBUSUARIO_PAPEL", joinColumns = { @JoinColumn(name = "CD_USUARIO") }, inverseJoinColumns = { @JoinColumn(name = "CD_ROLE") })
	private List<PapelModel> listPapel;

	@Column(name = "HS_SENHA")
	private String senha;

	@Transient
	private String senhaConfirm;

	@Override
	public String[] getAtributosOrdencao() {
		return new String[] { "codigo" };
	}

	@Override
	public Long getCodigo() {
		return codigo;
	}

	public String getLogin() {
		return login;
	}

	public String getNome() {
		return nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<PapelModel> getListPapel() {
		if (listPapel == null) {
			listPapel = new ArrayList<PapelModel>();
		}
		return listPapel;
	}

	public void setListPapel(List<PapelModel> listPapel) {
		this.listPapel = listPapel;
	}

	public String getSenhaConfirm() {
		return senhaConfirm;
	}

	public void setSenhaConfirm(String senhaConfirm) {
		this.senhaConfirm = senhaConfirm;
	}

}