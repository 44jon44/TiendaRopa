package com.ipartek.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ipartek.model.Privilegio;
import com.ipartek.model.Producto;
import com.ipartek.model.Usuario;
import com.ipartek.repository.CategoriaRepository;
import com.ipartek.repository.GeneroRepository;
import com.ipartek.repository.ProductoRepository;
import com.ipartek.repository.UsuarioRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class MenuController {

	@Autowired
	private ProductoRepository productosRepo;

	@Autowired
	private CategoriaRepository categoriasRepo;
	
	@Autowired
	private GeneroRepository generosRepo;

	@Autowired
	private UsuarioRepository usuariosRepo;

	@RequestMapping("/menuCamiseta")
	public String opcionCamisetas(Model model) {
		List<Producto> listaRopa = productosRepo.findAll();
		List<Producto> listaCamisetas = listaRopa.stream().filter(prod -> prod.getCategoria().getId() == 1)
				.collect(Collectors.toList());

		model.addAttribute("atr_lista_camisetas", listaCamisetas);
		model.addAttribute("atr_lista_categorias", categoriasRepo.findAll());
		return "index";

	}

	@RequestMapping("/menuFalda")
	public String opcionFaldas(Model model) {
		List<Producto> listaRopa = productosRepo.findAll();
		List<Producto> listaFaldas = listaRopa.stream().filter(prod -> prod.getCategoria().getId() == 2)
				.collect(Collectors.toList());

		model.addAttribute("atr_lista_faldas", listaFaldas);
		model.addAttribute("atr_lista_categorias", categoriasRepo.findAll());
		return "faldas";
	}

	@RequestMapping("/menuJersey")
	public String opcionJerseys(Model model) {
		List<Producto> listaRopa = productosRepo.findAll();
		List<Producto> listaJerseys = listaRopa.stream().filter(prod -> prod.getCategoria().getId() == 3)
				.collect(Collectors.toList());

		model.addAttribute("atr_lista_jerseys", listaJerseys);
		model.addAttribute("atr_lista_categorias", categoriasRepo.findAll());
		return "jerseys";
	}

	@RequestMapping("/menuPantalon")
	public String opcionPantalones(Model model) {
		List<Producto> listaRopa = productosRepo.findAll();
		List<Producto> listaPantalones = listaRopa.stream().filter(prod -> prod.getCategoria().getId() == 4)
				.collect(Collectors.toList());

		model.addAttribute("atr_lista_pantalones", listaPantalones);
		model.addAttribute("atr_lista_categorias", categoriasRepo.findAll());
		return "pantalones";
	}

	@RequestMapping("/formLogin")
	public String formLogin(Model model) {

		model.addAttribute("obj_usuario", new Usuario());

		
		return "login";
	}

	@RequestMapping("/login")
	public String login(Model model,@ModelAttribute(value="obj_usuario") Usuario user, HttpSession session) {
		
		
				
		System.out.println("El nombre que llega es "+user.getNombre());
		System.out.println("La Contraseña que llega es "+user.getContraseña());
		
		List<Usuario> usuarios = usuariosRepo.findAll();
		for (Usuario elem : usuarios) {
			System.out.println(elem.getNombre());
			if (elem.getNombre().equals(user.getNombre()) && elem.getContraseña().equals(user.getContraseña())) {
				model.addAttribute("atr_lista_categorias", categoriasRepo.findAll());
				model.addAttribute("atr_lista_productos", productosRepo.findAll());
				model.addAttribute("atr_lista_generos", generosRepo.findAll());
				
				model.addAttribute("obj_producto", new Producto());
				session.setAttribute("rol", Privilegio.ADMIN);
				
				return "admin";
			}
		}
		List<Producto> listaRopa = productosRepo.findAll();
		List<Producto> listaCamisetas = listaRopa.stream()
		        .filter(prod -> prod.getCategoria().getId() == 1)
		        .collect(Collectors.toList());

		model.addAttribute("atr_lista_camisetas", listaCamisetas);
		model.addAttribute("atr_lista_categorias", categoriasRepo.findAll());
		return "index";
	}

}
