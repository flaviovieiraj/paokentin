package com.devcaotics.PaoKentin.model.repostorios;

import java.security.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.devcaotics.PaoKentin.model.classes.Fornada;
import com.devcaotics.PaoKentin.model.classes.Pao;

public class RepositorioFornada implements Repositorio<Fornada, Integer>{
	
	RepositorioFornada(){
		
	}

	@Override
	public void inserir(Fornada p1) throws SQLException {
		String sql ="insert into fornada(horaInicio, pronto, quente, idPao) values (?,0,0,?)";
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
				
		pstm.setTimestamp(1, p1.getHoraInicio());
		pstm.setInt(2, p1.getPao().getCodigo());
		
		pstm.execute();
		
		
	}

	@Override
	public void alterar(Fornada p1) throws SQLException {
		String sql = "update fornada set pronto=?, quente=? where idFornada=?";
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		
		pstm.setBoolean(1, p1.isPronto());
		pstm.setBoolean(2, p1.isQuente());
		pstm.setInt(3, p1.getCodigo());
		
		pstm.execute();
		
	}

	@Override
	public Fornada ler(Integer k) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletar(Integer k) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Fornada> lerTudo() throws SQLException {
		String sql = "select * from fornada";
		PreparedStatement pstm = ConnectionManager.getCurrentConnection().prepareStatement(sql);
		ResultSet result = pstm.executeQuery();
		
		List<Fornada> fornadas = new ArrayList<Fornada>();
		
		while(result.next()) {
			Fornada f = new Fornada();
			
			f.setCodigo(result.getInt("idFornada"));
			f.setHoraInicio(result.getTimestamp("horaInicio"));
			f.setQuente(result.getBoolean("quente"));
			f.setPronto(result.getBoolean("pronto"));
			
			int tipoPao = result.getInt("idPao");
			
			Pao p = Fachada.getCurrentInstance().lerPao(tipoPao);
			
			f.setPao(p);
			fornadas.add(f);
		}		
		return fornadas;
	}
	

}
