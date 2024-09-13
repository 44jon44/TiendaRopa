package com.ipartek.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.model.Producto;
import com.ipartek.repository.ProductoRepository;

@Controller
public class MenuController {
	
	@Autowired
	private ProductoRepository productosRepo;

	
	
	@RequestMapping("/menuCamisetas")
	public String opcionCamisetas(Model model) {
		List<Producto> listaRopa = productosRepo.findAll();
		List<Producto> listaCamisetas = listaRopa.stream()
		        .filter(prod -> prod.getCategoria().getId() == 1)
		        .collect(Collectors.toList());

		model.addAttribute("atr_lista_camisetas", listaCamisetas);

		return "index";

	}
	
	@RequestMapping("/menuFaldas")
	public String opcionFaldas(Model model) {
		List<Producto> listaRopa = productosRepo.findAll();
		List<Producto> listaFaldas = listaRopa.stream()
		        .filter(prod -> prod.getCategoria().getId() == 2)
		        .collect(Collectors.toList());

		model.addAttribute("atr_lista_faldas", listaFaldas);
		System.out.println(productosRepo.toString());
		
		return "faldas";	
	}
	
	@RequestMapping("/menuJerseys")
	public String opcionJerseys(Model model) {
		List<Producto> listaRopa = productosRepo.findAll();
		List<Producto> listaJerseys = listaRopa.stream()
		        .filter(prod -> prod.getCategoria().getId() == 3)
		        .collect(Collectors.toList());

		model.addAttribute("atr_lista_jerseys", listaJerseys);
		return "jerseys";	
	}
	
	@RequestMapping("/menuPantalones")
	public String opcionPantalones(Model model) {
		List<Producto> listaRopa = productosRepo.findAll();
		List<Producto> listaPantalones = listaRopa.stream()
		        .filter(prod -> prod.getCategoria().getId() == 4)
		        .collect(Collectors.toList());

		model.addAttribute("atr_lista_pantalones", listaPantalones);
		
		return "pantalones";	
	}
	
	
}
