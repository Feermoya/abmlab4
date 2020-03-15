package com.utn.project.controllers;

import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utn.project.dtos.EmpresaDTO;
import com.utn.project.services.EmpresaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/empresa")
public class EmpresaController {
	private EmpresaService service;

	public EmpresaController(EmpresaService service) { 
		this.service = service;
	}

	@GetMapping("/")
	@Transactional
	public ResponseEntity getAll() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{\"message\": \"Error. Registro no encontrado.\"}");
		}
	}

	@PostMapping("/")
	@Transactional
	public ResponseEntity post(@RequestBody EmpresaDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.save(dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("{\"message\": \"Error. Por favor comprobar el contenido del post o vuelva a intentarlo luego.\"}");
		}
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity put(@PathVariable int id, @RequestBody EmpresaDTO dto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.update(id, dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("{\"message\": \"Error. Por favor comprobar el contenido del post o vuelva a intentarlo luego.\"}");
		}
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity delete(@PathVariable int id) {
		try {
			service.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("{\"message\": \"Registro eliminado\"}");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("{\"message\": \"Error. Por favor comprobar el contenido del post o vuelva a intentarlo luego.\"}");
		}
	}

	@GetMapping("/{id}")
	@Transactional
	public ResponseEntity getOne(@PathVariable int id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\": \"Error. \"}");
		}
	}

}
