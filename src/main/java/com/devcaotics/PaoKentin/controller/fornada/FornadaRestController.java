package com.devcaotics.PaoKentin.controller.fornada;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devcaotics.PaoKentin.model.classes.Fornada;
import com.devcaotics.PaoKentin.model.classes.Pao;
import com.devcaotics.PaoKentin.model.repostorios.Fachada;

@RestController
public class FornadaRestController {
	
	@PostMapping("/Fornada")
	public void inserir(@RequestBody Fornada fornada) {
		
		try {
			Fachada.getCurrentInstance().inserir(fornada);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@GetMapping("Fornada")
	public List<Fornada> lerTudo(){
		try {
			return Fachada.getCurrentInstance().lerTudoFornada();
		} catch (SQLException e) {
			return null;
		}
	}
	
	@PutMapping("/Fornada/{codigo}")
	public String alterar(@PathVariable int codigo, @RequestBody Fornada fornada) {
		try {
			Fachada.getCurrentInstance().alterar(fornada);
			return "Fornada atualizada com sucesso";
		} catch (SQLException e) {
			System.err.println("Erro ao atualizar fornada: " + e.getMessage());
			return "Falha ao atualizar fornada";
		}
		
	}
	
}
