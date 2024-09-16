package com.ipartek.auxiliares;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.multipart.MultipartFile;

import com.ipartek.model.Producto;

public class Auxiliares {

	
	public static void guardarImagen(Producto producto, MultipartFile archivo) {
		long d = System.currentTimeMillis();

		
		if (!archivo.isEmpty()) {
		        try {
		            // Ruta donde se guardará el archivo
		            String nombreArchivo = archivo.getOriginalFilename();
		            Path ruta = Paths.get("src/main/resources/static/imagenes/", nombreArchivo+d);
		            System.out.println(ruta);
		            // Guardar el archivo físicamente
		            Files.write(ruta, archivo.getBytes());
		            
		            // Asignar el nombre del archivo al objeto Producto
		            producto.setFoto("imagenes/"+nombreArchivo+d);
		            
		        } catch (Exception e) {
		            System.out.println(e.getMessage());
		            e.printStackTrace();
		        }
		    } else {
		        // Si no se subió archivo, asignar un valor por defecto
		        producto.setFoto("default.jpg");
		    }
	}

}

