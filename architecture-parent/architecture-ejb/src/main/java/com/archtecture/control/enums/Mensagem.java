package com.archtecture.control.enums;

public enum Mensagem {

	ERRO_METODO_NAO_IMPLEMENTADO, // Erro de Ambiente: Pausa Inexperada! Funcionalidade não implementada.
	ERRO_MD5, // Erro de Ambiente: Erro ao realizar criptografia. Modelo de criptografia inexistente.
	ERRO_OBJETO_NULO, // Erro de Ambiente: O objeto não pode estar nulo
	ERRO_ENTIDADE_SEM_CODIGO, // Erro de Ambiente: A entidade selecionada não possui um código de identificação.
	ERRO_ENTIDADE_COM_CODIGO, // Erro de Ambiente: A entidade selecionada possui um código de identificação.
	ERRO_MULTIPLOS_RESULTADOS_NA_PESQUISA_UNICA, // Erro de Ambiente: Foram retornados multiplos resultados na pesquisa de resultado único

	ERRO_OBJETO_NAO_E_ENTIDADE,

	ERRO_PERSISTENCE,
	ERRO_ID_SESSAO_JA_UTILIZADO,
	ERRO_ID_SESSAO_NAO_ENCONTRADO,

	// Negocio Exception
	ERRO_USUARIO_SENHA_INCORRETOS,
	ERRO_PESQUISA_VAZIA,
	ERRO_AMBIENTE,
	ERRO_VIOLACAO_BANCO,
	ERRO_CONFIRMACAO_SENHA,
	ERRO_ENTIDADE_NAO_ENCONTRADA,// Erro Negocio

	// Mensagens
	SUCESSO_SALVAR,
	SUCESSO_EDITAR,
	SUCESSO_DELETAR, WARN_VENDA_CAIXA_NAO_CADASTRADO, 
	
	// MYSQL
	FATAL_ERRO_CADASTRO,
	ERROR_ERRO_CADASTRO_REGISTRO_DUPLICADO,
	ERROR_ERRO_EXCLUSAO_NEGADA,
	
	ERRO_FECHAR_CAIXA_VENDA_ABERTA
}
