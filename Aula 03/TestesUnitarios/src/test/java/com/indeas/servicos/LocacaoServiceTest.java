package com.indeas.servicos;

import static com.indeas.utils.DataUtils.isMesmaData;
import static com.indeas.utils.DataUtils.obterDataComDiferencaDias;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import com.indeas.entidades.Filme;
import com.indeas.entidades.Locacao;
import com.indeas.entidades.Usuario;

import br.ce.wcaquino.exceptions.FilmeSemEstoqueException;
import br.ce.wcaquino.exceptions.LocadoraException;

import static org.junit.Assert.assertThat;

public class LocacaoServiceTest {	

	private LocacaoService service;
	
	/*
	 * O JUnit sempre reinicializa as variaveis para que se tenha uma garantia
	 * de que os valores de um teste não interfiram no teste seguinte.
	 * Para que possamos utilizar tais valores nos testes seguintes é necessário tornar o valor estático.
	 */
	private static int count = 0;
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	
	/*
	 * Executado antes de cada teste
	 */
	@Before
	public void setup() {
		System.out.println("Before");
		service = new LocacaoService();
		count++;
		System.out.println(count);
	}
	
	/*
	 * Executado após cada teste
	 */
	@After
	public void tearDown() {
		System.out.println("After");
	}
	
	/*
	 * Executado na inicialização da classe
	 */
	@BeforeClass
	public static void setupClass() {
		System.out.println("Before Class");
	}
	
	/*
	 * Executado na finalização da classe
	 */
	@AfterClass
	public static void tearDownClass() {
		System.out.println("After Class");
	}
	
	@Test
	public void testeLocacao() throws Exception {
		//cenario
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 1", 1, 5.0);
		
		System.out.println("Teste");
		
		//acao
		Locacao locacao = service.alugarFilme(usuario, filme);
			
		//verificacao
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(isMesmaData(locacao.getDataRetorno(), obterDataComDiferencaDias(1)), is(true));
	}
	
	@Test(expected = FilmeSemEstoqueException.class)
	public void testLocacao_filmeSemEstoque() throws Exception{
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		Filme filme = new Filme("Filme 2", 0, 4.0);
		
		//acao
		service.alugarFilme(usuario, filme);
	}
	
	@Test
	public void testLocacao_usuarioVazio() throws FilmeSemEstoqueException{
		//cenario
		LocacaoService service = new LocacaoService();
		Filme filme = new Filme("Filme 2", 1, 4.0);
		
		//acao
		try {
			service.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}
	

	@Test
	public void testLocacao_FilmeVazio() throws FilmeSemEstoqueException, LocadoraException{
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		//acao
		service.alugarFilme(usuario, null);
	}

}
