package com.ipartek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ipartek.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
	@Query(value = "SELECT * FROM productos WHERE nombre = :producto AND genero_id = :genero AND categoria_id = :categoria", nativeQuery = true)
	List<Producto> buscarProducto(@Param("producto") String prod, @Param("genero") int genero, @Param("categoria") int categoria);
	
}
