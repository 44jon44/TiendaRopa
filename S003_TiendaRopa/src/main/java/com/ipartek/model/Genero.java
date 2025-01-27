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
@Table(name = "generos")
public class Genero {

	@Id
	@Column(name = "id")
	private int id;

	@Column(name = "genero")
	private String genero;

	@OneToMany
	private List<Producto> listaProductos;

	public Genero(int id, String genero) {
		super();
		this.id = id;
		this.genero = genero;
		
	}
	
	public Genero() {
		super();
		this.id = 0;
		this.genero = "";
		this.listaProductos = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}
	public String toCSV() {
	    return id + "," + genero;
	}
}
