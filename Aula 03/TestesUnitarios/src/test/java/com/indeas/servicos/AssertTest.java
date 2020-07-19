package com.indeas.servicos;

import org.junit.Assert;
import org.junit.Test;


public class AssertTest {

	@Test
	public void Test() {
		Assert.assertTrue(true);
		Assert.assertFalse(false);

		/*
		 * Podemos inserir um breve descritivo da comparacao em caso de erro
		 */
		Assert.assertEquals("Erro de comparacao", 1, 1);

		/*
		 * Podemos insertir um delimitador a nível de comparacao de casas decimais nesse
		 * caso representado pelo 0.001
		 */
		Assert.assertEquals(0.51234, 0.512, 0.001);
		Assert.assertEquals(Math.PI, 3.14, 0.01);

		int i = 5;
		Integer i2 = 5;
		/*
		 * No caso de comparacao com tipagem raiz, mesmo que aparentemente sejam o mesmo
		 * tipo, se faz necessaria a conversao
		 */
		Assert.assertEquals(Integer.valueOf(i), i2);
		Assert.assertEquals(i, i2.intValue());

		/* Uma observacao, no caso do equals a ordem e o esperado e o atual */
		Assert.assertEquals("bola", "bola");
		Assert.assertNotEquals("bola","casa");
		Assert.assertTrue("bola".equalsIgnoreCase("Bola"));
		Assert.assertTrue("bola".startsWith("bo"));
	}

}
