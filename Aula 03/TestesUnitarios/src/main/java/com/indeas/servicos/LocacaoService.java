package com.indeas.servicos;

import static com.indeas.utils.DataUtils.adicionarDias;

import java.util.Date;

import com.indeas.entidades.Filme;
import com.indeas.entidades.Locacao;
import com.indeas.entidades.Usuario;
import com.indeas.utils.DataUtils;

public class LocacaoService {

	public Locacao alugarFilme(Usuario usuario, Filme filme) {
		Locacao locacao = new Locacao();
		locacao.setFilme(filme);
		locacao.setUsuario(usuario);
		locacao.setDataLocacao(new Date());
		locacao.setValor(filme.getPrecoLocacao());

		// Entrega no dia seguinte
		Date dataEntrega = new Date();
		dataEntrega = adicionarDias(dataEntrega, 1);
		locacao.setDataRetorno(dataEntrega);

		// Salvando a locacao...
		// TODO adicionar mÃ©todo para salvar

		return locacao;
	}

	public static void main(String[] args) {
		LocacaoService ls = new LocacaoService();
		Usuario user = new Usuario();
		Filme film = new Filme();

		user.setNome("Teste");
		film.setEstoque(1);
		film.setNome("As tranças do rei careca");
		film.setPrecoLocacao(10.5);

		Locacao locacao = ls.alugarFilme(user, film);
		System.out.println(locacao.getUsuario().getNome());
		System.out.println(locacao.getFilme().getNome());
		System.out.println(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		System.out.println(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
		System.out.println(locacao.getValor() == 10.5);
	}
}