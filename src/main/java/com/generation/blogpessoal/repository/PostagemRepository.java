package com.generation.blogpessoal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Postagem;

//1-criar a interface da postagem, metodos para implementar na classe.
//2-@Repository falando que é repository
// Erdando a jpaRepository <passando os dados pra manipular> 
// Long = id, ele recebe o id
// dando acesso aos metodos para o repository manipular
// postagem = tabela, Long = id

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

}
