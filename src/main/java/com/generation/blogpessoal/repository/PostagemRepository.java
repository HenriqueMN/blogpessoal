package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {
	
	// JpaRepository: repositório que já tem todos os métodos necessários para a criação de um CRUD
	
	/* ----- Principais métodos da classe JpaRepository
	 * 
	 * 
	 * save(Objeto objeto): cria ou Atualiza um objeto no Banco de Dados.
	 * 
	 * findById(Long id): retorna (exibe) um Objeto persistido de acordo com o id informado.
	 * 
	 * existsById(Long id): retorna True se um Objeto identificado pelo id estiver persistido no Banco de dados.
	 * 
	 * findAll(): retorna (exibe) todos os Objetos persistidos.
	 * 
	 * deleteById(Long id): localiza um Objeto persistido pelo id e deleta caso ele seja encontrado. Não é possível desfazer esta operação.
	 * 
	 * deleteAll(): deleta todos os Objetos persistidos.Não é possível desfazer esta operação.
	 */
	
	List<Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
}
