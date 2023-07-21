package com.devcaotics.PaoKentin.model.repostorios;

import java.sql.SQLException;
import java.util.List;

import com.devcaotics.PaoKentin.model.classes.Fornada;
import com.devcaotics.PaoKentin.model.classes.Pao;

public class Fachada {
	
	private static Fachada myself=null;
	
	private Repositorio<Pao, Integer> rPao = null;
	private Repositorio<Fornada, Integer> rFornada = null;
	
	private Fachada() {
		this.rPao = new RepositorioPao();
		this.rFornada = new RepositorioFornada();
	}
	
	public static Fachada getCurrentInstance() {
		if(myself == null)
			myself = new Fachada();
		
		return myself;
	}

	public void inserir(Pao p1) throws SQLException {
		this.rPao.inserir(p1);
	}

	public void alterar(Pao p1) throws SQLException {
		this.rPao.alterar(p1);
	}

	public Pao lerPao(int codigo) throws SQLException {
		return this.rPao.ler(codigo);
	}

	public void deletarPao(int codigo) throws SQLException {
		this.rPao.deletar(codigo);
	}

	public List<Pao> lerTudoPao() throws SQLException {
		return this.rPao.lerTudo();
	}

	public void inserir(Fornada f) throws SQLException {
		this.rFornada.inserir(f);
	}

	public void alterar(Fornada f) throws SQLException {
		this.rFornada.alterar(f);
	}

	public Fornada lerFornada(int codigo) throws SQLException {
		return this.rFornada.ler(codigo);
	}

	public void deletarFornada(int codigo) throws SQLException {
		this.rFornada.deletar(codigo);
	}

	public List<Fornada> lerTudoFornada() throws SQLException {
		return this.rFornada.lerTudo();
	}

	
	
	
	

}
