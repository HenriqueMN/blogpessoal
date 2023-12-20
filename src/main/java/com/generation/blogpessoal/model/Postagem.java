package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*
 * 	----- ANOTAÇÕES DA CLASSE
 * @Entity: indica que esta Classe define uma entidade, ou seja, ela será utilizada para gerar uma tabela no Banco de dados da aplicação.
 * @Table: indica o nome da Tabela no Banco de dados. Caso esta anotação não seja declarada, o Banco de dados criará a tabela com o mesmo nome da Classe Model.
 */

@Entity
@Table(name = "tb_postagens")
public class Postagem {
	
	/*
	 * ----- ANOTAÇÕES DOS ATRIBUTOS
	 * 
	 * @Id: indica o atributo que será a chave primária da tabela
	 * 
	 * @GeneratedValue(strategy = XYZ.XYZ): indica que a chave primária será gerada pelo banco de dados e a estratégia que ele usará para gerar a chave. A estratégia GeneratipnType.IDENTITY significa que a chave será gerada como auto-increment
	 * 
	 * @NotBlank(message = "XYZ"): similar à anotação @NotNull, mas utilizada para texto ao invés de números. Não permite que o atributo seja nulo nem contenha apenas espaços em branco. O parâmetro "message" permite configurar uma mensagem para o usuário
	 * 
	 * @Size: define os valores mínimo (min) e máximo (max) de caracteres do atributo. Não é obrigatório especificar os dois parâmetros. Quando especificado, o valor max é o utilizado apra a criação dos atributos na tabela. O parâmetro "message" permite confitgurar uma mensagme para o usuário
	 * 
	 * @UpdateTimestamp: configura o Atributo data como Timestamp, ou seja, o Spring se encarregará de obter a data e a hora do Sistema Operacional e inserir no Atributo data toda vez que um Objeto da Classe Postagem for criado ou atualizado.
	 * 
	 * @ManyToOne: configura a relação muitos para um
	 * 
	 * @JsonIgnoreProperties: evita a recursividade ignorando a própria classe
	 * 
	 */
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo título é Obrigatório!")	
	@Size(min = 5, max = 100, message = "O atributo título deve conter no mínimo 05 e no máximo 100 caracteres")
	@Column(length = 100)
	private String titulo;
	
	@NotBlank(message = "O atributo texto é Obrigatório!")
	@Size(min = 10, max = 1000, message = "O atributo texto deve conter no mínimo 10 e no máximo 1000 caracteres")
	@Column(length = 1000)
	private String texto;
	
	@UpdateTimestamp
	private LocalDateTime data;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return this.texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getData() {
        return this.data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}  
}