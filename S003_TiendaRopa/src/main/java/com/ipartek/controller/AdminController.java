package com.ipartek.controller;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ipartek.auxiliares.Auxiliares;
import com.ipartek.model.Privilegio;
import com.ipartek.model.Producto;
import com.ipartek.repository.CategoriaRepository;
import com.ipartek.repository.GeneroRepository;
import com.ipartek.repository.ProductoRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {

	@Autowired
	private ProductoRepository productosRepo;
	@Autowired
	private CategoriaRepository categoriasRepo;
	@Autowired
	private GeneroRepository generosRepo;

	@RequestMapping("/admin")
	public String admin(Model model, @ModelAttribute("obj_producto") Producto prod, HttpSession session) {
		if (session.getAttribute("rol").equals(Privilegio.ADMIN)) {
			model.addAttribute("atr_lista_categorias", categoriasRepo.findAll());
			model.addAttribute("atr_lista_productos", productosRepo.findAll());
			model.addAttribute("atr_lista_generos", generosRepo.findAll());

			model.addAttribute("obj_producto", new Producto());
			
			return "admin";
		}else {
	        return "redirect:https://www.google.es/";
		}

	}

	@RequestMapping("/adminBorrarProducto")
	public String borrarAdmin(Model model, int id, HttpSession session) {

		try {
			productosRepo.deleteById(id);
		} catch (NumberFormatException e) {
			System.err.println("Error con el id");
			e.printStackTrace();
		}
		session.setAttribute("modificacion", "borrarProducto");
        return "redirect:/admin";
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

		model.addAttribute("obj_producto", prod);
		return "frm_modificar_productos";
	}

	@RequestMapping("/adminModificarProducto")
	public String modificarProductoAdmin(Model model, @ModelAttribute(value = "obj_producto") Producto producto,
			@RequestParam(value = "param_foto") MultipartFile archivo, HttpSession session) {

		// HACER
		if (!archivo.isEmpty()) {
			Auxiliares.guardarImagen(producto, archivo);
		}

		productosRepo.save(producto);
		session.setAttribute("modificacion", "modificarProducto");
		return "redirect:/admin";
	}

	@RequestMapping("/adminBorrarFoto")
	public String borrarfotoAdmin(Model model, @RequestParam(value = "param_foto", required = false) String nombre,
			@RequestParam(value = "id", required = false) int id, HttpSession session) {
		String ruta = "src/main/resources/static/imagenes" + nombre;

		File archivoFoto = new File(ruta);
		// HACER
		if (archivoFoto.exists()) {
			if (archivoFoto.delete()) {
				System.out.println("Foto Borrada");
			} else {
				System.out.println("Foto No Borrada");
			}
		} else {
			System.out.println("Foto no Encontrada");
		}

		Optional<Producto> prod = productosRepo.findById(id);

		prod.get().setFoto("imagenes/default.jpg");

		Producto productoModificado = prod.get();

		productosRepo.save(productoModificado);
		session.setAttribute("modificacion", "borrarFoto");

		return "redirect:/admin";
	}

	@RequestMapping("/adminAnadirProducto")
	public String anadirProductoAdmin(Model model, @ModelAttribute(value = "obj_producto") Producto producto,
			@RequestParam("param_foto") MultipartFile archivo, HttpSession session) {

		Auxiliares.guardarImagen(producto, archivo);
		model.addAttribute("obj_producto", producto);
		productosRepo.save(producto);
		
		session.setAttribute("modificacion", "anadirProducto");
		return "redirect:/admin";
	}
	@RequestMapping("/busquedaFullText")
	public String buscarProductoAdmin(Model model, @ModelAttribute(value = "obj_producto") Producto producto,
			 HttpSession session) {

		

		
		session.setAttribute("modificacion", "anadirProducto");
		return "redirect:/admin";
	}
}
