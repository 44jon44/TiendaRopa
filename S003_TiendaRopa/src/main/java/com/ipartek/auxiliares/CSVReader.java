package com.ipartek.auxiliares;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.model.Categoria;
import com.ipartek.model.Genero;
import com.ipartek.model.Producto;
import com.ipartek.model.Talla;

public class CSVReader {

	public static List<Object> leerCSV(String archivo, String tipo) {
		
		List<Object> productos = new ArrayList<>();
		List<Object> generos = new ArrayList<>();
		List<Object> categorias = new ArrayList<>();

		try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
			String linea;

			reader.readLine();//Ignora Cabecera
			if (tipo == Producto.class.getName()) {

				while ((linea = reader.readLine()) != null) {
					String[] valores = linea.split(",");

					Producto producto = new Producto();
					producto.setId(Integer.parseInt(valores[0]));
					producto.setNombre(valores[1]);
					producto.setPrecio(Double.parseDouble(valores[2]));
					producto.setFoto(valores[3]);
					producto.setGenero(new Genero(Integer.parseInt(valores[5]), ""));
					producto.setCategoria(new Categoria(Integer.parseInt(valores[4]), ""));
					producto.setTalla(new Talla(Integer.parseInt(valores[5]), ""));

					productos.add(producto);
					
				}
				return productos;
			}
			if (tipo == Genero.class.getName()) {
				// Leer el resto del archivo
				while ((linea = reader.readLine()) != null) {
					String[] valores = linea.split(",");

					Genero gen = new Genero();
					gen.setId(Integer.parseInt(valores[0]));
					gen.setGenero(valores[1]);

					generos.add(gen);
					
				}
				return generos;
			}
			if (tipo == Categoria.class.getName()) {
				// Leer el resto del archivo
				while ((linea = reader.readLine()) != null) {
					String[] valores = linea.split(",");

					Categoria cat = new Categoria();
					cat.setId(Integer.parseInt(valores[0]));
					cat.setNombre(valores[1]);

					categorias.add(cat);
					
				}
				return categorias;
			}
			if (tipo == Talla.class.getName()) {
				// Leer el resto del archivo
				while ((linea = reader.readLine()) != null) {
					String[] valores = linea.split(",");

					Talla talla = new Talla();
					talla.setId(Integer.parseInt(valores[0]));
					talla.setNombre(valores[1]);

					categorias.add(talla);
					
				}
				return categorias;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();

	}
}
