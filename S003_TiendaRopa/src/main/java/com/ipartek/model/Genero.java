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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "genero")
	private String genero;

	@OneToMany
	private List<Producto> listaMoviles;

	public Genero(int id, String genero, List<Producto> listaMoviles) {
		super();
		this.id = id;
		this.genero = genero;
		this.listaMoviles = listaMoviles;
	}
	
	public Genero() {
		super();
		this.id = 0;
		this.genero = "";
		this.listaMoviles = new ArrayList<>();
	}
}
