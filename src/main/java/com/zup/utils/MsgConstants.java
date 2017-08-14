package com.zup.utils;

public class MsgConstants {
	
	public static final String IDENTIFICADOR_NAO_PODE_SER_NULO = "Identificador não pode ser nulo.";
	public static final String OBJETO_NAO_ENCONTRADO = "Objeto não foi encontrado";
	public static final String OBJETO_OBRIGATORIO_NAO_INFORMADO = "Objeto obrigatório não informado";
	public static final String PARAMETRO_OBRIGATORIO_NAO_INFORMADO = "Parâmetro obrigatório não informado";
	public static final String EXISTE_DADOS_DIVERGENTES_NA_ATUALIZACAO = "Existem dados divergentes na atualização do objeto";
	
	//PRODUTO
	public static final String PROPRIEDADE_PRODUTO_NAO_PODE_SER_NULO = "A propriedade produto é obrigatório";
	public static final String PROPRIEDADE_QUANTIDADE_NAO_PODE_SER_NULO = "A propriedade quantidade é obrigatório";
	public static final String ERRO_QUANTIDADE_MINIMA = "A quantidade deve ser maior que zero";
	public static final String PRODUTO_SEM_ESTOQUE = "Produto indisponível na quantidade informada";
	
	//CLIENTE
	public static final String PROPRIEDADE_NOME_NAO_PODE_SER_NULO = "A propriedade nome é obrigatório";
	public static final String PROPRIEDADE_CPF_NAO_PODE_SER_NULO = "A propriedade CPF é obrigatório";
	public static final String CPF_INVALIDO = "CPF Inválido";
	
	//PEDIDO
	public static final String DATA_PEDIDO_NAO_PODE_SER_NULO = "A propriedade data do pedido é obrigatória";
	public static final String STATUS_NAO_PODE_SER_NULO = "A propriedade status do pedido é obrigatória";
	public static final String CLIENTE_NAO_PODE_SER_NULO = "A propriedade cliente do pedido é obrigatória";
	public static final String ITENS_NAO_PODE_SER_NULO = "A propriedade itens do pedido é obrigatória";
	public static final String TOTAL_DOS_ITENS_DIVERGENTE = "O valor total dos itens está divergentes com o valor total do pedido";
	
}
