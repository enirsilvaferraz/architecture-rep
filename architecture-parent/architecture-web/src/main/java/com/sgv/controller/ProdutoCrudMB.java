package com.sgv.controller;

import com.sgv.model.entities.ProdutoModel;
import java.util.List;
import com.sgv.model.entities.CategoriaModel;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.gerador.auxiliar.PadraoMBImpl;

@ViewScoped
@ManagedBean
public class ProdutoCrudMB extends PadraoMBImpl<ProdutoModel> {

	@SuppressWarnings("unchecked")
	public List<CategoriaModel> getListCategoriaModel(){
		return (List<CategoriaModel>) montarCombo(new CategoriaModel(), null);
	}


}