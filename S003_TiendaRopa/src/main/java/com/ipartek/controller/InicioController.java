package com.ipartek.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.model.Producto;
import com.ipartek.repository.CategoriaRepository;
import com.ipartek.repository.ProductoRepository;

@Controller
public class InicioController {

	@Autowired
	private ProductoRepository productosRepo;
	@Autowired
	private CategoriaRepository categoriasRepo;

	@RequestMapping("/")
	public String index(Model model) {

		List<Producto> listaRopa = productosRepo.findAll();
		List<Producto> listaCamisetas = listaRopa.stream()
		        .filter(prod -> prod.getCategoria().getId() == 1)
		        .collect(Collectors.toList());

		model.addAttribute("atr_lista_camisetas", listaCamisetas);
		model.addAttribute("atr_lista_categorias", categoriasRepo.findAll());
		return "index";
		
	}
}
