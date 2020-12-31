package com.indeas.servicos.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.indeas.servicos.servicos.CalculadoraTest;
import com.indeas.servicos.servicos.CalculoValorLocacaoTest;
import com.indeas.servicos.servicos.LocacaoServiceTest;

//@RunWith(Suite.class)
@SuiteClasses({
	CalculadoraTest.class,
	CalculoValorLocacaoTest.class,
	LocacaoServiceTest.class
})
public class SuiteExecucao {
	//Remova se puder!
}
