package com.devcaotics.PaoKentin.controller.pao;
import java.sql.SQLException;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.devcaotics.PaoKentin.model.classes.Pao;
import com.devcaotics.PaoKentin.model.repostorios.Fachada;


@RestController
public class PaoRestController {
	
	@PostMapping("/Pao")
	public String inserir(@RequestBody Pao tipoPao) {
		
		try {
			Fachada.getCurrentInstance().inserir(tipoPao);
			return "Tipo de pão cadastrado";
		} catch (SQLException e) {
			return "Falha ao cadastrar tipo de pão";
		}
		
	}
	@PutMapping("/Pao")
	public String alterar(@RequestBody Pao tipoPao) {
		try {
			Fachada.getCurrentInstance().alterar(tipoPao);
			return "Tipo de pão alterado com sucesso";
		} catch (SQLException e) {
			return "Falha ao alterar tipo de pão";
		}
		
	}
	
	@GetMapping("Pao/{codigo}")
	public Pao ler(@PathVariable("codigo") int codigo) {
		
		try {
			return Fachada.getCurrentInstance().lerPao(codigo);
		} catch (SQLException e) {
			return null;
		}
		
	}
	
	@DeleteMapping("Pao/{codigo}")
	public String delete(@PathVariable("codigo") int codigo) {
		try {
			Fachada.getCurrentInstance().deletarPao(codigo);
			return "Tipo de pão deletado";
		} catch (SQLException e) {
			return "Falha ao deletar tipo de pão";
		}
		
	}
	@GetMapping("Pao")
	public List<Pao> lerTodos(){
		try {
			return Fachada.getCurrentInstance().lerTudoPao();
		} catch (SQLException e) {
			return null;
		}
	}

}
