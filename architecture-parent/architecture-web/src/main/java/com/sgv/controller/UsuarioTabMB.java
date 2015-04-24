package com.sgv.controller;

import com.sgv.model.entities.UsuarioModel;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.gerador.auxiliar.PadraoMBImpl;

@ViewScoped
@ManagedBean
public class UsuarioTabMB extends PadraoMBImpl<UsuarioModel> {

	@PostConstruct
	public void executarIniciar() {
		executarPesquisar();
	}

}