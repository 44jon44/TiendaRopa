package com.ipartek.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ipartek.auxiliares.AdvancedLogger;
import com.ipartek.auxiliares.CSVReader;
import com.ipartek.auxiliares.CSVWriter;
import com.ipartek.model.Categoria;
import com.ipartek.model.Genero;
import com.ipartek.model.Producto;
import com.ipartek.repository.CategoriaRepository;
import com.ipartek.repository.GeneroRepository;
import com.ipartek.repository.ProductoRepository;
import com.ipartek.service.DatabaseService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SeguridadController {
	@Autowired
	private ProductoRepository productosRepo;
	@Autowired
	private CategoriaRepository categoriasRepo;
	@Autowired
	private GeneroRepository generosRepo;
	private static final Logger logger = LogManager.getLogger(AdvancedLogger.class);
	
	private DatabaseService databaseService = null;

    public SeguridadController(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }
   


   

	@RequestMapping("/copiaSeguridadProductos")
	public String copiaSeguriadProductos(Model model, @ModelAttribute(value = "obj_producto") Producto producto,
			HttpSession session) {
		model.addAttribute("obj_producto", new Producto());

		List<Object> listaProd = new ArrayList<>(productosRepo.findAll());
		session.setAttribute("modificacion", "copiaSeguridadProductos");

		CSVWriter.escribirCSV("src/main/resources/copiasSeguridad/productos.csv", listaProd, session);

		return "redirect:/admin";
	}

	@RequestMapping("/copiaSeguridadCategorias")
	public String copiaSeguriadCategorias(Model model, @ModelAttribute(value = "obj_categoria") Categoria cat,
			HttpSession session) {
		model.addAttribute("obj_categoria", new Categoria());

		List<Object> listaCat = new ArrayList<>(categoriasRepo.findAll());
		session.setAttribute("modificacion", "copiaSeguridadCategorias");
		CSVWriter.escribirCSV("src/main/resources/copiasSeguridad/categorias.csv", listaCat, session);

		return "redirect:/admin";
	}

	@RequestMapping("/copiaSeguridadGeneros")
	public String copiaSeguriadGeneros(Model model, @ModelAttribute(value = "obj_genero") Genero gen,

			HttpSession session) {
		model.addAttribute("obj_genero", new Genero());

		List<Object> listaGen = new ArrayList<>(generosRepo.findAll());
		session.setAttribute("modificacion", "copiaSeguridadGeneros");
		CSVWriter.escribirCSV("src/main/resources/copiasSeguridad/generos.csv", listaGen, session);

		return "redirect:/admin";
	}

	@RequestMapping("/restaurarProductos")
	public String restaurarProductos(Model model, @ModelAttribute(value = "obj_producto") Producto producto,
			HttpSession session) {
		model.addAttribute("obj_producto", new Producto());

		session.setAttribute("modificacion", "restaurarProductos");
		List<Object> listaProd = CSVReader.leerCSV("src/main/resources/copiasSeguridad/productos.csv",
				Producto.class.getName());

		for (Object object : listaProd) {

			productosRepo.save((Producto) object);
		}

		return "redirect:/admin";
	}

	@RequestMapping("/restaurarCategorias")
	public String restaurarCategorias(Model model, @ModelAttribute(value = "obj_categoria") Categoria cat,
			HttpSession session) {
		model.addAttribute("obj_categoria", new Categoria());
		String categoria="categorias";
		 
		databaseService.eliminarTabla(categoria);

		session.setAttribute("modificacion", "restaurarCategorias");
		List<Object> listaCat = CSVReader.leerCSV("src/main/resources/copiasSeguridad/categorias.csv",
				Categoria.class.getName());

		for (Object object : listaCat) {
System.out.println("*****************************************");
System.out.println(((Categoria) object).getId());
System.out.println(((Categoria) object).getNombre());
System.out.println("*****************************************");

			categoriasRepo.save((Categoria) object);
		}
		categoriasRepo.save(new Categoria(3,"Jerseys"));
		return "redirect:/admin";
	}

	@RequestMapping("/restaurarGeneros")
	public String restaurarGeneros(Model model, @ModelAttribute(value = "obj_genero") Genero gen, HttpSession session) {
		model.addAttribute("obj_genero", new Genero());

		session.setAttribute("modificacion", "restaurarGeneros");
		List<Object> listaGen = CSVReader.leerCSV("src/main/resources/copiasSeguridad/generos.csv",
				Genero.class.getName());

		for (Object object : listaGen) {
			generosRepo.save((Genero) object);
			
		}
		
		return "redirect:/admin";
	}
}









