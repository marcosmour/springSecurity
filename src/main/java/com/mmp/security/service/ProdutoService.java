package com.mmp.security.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmp.security.model.ProdutoModel;
import com.mmp.security.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;

	public ProdutoModel create(ProdutoModel produto) {
		return repository.save(produto);
	}

	public List<ProdutoModel> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public Optional<ProdutoModel> getById(UUID id) {
		// TODO Auto-generated method stub
		return repository.findById(id);
	}

	public void delete(UUID id) {
		repository.deleteById(id);
		
	}




}
