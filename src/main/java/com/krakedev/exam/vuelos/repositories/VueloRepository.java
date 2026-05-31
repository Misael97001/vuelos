package com.krakedev.exam.vuelos.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.krakedev.exam.vuelos.entities.Vuelo;

@Repository
public interface VueloRepository
extends JpaRepository<Vuelo, Long>{

	List<Vuelo> findByPrecioBoletoLessThan(BigDecimal precio);

	List<Vuelo> findByAsientosDisponiblesGreaterThan(Integer asientos);

}