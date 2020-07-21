package com.indeas.servicos;

import static com.indeas.utils.DataUtils.isMesmaData;
import static com.indeas.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.indeas.entidades.Filme;
import com.indeas.entidades.Locacao;
import com.indeas.entidades.Usuario;

public class LocacaoServiceTest {
	
	@Test
	public void testes() {
		LocacaoService ls = new LocacaoService();
		Usuario user = new Usuario();
		Filme film = new Filme();

		user.setNome("Teste");
		film.setEstoque(1);
		film.setNome("As tranças do rei careca");
		film.setPrecoLocacao(10.5);
		
		Locacao locacao = ls.alugarFilme(user, film);
		
		assertThat(locacao.getValor(), is(CoreMatchers.equalTo(10.5)));
		assertThat(locacao.getValor(), is(CoreMatchers.not(6.0)));
		assertThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		assertThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
		
	}

}
