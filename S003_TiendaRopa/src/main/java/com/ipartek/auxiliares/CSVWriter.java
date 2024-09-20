package com.ipartek.auxiliares;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.ipartek.model.Categoria;
import com.ipartek.model.Genero;
import com.ipartek.model.Producto;
import com.ipartek.model.Talla;

import jakarta.servlet.http.HttpSession;

public class CSVWriter {

	// Método para escribir la lista de objetos en un archivo CSV
	public static void escribirCSV(String archivo, List<Object> objetos,HttpSession session) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {

			if (session.getAttribute("modificacion").equals("copiaSeguridadProductos")) {
				//Cabecera del csv
				writer.write("id,nombre,precio,foto,categoria_id,genero_id" + "\n");
				for (Object prod : objetos) {					
					Producto producto = (Producto) prod;
					writer.write(producto.toCSV() + System.lineSeparator());
				}
			}
			if (session.getAttribute("modificacion").equals("copiaSeguridadCategorias")){
				//Cabecera del csv
				writer.write("id,nombre" + "\n");
				for (Object cat : objetos) {					
					Categoria categoria = (Categoria) cat;
					writer.write(categoria.toCSV() + System.lineSeparator());
				}
			}
			if (session.getAttribute("modificacion").equals("copiaSeguridadGeneros")){
				//Cabecera del csv
				writer.write("id,genero" + "\n");
				for (Object gen : objetos) {					
					Genero genero = (Genero) gen;
					writer.write(genero.toCSV() + System.lineSeparator());
				}
			}
			if (session.getAttribute("modificacion").equals("copiaSeguridadTallas")){
				//Cabecera del csv
				writer.write("id,nombre" + "\n");
				for (Object obj : objetos) {					
					Talla talla = (Talla) obj;
					writer.write(talla.toCSV() + System.lineSeparator());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
