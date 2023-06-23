package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Quarto;
import entidades.Hotel;

public class DaoQuarto {

		
		public boolean inserir(Quarto quarto) throws SQLException {
					
			Connection conexao = ConexaoHotel.getConexao();
			
			String sql = "insert into quarto (numQuarto, andar, tipo, id_hotel) values(? , ? , ?, ?)";
			PreparedStatement ps = conexao.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

			ps.setInt(1, quarto.getNumQuarto());
			ps.setString(2, quarto.getAndar());
			ps.setString(3, quarto.getTipo());
			ps.setInt(4, quarto.getHotel().getId());

			int linhasAfetadas = ps.executeUpdate();
			
			ResultSet r = ps.getGeneratedKeys();
			
			if( r.next() ) {
				int numQuarto = r.getInt(1);	
				quarto.setNumQuarto(numQuarto);
			}
			
			return (linhasAfetadas == 1 ? true : false);
		}

		public boolean atualizar(Quarto quarto) throws SQLException {
			Connection con = ConexaoHotel.getConexao();
			
			String sql = "update quarto set andar = ?, tipo = ? where numQuarto = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, quarto.getAndar());
			ps.setString(2, quarto.getTipo());
			ps.setInt(3, quarto.getNumQuarto());
			
			if( ps.executeUpdate() == 1) {
				return true;
			}else {
				return false;
			}
		}

		public boolean excluir(int id) throws SQLException {
			Connection con = ConexaoHotel.getConexao();
			
			String sql = "delete from quarto where numQuarto = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			
			if( ps.executeUpdate() == 1) {
				return true;
			}else {
				return false;
			}
		}

		public Quarto buscar(int idBuscado) throws SQLException {
			
			Connection con = ConexaoHotel.getConexao();
			
			String sql = "select * from quarto where numQuarto = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, idBuscado);
			
			ResultSet result = ps.executeQuery();
			
			Quarto quarto = null;
			
			if( result.next() ) {
				int numQuarto = result.getInt("numQuarto");
				String andar = result.getString("andar");
				String tipo = result.getString("tipo");
				int idHotel = result.getInt("id_hotel");
				
				DaoHotel daoHot = new DaoHotel();
				Hotel h= daoHot.buscarPorId(idHotel);
				
				quarto = new Quarto(numQuarto, andar, tipo, h);
			}
			
			return quarto;
		}
		public List<Quarto> pesquisarQuarto(String texto) throws SQLException {
			Connection con = ConexaoHotel.getConexao();
			
			String sql = "select * from quarto where andar like ? ";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, "%"+texto+"%");
			
			ResultSet result = ps.executeQuery();
			
			List<Quarto> quartos = new ArrayList<Quarto>();
			
			DaoHotel daoHot = new DaoHotel();
			
			while( result.next() ) {
				int numQuarto = result.getInt("numQuarto");
				String andar = result.getString("andar");
				String tipo = result.getString("tipo");
				int idHotel = result.getInt("id_hotel");
				
				Hotel h = daoHot.buscarPorId(idHotel);
				Quarto t = new Quarto(numQuarto, andar, tipo, h);
		
				quartos.add(t);
			}
			
			return quartos;
		}

		public List<Quarto> buscarTodas() throws SQLException {
			Connection con = ConexaoHotel.getConexao();
			
			String sql = "select * from quarto";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet result = ps.executeQuery();
			
			List<Quarto> quartos = new ArrayList<Quarto>();
			
			DaoHotel daoHot = new DaoHotel();

			while( result.next() ) {
				int numQuarto = result.getInt("numQuarto");
				String andar = result.getString("andar");
				String tipo = result.getString("tipo");
				int idHotel = result.getInt("id_hotel");
				
				Hotel h = daoHot.buscarPorId(idHotel);
				
				Quarto t = new Quarto(numQuarto, andar, tipo, h);
		
				quartos.add(t);
			}
			
			return quartos;
		}
		
		
	}

