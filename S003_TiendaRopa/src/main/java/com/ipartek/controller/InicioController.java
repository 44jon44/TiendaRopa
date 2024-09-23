package com.ipartek.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.auxiliares.AdvancedLogger;
import com.ipartek.model.Privilegio;
import com.ipartek.model.Producto;

import com.ipartek.repository.CategoriaRepository;
import com.ipartek.repository.ProductoRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class InicioController {

	@Autowired
	private ProductoRepository productosRepo;
	@Autowired
	private CategoriaRepository categoriasRepo;
	
	private static final Logger logger = LogManager.getLogger(AdvancedLogger.class);
	@RequestMapping("/")
	public String index(Model model, HttpSession session) {


		model.addAttribute("atr_lista_categorias", categoriasRepo.findAll());
		model.addAttribute("atr_lista_productos", productosRepo.buscarTodosVisibles());
		
		
		session.setAttribute("rol", Privilegio.USUARIO);
		session.setAttribute("Intentos", 0);
		return "index";

	}
}
