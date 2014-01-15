package br.com.ninb.moper.app;


import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.ninb.moper.model.Test;
import br.com.ninb.moper.service.TestService;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Test t = new Test();
		t.setMenssagem("Lixo");
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/application-context.xml");
		TestService testService = context.getBean(TestService.class);
		testService.save(t);
		
		List<Test> list = testService.list();
		for (Test instance: list){
			System.out.println( "PASSEI AQUI:" + instance.getMenssagem());
		}

	}

}
