package com.exemplo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.gerador.auxiliar.PadraoMBImpl;

@ManagedBean
public class PessoaMB2 extends PadraoMBImpl<PessoaModel> {

	@Override
	public List<PessoaModel> getListModel() {

		List<PessoaModel> list = new ArrayList<PessoaModel>();
		list.add(new PessoaModel(1, "Nome", "Login", Arrays.asList(new String[] { "email@email.com" })));
		list.add(new PessoaModel(2, "Nome", "Login", Arrays.asList(new String[] { "email@email.com" })));
		list.add(new PessoaModel(3, "Nome", "Login", Arrays.asList(new String[] { "email@email.com" })));
		list.add(new PessoaModel(4, "Nome", "Login", Arrays.asList(new String[] { "email@email.com" })));
		list.add(new PessoaModel(5, "Nome", "Login", Arrays.asList(new String[] { "email@email.com" })));
		list.add(new PessoaModel(6, "Nome", "Login", Arrays.asList(new String[] { "email@email.com" })));

		return list;
	}

	@Override
	protected PessoaModel getInstance() {
		return new PessoaModel();
	}

	public List<TipoPessoa> getListTipoPessoa(){
		return Arrays.asList(TipoPessoa.values());
	}
}
