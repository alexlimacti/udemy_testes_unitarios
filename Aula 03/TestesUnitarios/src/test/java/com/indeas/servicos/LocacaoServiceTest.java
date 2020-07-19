package com.indeas.servicos;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.indeas.entidades.Filme;
import com.indeas.entidades.Locacao;
import com.indeas.entidades.Usuario;
import com.indeas.servicos.LocacaoService;
import com.indeas.utils.DataUtils;

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
		
		Assert.assertEquals(9.5, locacao.getValor(), 0.01);
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()));
		Assert.assertTrue(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)));
		
	}

}
