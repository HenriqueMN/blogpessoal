package com.generation.blogpessoal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

/*
 * ----- ANOTAÇÕES DA CLASSE
 * 
 * @RestController: deﬁne que a Classe é do tipo RestController, que receberá requisições que serão compostas por URL(endereço da requisição - endpoint), VERBO (define qual método HTTP será acionado na classe controller) e CORPO DA REQUISIÇÃO (objetos que contém os dados que serão persistidos no banco de dados. Nem toda a requisição enviará dandos no corpo.
 * 
 * @RequestMapping("/xyz"): define a URL padrão do recurso.
 * 
 * @CrossOrigin(origins = "*", allowedHeaders = "*"): indica que o controller permitirá o recebimento de requisições realizadas de fora do domínio (localhost e futuramente da nuvem quando o Deploy da aplicação for efetivado) ao qual ela pertence. Essa anotação é essencial para que o front-end, ou aplicativo mobile, tenha acesso aos Métodos e Recursos da nossa aplicação (o termo técnico é consumir a API).
 * 
 */

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	/*
	 * ----- ANOTAÇÕES DOS ATRIBUBTOS E MÉTODOS
	 * 
	 * @Autowired: injeção de Dependência. É a implementação utilizada pelo Spring Framework para aplicar a Inversão de Controle (IoC) quando for necessário. A Injeção de Dependência define quais Classes serão instanciadas e em quais lugares serão Injetadas quando houver necessidade.
	 * 
	 * @GetMapping: mapeia todas as Requisições HTTP GET, enviadas para um endereço específico, chamado endpoint, dentro do Recurso Postagem, para um Método específico que responderá a requisição, ou seja, ele indica que o Método getAll(), responderá a todas as requisições do tipo HTTP GET, enviadas no endereço.
	 * * O Endereço do endpoint será igual ao Endereço do Recurso (@RequestMapping) apenas quando a anotação @GetMapping não possuir um endereço personalizado, como um parâmetro por exemplo. Caso existam dois ou mais Métodos do tipo GET será necessário personalizar o endereço de cada Método anotado com @GetMapping, pois não pode existir endereços duplicados.
	 * 
	 */
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){ // O Método getAll() será do tipo ResponseEntity porquê ele responderá a Requisição HTTP (HTTP Request), com uma Resposta HTTP (HTTP Response).
		return ResponseEntity.ok(postagemRepository.findAll());
	}
}