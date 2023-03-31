package com.mmp.security.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmp.security.dto.ProdutoDto;
import com.mmp.security.model.ProdutoModel;
import com.mmp.security.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService service;
	
	
	@PostMapping
	public ResponseEntity<Object> create(@RequestBody ProdutoDto produtoDto){
		
		ProdutoModel produto = new ProdutoModel();
		BeanUtils.copyProperties(produtoDto, produto);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.create(produto));
	}
	
	@GetMapping
	public ResponseEntity<List<ProdutoModel>> getAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id){
		Optional<ProdutoModel> produtoOptional = service.getById(id);
		if(!produtoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado");
		}
		
		service.delete(id);
		
		return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso!");
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getById(@PathVariable(value = "id") UUID id){
		Optional<ProdutoModel> produtoOptional = service.getById(id);
		if(!produtoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(produtoOptional.get());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id, @RequestBody ProdutoDto produtoDto){
		Optional<ProdutoModel> produtoOptional = service.getById(id);
		if(!produtoOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
		}
		ProdutoModel produto = produtoOptional.get();
		produto.setNome(produtoDto.getNome());
		produto.setPreco(produtoDto.getPreco());
		
		return ResponseEntity.status(HttpStatus.OK).body(service.create(produto));
	}
}
