package com.sgv.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.gerador.auxiliar.PadraoMBImpl;
import com.sgv.model.entities.UsuarioModel;

@ViewScoped
@ManagedBean
public class UsuarioTabMB extends PadraoMBImpl<UsuarioModel> {

	@PostConstruct
	public void executarIniciar() {
		executarPesquisar();
	}

}