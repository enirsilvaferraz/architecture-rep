package com.architecture.security.model.entities;

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

import lombok.Getter;
import lombok.Setter;

import com.architecture.persistence.model.entities.ModelAb;

@Entity
@Table(name = "TBUSUARIO", schema="sgvdev")
@Getter @Setter
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
	@JoinTable(name = "TBUSUARIO_PAPEL", 
		joinColumns = { @JoinColumn(name = "CD_USUARIO") }, 
		inverseJoinColumns = { @JoinColumn(name = "CD_ROLE") })
	private List<PapelModel> listPapel;

	@Column(name = "HS_SENHA")
	private String senha;

	@Transient
	private String senhaConfirm;

}