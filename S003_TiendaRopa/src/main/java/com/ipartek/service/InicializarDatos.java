package com.ipartek.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ipartek.model.Categoria;
import com.ipartek.model.Genero;
import com.ipartek.model.Privilegio;
import com.ipartek.model.Usuario;
import com.ipartek.repository.CategoriaRepository;
import com.ipartek.repository.GeneroRepository;
import com.ipartek.repository.UsuarioRepository;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;

@Service
public class InicializarDatos {
	
	@Autowired
	private CategoriaRepository categoriaRepo ;
	
	@Autowired
	private GeneroRepository generoRepo ;
	
	@Autowired
	private UsuarioRepository usuarioRepo ;
	
	@PostConstruct
	@Transactional
	public void inicializarDatos() {
		//lista de generos
//		generoRepo.save(new Genero(1, "Hombre"));
//		generoRepo.save(new Genero(2, "Mujer"));
//		generoRepo.save(new Genero(3, "Infantil"));
//		
//		//equipos en las aulas
//		categoriaRepo.save(new Categoria(1, "Pantalones"));
//		categoriaRepo.save(new Categoria(2, "Faldas"));
//		categoriaRepo.save(new Categoria(3, "Camisetas"));
//		categoriaRepo.save(new Categoria(4, "Camisas"));
//		
		usuarioRepo.save(new Usuario(2,"2","d4735e3a265e16eee03f59718b9b5d03019c07d8b6c51f90da3a666eec13ab35",Privilegio.ADMIN));
		usuarioRepo.save(new Usuario(1,"1","6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b",Privilegio.USUARIO));
//		
//	
	}

}
