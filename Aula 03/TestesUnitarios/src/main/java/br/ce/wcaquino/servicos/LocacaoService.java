package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.utils.DataUtils.adicionarDias;

import java.util.Date;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.utils.DataUtils;

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
		// TODO adicionar método para salvar

		return locacao;
	}

	public static void main(String[] args) {
		LocacaoService ls = new LocacaoService();
		Usuario user = new Usuario();
		Filme film = new Filme();

		user.setNome("Teste");
		film.setEstoque(1);
		film.setNome("As tran�as do rei careca");
		film.setPrecoLocacao(10.5);

		Locacao locacao = ls.alugarFilme(user, film);
		System.out.println(locacao.getUsuario().getNome());
		System.out.println(locacao.getFilme().getNome());
		System.out.println(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		System.out.println(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
		System.out.println(locacao.getValor() == 10.5);
	}
}