package com.ipartek.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


	@Entity
	@Table(name="categorias")
	
	public class Categoria {
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="id")
		private int id;
		
		@Column(name="nombre")
		private String nombre;
		
		@OneToMany
		private List<Producto> listaProductos;



		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNombre() {
			return nombre;
		}

		public void setNombre(String nombre) {
			this.nombre = nombre;
		}

		public List<Producto> getListaProductos() {
			return listaProductos;
		}

		public void setListaProductos(List<Producto> listaProductos) {
			this.listaProductos = listaProductos;
		}

		public Categoria(int id, String nombre) {
			super();
			this.id = id;
			this.nombre = nombre;
			
		}

		public Categoria(int id, String nombre, List<Producto> listaProductos) {
			super();
			this.id = 0;
			this.nombre = "";
			this.listaProductos = new ArrayList<>();
		}

		public Categoria() {
			super();
		}
		public String toCSV() {
		    return id + "," + nombre;
		}
		

}
