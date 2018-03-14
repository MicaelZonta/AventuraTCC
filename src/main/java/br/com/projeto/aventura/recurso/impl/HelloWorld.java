package br.com.projeto.aventura.recurso.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorld {

	@RequestMapping(method=RequestMethod.GET)
	public String helloWorld(@RequestParam(value="nome", defaultValue="Mundo")String nome) {
		return String.format("Olá %s!!!!", nome);
	}
}
