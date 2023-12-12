package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;
import com.generation.blogpessoal.repository.TemaRepository;

import jakarta.validation.Valid;

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
	 * @PathVariable: informa que a variável usada será especificada no path da URL
	 * 
	 * @Valid: verifica se a requisição está de acordo com as regras definidas na model
	 */
	
	@Autowired
	private PostagemRepository postagemRepository;
	
	@Autowired
	private TemaRepository temaRepository;
	
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){ // O Método getAll() será do tipo ResponseEntity porquê ele responderá a Requisição HTTP (HTTP Request), com uma Resposta HTTP (HTTP Response).
		return ResponseEntity.ok(postagemRepository.findAll());
	}
	
	@GetMapping("/{id}") // É como se uma variável estivesse no path
	public ResponseEntity<Postagem> getById(@PathVariable Long id){
		// Verifica se a postagem foi encontrada, se sim, mostra a postagem para o usuário, caso contrário, retorna o status 404
		return postagemRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build()); // Status 404: not foudn
	}
		
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@PostMapping
	public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem){ // Envia status 400: bad request caso inválido
		if (temaRepository.existsById(postagem.getTema().getId()))
			return ResponseEntity.status(HttpStatus.CREATED) // Status 201: created
					.body(postagemRepository.save(postagem));
			
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe!", null);
	}
	
	@PutMapping
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem){
		if (postagemRepository.existsById(postagem.getId())){
			
			if (temaRepository.existsById(postagem.getTema().getId()))
				return ResponseEntity.status(HttpStatus.OK) // Status 200: OK
						.body(postagemRepository.save(postagem));
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tema não existe!", null);
			
		}			
			
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT) // Status 204: no content
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Postagem> postagem = postagemRepository.findById(id);
		
		if(postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		postagemRepository.deleteById(id);				
	}

	public TemaRepository getTemaRepository() {
		return temaRepository;
	}

	public void setTemaRepository(TemaRepository temaRepository) {
		this.temaRepository = temaRepository;
	}
}