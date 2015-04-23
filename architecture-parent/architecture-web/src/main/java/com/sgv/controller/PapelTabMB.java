package com.sgv.controller;

import com.sgv.model.entities.PapelModel;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.gerador.auxiliar.PadraoMBImpl;

@ViewScoped
@ManagedBean
public class PapelTabMB extends PadraoMBImpl<PapelModel> {

	@PostConstruct
	public void executarIniciar(){
		executarPesquisar();
	}


}