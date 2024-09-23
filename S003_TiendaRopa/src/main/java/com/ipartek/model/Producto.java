package com.ipartek.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Clase que representa un producto en el sistema.
 * Un producto está asociado a un género, una categoría y una talla.
 * Además, tiene atributos como nombre, precio y foto.
 * 
 * @author Jon Mayo
 */
@Entity
@Table(name = "productos")
public class Producto {

    /**
     * Identificador único del producto, generado automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Nombre del producto.
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Precio del producto.
     */
    @Column(name = "precio")
    private double precio;

    /**
     * URL o ruta de la imagen asociada al producto.
     */
    @Column(name = "foto")
    private String foto;
    
    /**
     * Establece si el producto es visible
     */  
	@Column(name="visible")
	private int visible;
	
    /**
     * Género al que pertenece el producto.
     */
    @ManyToOne
    @JoinColumn(name = "genero_id", nullable = false)
    private Genero genero;

    /**
     * Categoría a la que pertenece el producto.
     */
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;
    
    /**
     * Talla asociada al producto.
     */
    @ManyToOne
    @JoinColumn(name = "talla_id", nullable = false)
    private Talla talla;

    /**
     * Constructor con parámetros para inicializar un producto.
     * 
     * @param id        Identificador del producto
     * @param nombre    Nombre del producto
     * @param precio    Precio del producto
     * @param foto      URL o ruta de la foto del producto
     * @param genero    Género asociado al producto
     * @param categoria Categoría asociada al producto
     */
    public Producto(int id, String nombre, double precio, String foto, int visible, Genero genero, Categoria categoria,
			Talla talla) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.foto = foto;
		this.visible = visible;
		this.genero = genero;
		this.categoria = categoria;
		this.talla = talla;
	}
    public Producto() {
		super();
		this.id = 0;
		this.nombre = "";
		this.precio = 0.0;
		this.foto = "";
		this.visible = 0;
		this.genero = new Genero();
		this.categoria = new Categoria();
		this.talla = new Talla();
	}
    /**
     * Obtiene el identificador del producto.
     * 
     * @return id del producto
     */
    public int getId() {
        return id;
    }

   

	/**
     * Establece el identificador del producto.
     * 
     * @param id Nuevo identificador del producto
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return nombre del producto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     * 
     * @param nombre Nuevo nombre del producto
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el precio del producto.
     * 
     * @return precio del producto
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     * 
     * @param precio Nuevo precio del producto
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el género asociado al producto.
     * 
     * @return género del producto
     */
    public Genero getGenero() {
        return genero;
    }

    /**
     * Establece el género del producto.
     * 
     * @param genero Nuevo género del producto
     */
    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    /**
     * Obtiene la categoría asociada al producto.
     * 
     * @return categoría del producto
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoría del producto.
     * 
     * @param categoria Nueva categoría del producto
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtiene la URL o ruta de la foto del producto.
     * 
     * @return foto del producto
     */
    public String getFoto() {
        return foto;
    }

    /**
     * Establece la URL o ruta de la foto del producto.
     * 
     * @param foto Nueva foto del producto
     */
    public void setFoto(String foto) {
        this.foto = foto;
    }

    /**
     * Convierte el producto a formato CSV.
     * 
     * @return representación en formato CSV del producto
     */
    public String toCSV() {
        return id + "," + nombre + "," + precio + "," + foto + "," + visible + "," + categoria.getId() + "," + genero.getId() + "," + talla.getId();
    }

    /**
     * Obtiene la talla asociada al producto.
     * 
     * @return talla del producto
     */
    public Talla getTalla() {
        return talla;
    }

    /**
     * Establece la talla del producto.
     * 
     * @param talla Nueva talla del producto
     */
    public void setTalla(Talla talla) {
        this.talla = talla;
    }

	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
	}


    
}
