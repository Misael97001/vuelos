package com.krakedev.exam.vuelos.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.krakedev.exam.vuelos.entities.Vuelo;
import com.krakedev.exam.vuelos.services.ServicioVuelo;

@RestController
@RequestMapping("/api/vuelos")
public class VueloController {

	private final ServicioVuelo servicio;

	public VueloController(ServicioVuelo servicio) {
		this.servicio = servicio;
	}

	@PostMapping
	public ResponseEntity<?> crear(@RequestBody Vuelo vuelo){

		try {

			Vuelo creado =servicio.crear(vuelo);

			return ResponseEntity.status(HttpStatus.CREATED).body(creado);

		}catch(Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear vuelo");
		}
	}

	@GetMapping
	public ResponseEntity<?> listar(){

		try {

			List<Vuelo> vuelos =servicio.listar();

			return ResponseEntity.ok(vuelos);

		}catch(Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar");
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(
			@PathVariable Long id){

		try {

			Vuelo vuelo =servicio.buscarPorId(id);

			if(vuelo == null) {

				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vuelo no encontrado");
			}

			return ResponseEntity.ok(vuelo);

		}catch(Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar");
		}
	}

	@GetMapping("/precio")
	public ResponseEntity<?> buscarPorPrecio(@RequestParam BigDecimal precio){

		try {

			return ResponseEntity.ok(servicio.buscarPorPrecio(precio));

		}catch(Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
		}
	}

	@GetMapping("/asientos")
	public ResponseEntity<?> buscarPorAsientos(	@RequestParam Integer asientos){

		try {

			return ResponseEntity.ok(
					servicio.buscarPorAsientos(asientos));

		}catch(Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> actualizar(@PathVariable Long id,@RequestBody Vuelo vuelo){

		try {

			Vuelo actualizado =servicio.actualizar(id, vuelo);

			if(actualizado == null) {

				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vuelo no encontrado");
			}

			return ResponseEntity.ok(actualizado);

		}catch(Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminar(@PathVariable Long id){

		try {

			boolean eliminado =servicio.eliminar(id);

			if(!eliminado) {

				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vuelo no encontrado");
			}

			return ResponseEntity.ok("Vuelo eliminado");
		}catch(Exception e) {

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
		}
	}
}