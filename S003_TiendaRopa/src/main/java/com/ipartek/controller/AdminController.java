package com.ipartek.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ipartek.auxiliares.Auxiliares;
import com.ipartek.model.Producto;
import com.ipartek.repository.CategoriaRepository;
import com.ipartek.repository.GeneroRepository;
import com.ipartek.repository.ProductoRepository;

@Controller
public class AdminController {

	@Autowired
	private ProductoRepository productosRepo;
	@Autowired
	private CategoriaRepository categoriasRepo;
	@Autowired
	private GeneroRepository generosRepo;

	@RequestMapping("/admin")
	public String admin(Model model) {
		model.addAttribute("atr_lista_categorias", categoriasRepo.findAll());
		model.addAttribute("atr_lista_productos", productosRepo.findAll());
		return "admin";

	}

	@RequestMapping("/adminBorrarProducto")
	public String borrarAdmin(Model model, int id) {

		try {
			productosRepo.deleteById(id);
		} catch (NumberFormatException e) {
			System.err.println("Error con el id");
			e.printStackTrace();
		}

		model.addAttribute("atr_lista_categorias", categoriasRepo.findAll());
		model.addAttribute("atr_lista_productos", productosRepo.findAll());

		return "admin";

	}

	@RequestMapping("/adminFrmModificarProducto")
	public String modificarFrmProdAdmin(Model model, @ModelAttribute("obj_producto") Producto prod,
			@RequestParam(value = "id", required = false) Integer id) {
		model.addAttribute("atr_lista_categorias", categoriasRepo.findAll());
		model.addAttribute("atr_lista_generos", generosRepo.findAll());

		if (id != null) {
			prod = productosRepo.findById(id).orElse(new Producto());
		} else {
			prod = new Producto();

		}
		System.out.println(prod.getNombre());
		model.addAttribute("obj_producto", prod);
		return "frm_modificar_productos";
	}

	@RequestMapping("/adminModificarProducto")
	public String modificarProductoAdmin(Model model, @ModelAttribute("obj_producto") Producto producto,
			@RequestParam("param_foto") MultipartFile archivo) {

		// HACER
		if (!archivo.isEmpty()) {
			Auxiliares.guardarImagen(producto, archivo);
		}

		productosRepo.save(producto);

		return "redirect:/admin";
	}

	@RequestMapping("/adminAnadirProducto")
	public String guardarProductoAdmin(Model model, @ModelAttribute("obj_producto") Producto producto,
			@RequestParam("param_foto") MultipartFile archivo) {

		Auxiliares.guardarImagen(producto, archivo);

		productosRepo.save(producto);

		return "redirect:/admin";
	}
}
