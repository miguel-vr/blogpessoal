package com.generation.blogpessoal.model;


import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//3- colocar a @entity = entidade, javax. e @Table(name = "tb_nomedatabela")
// CREATE TABLE tb_postagens
@Entity
@Table(name = "tb_postagens")
public class Postagem {
	// 4- @Id indica a chave primaria
	// 4- @GenetedValue = auto increment
	// 1-coloque o private com os atributos

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// @NotNull = NOTNULL
	// @NotBlank = NOT NULL (mensagem para o usuario)
	// @Size = definir os parametros de tamanho
	@NotBlank(message = "O atributo título é Obrigatório!")
	@Size(min = 5, max = 100, message = "O atributo titulo deve possuir no min 5 e no max 100 caracteres")
	private String titulo;

	@NotNull(message = "O atributo texto é Obrigatório!")
	@Size(min = 10, max = 1000, message = "O atributo texto deve possuir no min 10 e no max 1000 caracteres")
	private String texto;

	// @UpdateTimestamp = atualizar/criar a data e hora da postagem automaticamente
	@UpdateTimestamp
	private LocalDateTime data;

	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;
	
	
	// 2-criando get/set
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
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

	

}
