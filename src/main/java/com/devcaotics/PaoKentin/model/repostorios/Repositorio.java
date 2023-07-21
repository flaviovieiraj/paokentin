package com.devcaotics.PaoKentin.model.repostorios;
import java.sql.SQLException;
import java.util.List;

public interface Repositorio <P1, Key>{

	public void inserir(P1 p1) throws SQLException;
	public void alterar(P1 p1) throws SQLException;
	public P1 ler(Key k) throws SQLException;
	public void deletar(Key k) throws SQLException;
	public List<P1> lerTudo() throws SQLException;
}
