package com.ipartek.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void eliminarTabla(String nombreTabla) {
        String sql = "DROP TABLE IF EXISTS " + nombreTabla;
        jdbcTemplate.execute(sql);
    }
}
