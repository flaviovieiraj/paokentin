package com.devcaotics.PaoKentin.model.repostorios;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.devcaotics.PaoKentin.model.classes.Pao;

public class RepositorioPao implements  Repositorio<Pao, Integer>{

	RepositorioPao(){
		
	}

	@Override
	public void inserir(Pao p1) throws SQLException {
		String sql = "insert into pao(tipoPao, tempoPreparo) values (?,?)";
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
		pstm.setString(1, p1.getTipoPao());
		pstm.setLong(2, p1.getTempoPreparo());
		
		pstm.execute();
		
	}

	@Override
	public void alterar(Pao p1) throws SQLException {
		String sql = "update pao set tipoPao=?, tempoPreparo=? where idPao=?";
		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
		pstm.setString(1, p1.getTipoPao());
		pstm.setLong(2, p1.getTempoPreparo());
		pstm.setInt(3, p1.getCodigo());
		pstm.execute();
		
		
	}

	@Override
	public Pao ler(Integer k) throws SQLException {
		String sql = "select * from pao where idPao = ?";		
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		pstm.setInt(1, k);
		ResultSet result = pstm.executeQuery();
		Pao tPao = null;
		
		if(result.next()) {
			tPao = new Pao();
			tPao.setCodigo(k);
			tPao.setTipoPao(result.getString("tipoPao"));
			tPao.setTempoPreparo(result.getInt("tempoPreparo"));
		}
		return tPao;
	}

	@Override
	public void deletar(Integer k) throws SQLException {
		String sql = "delete from pao where idPao = ?";
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		pstm.setInt(1, k);
		pstm.execute();
		
	}

	@Override
	public List<Pao> lerTudo() throws SQLException {
		String sql = "select * from pao";
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		ResultSet result = pstm.executeQuery();
		
		List<Pao> paes = new ArrayList<Pao>();
		
		
		while (result.next()) {
			Pao tPao = new Pao();
			tPao.setCodigo(result.getInt("idPao"));
			tPao.setTipoPao(result.getString("tipoPao"));
			tPao.setTempoPreparo(result.getInt("tempoPreparo"));
			
			paes.add(tPao);
		}
		
		return paes;
	}
}

