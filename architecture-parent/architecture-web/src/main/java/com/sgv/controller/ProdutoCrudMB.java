package com.sgv.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.gerador.auxiliar.PadraoMBImpl;
import com.sgv.model.entities.CategoriaModel;
import com.sgv.model.entities.ProdutoModel;

@ViewScoped
@ManagedBean
public class ProdutoCrudMB extends PadraoMBImpl<ProdutoModel> {

	@SuppressWarnings("unchecked")
	public List<CategoriaModel> getListCategoriaModel(){
		return (List<CategoriaModel>) montarCombo(new CategoriaModel(), null);
	}


}