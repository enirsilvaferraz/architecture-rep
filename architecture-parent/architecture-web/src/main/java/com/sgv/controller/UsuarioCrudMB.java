package com.sgv.controller;

import com.sgv.model.entities.UsuarioModel;
import java.util.List;
import com.sgv.model.entities.PapelModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.gerador.auxiliar.PadraoMBImpl;

@ViewScoped
@ManagedBean
public class UsuarioCrudMB extends PadraoMBImpl<UsuarioModel> {

	private List<PapelModel> listPapelModel;

	@SuppressWarnings("unchecked")
	public List<PapelModel> getListPapelModel() {
		if (listPapelModel == null) {
			listPapelModel = (List<PapelModel>) montarCombo(new PapelModel(), null);
		}
		return listPapelModel;
	}

}