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

@RestController
@RequestMapping("/postagens")
//tudo que vier, aceite. liberando tudo para conectar
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	//transferindo a responsabilidade de criar e estanciar para o spring
	// ele que vai criar o objeto agora
	@Autowired
	private PostagemRepository postagemRepository;
	
	//criando metodos como o SELECT * FROM
	
	//consultar, ver use GET
	//ResponseEntity devolve um status como o 200 tudo ok
	//<List<Postagem>> trazer a lista de Postagem (tabela)
	// getAll() nome qualquer para isso
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		//SELECT * FROM tb_postagens
		//postagemRepository é o que transferimos a responsalidade
		return ResponseEntity.ok(postagemRepository.findAll());
		
	
	}
}
