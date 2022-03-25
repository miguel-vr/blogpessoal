package com.generation.blogpessoal.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

@RestController
@RequestMapping("/postagens")
//tudo que vier, aceite. liberando tudo para conectar
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {

	// transferindo a responsabilidade de criar e estanciar para o spring
	// ele que vai criar o objeto agora
	@Autowired
	private PostagemRepository postagemRepository;

	// criando metodos como o SELECT * FROM

	// consultar, ver use GET
	// ResponseEntity devolve um status como o 200 tudo ok
	// <List<Postagem>> trazer a lista de Postagem (tabela)
	// getAll() nome qualquer para isso
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll() {
		// SELECT * FROM tb_postagens
		// postagemRepository é o que transferimos a responsalidade
		return ResponseEntity.ok(postagemRepository.findAll());
	}

	// buscando por id da postagem
	// id entre {} entende o valor do id
	// tirar a lista
	// O get pode ser qualquer nome;
	// Criar variavél Long id (chave primaria)
	// @pathVariable para fazer conexão do Long id e do /{id}
	// Optional (.map) para não aparecer id/objetos nulos
	// executar findbyid
	// coloque o resultado de findbyid em resposta, se for .ok mostre resposta
	// orElse notFund
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id) {
		// find by id WHERE ID = 4
		return postagemRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

	// buscando por titulo da postagem
	// <list> pois pode ter mais de uma postagem
	// criar uma variavel String titulo
	// criar metodo de busca no repository
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}

	// Cadastrar/gravar/criar registros no db
	// gravar um objeto inteiro com todos os atributos
	// Postagem = classe, postagem = objeto
	// @RequestBody pegar no corpo da requisição
	// status(HttpStatus.CREATED)devolve o status 201 de created
	// .save gravar na tabela do mysql
	// @Valid mostra o erro
	@PostMapping
	public ResponseEntity<Postagem> postPostagem(@Valid @RequestBody Postagem postagem) {
		return ResponseEntity.status(HttpStatus.CREATED).body(postagemRepository.save(postagem));
	}

	// Atualização
	// A mesma forma do Post só trocar por Put e o status
	
	@PutMapping
    public ResponseEntity<Postagem> putPostagem(@Valid @RequestBody Postagem postagem) {

        return postagemRepository.findById(postagem.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(postagemRepository.save(postagem)))
                .orElse(ResponseEntity.notFound().build());
    }
	// Apagar
	// void não retorna status
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePostagem(@PathVariable long id) {
		return postagemRepository.findById(id).map(resposta -> {
			postagemRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
