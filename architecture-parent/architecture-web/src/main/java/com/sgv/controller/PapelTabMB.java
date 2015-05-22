package com.sgv.controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.gerador.auxiliar.PadraoMBImpl;
import com.sgv.model.entities.PapelModel;

@ViewScoped
@ManagedBean
public class PapelTabMB extends PadraoMBImpl<PapelModel> {

	@PostConstruct
	public void executarIniciar(){
		executarPesquisar();
	}


}