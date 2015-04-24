package com.sgv.controller;

import com.sgv.model.entities.ProdutoModel;
import java.util.List;
import com.sgv.model.entities.CategoriaModel;


import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.gerador.auxiliar.PadraoMBImpl;

@ViewScoped
@ManagedBean
public class ProdutoTabMB extends PadraoMBImpl<ProdutoModel> {

	@PostConstruct
	public void executarIniciar(){
		executarPesquisar();
	}

	@SuppressWarnings("unchecked")
	public List<CategoriaModel> getListCategoriaModel(){
		return (List<CategoriaModel>) montarCombo(new CategoriaModel(), null);
	}


}