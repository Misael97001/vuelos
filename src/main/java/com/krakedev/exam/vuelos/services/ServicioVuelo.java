package com.krakedev.exam.vuelos.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.krakedev.exam.vuelos.entities.Vuelo;
import com.krakedev.exam.vuelos.repositories.VueloRepository;

@Service
public class ServicioVuelo {

	private final VueloRepository repository;

	public ServicioVuelo(VueloRepository repository) {
		this.repository = repository;
	}

	public Vuelo crear(Vuelo vuelo) {
		return repository.save(vuelo);
	}

	public List<Vuelo> listar() {
		return repository.findAll();
	}

	public Vuelo buscarPorId(Long id) {

		Optional<Vuelo> resultado =
				repository.findById(id);

		return resultado.orElse(null);
	}


	public Vuelo actualizar(Long id,
			Vuelo vueloActualizado) {

		Vuelo vuelo = buscarPorId(id);

		if(vuelo == null) {
			return null;
		}

		vuelo.setCodigo(
				vueloActualizado.getCodigo());

		vuelo.setPrecioBoleto(
				vueloActualizado.getPrecioBoleto());

		vuelo.setAsientosDisponibles(
				vueloActualizado.getAsientosDisponibles());

		vuelo.setDestino(
				vueloActualizado.getDestino());

		return repository.save(vuelo);
	}
	
	public boolean eliminar(Long id) {

		Vuelo vuelo = buscarPorId(id);

		if(vuelo == null) {
			return false;
		}

		repository.deleteById(id);

		return true;
	}

	public List<Vuelo> buscarPorPrecio(
			BigDecimal precio){

		return repository
				.findByPrecioBoletoLessThan(precio);
	}

	public List<Vuelo> buscarPorAsientos(
			Integer asientos){

		return repository
				.findByAsientosDisponiblesGreaterThan(asientos);
	}
}