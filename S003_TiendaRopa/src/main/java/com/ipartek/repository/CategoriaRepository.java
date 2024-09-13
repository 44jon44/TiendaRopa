package com.ipartek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ipartek.model.Categoria;
import com.ipartek.model.Producto;



@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer>{

}
