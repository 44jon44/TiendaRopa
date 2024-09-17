package com.ipartek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ipartek.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	
	@Query(value = "SELECT * FROM vistafulltext WHERE producto= :producto and genero= :genero and categoria :categoria", nativeQuery = true)
	List<Producto> buscarProducto(@Param("producto") String prod,@Param("genero") String gen,@Param("categoria") String cat);
	
}
