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
public class DaoHotel {
		
			
			public boolean inserir(Hotel hotel) throws SQLException {
				
				Connection conexao = ConexaoHotel.getConexao();
				
				String sql = "insert into hotelaria ( nome, endereco) values( ?, ? );";
				PreparedStatement ps = conexao.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
				
				ps.setString(1, hotel.getNome());
				ps.setString(2, hotel.getEndereco());

				int linhasAfetadas = ps.executeUpdate();
				
				ResultSet r = ps.getGeneratedKeys();
				
				if( r.next() ) {
					int id = r.getInt(1);	
					hotel.setId(id);
				}
				
				return (linhasAfetadas == 1 ? true : false);
			}
			
			public boolean atualizarDados(Hotel hotel) throws SQLException {
				return true;
			}
			
			public boolean atualizarSenha(Hotel hotel) throws SQLException {
				return true;
			}

			public boolean excluir(int id) throws SQLException {
				return true;
			}
			
			public Hotel buscarPorId(int idBuscado) throws SQLException {
				Connection con = ConexaoHotel.getConexao();
				
				String sql = "select * from hotelaria where id = ?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, idBuscado);
				
				ResultSet result = ps.executeQuery();
				
				Hotel hotel = null;
				
				if( result.next() ) {
					int id = result.getInt("id");
					String nome = result.getString("nome");
					String endereco = result.getString("endereco");
					
					
					hotel = new Hotel(id, nome, endereco);
				}
				
				return hotel;
			}
			
			public Hotel buscarPorEndereco(String endereco) throws SQLException {
				return null;
			}
			
			public List<Hotel> buscarTodos() throws SQLException {
				Connection con = ConexaoHotel.getConexao();
				
				String sql = "select * from hotelaria";
				
				PreparedStatement ps = con.prepareStatement(sql);
				
				ResultSet result = ps.executeQuery();
				
				List<Hotel> hoteis = new ArrayList<Hotel>();
				
				while( result.next() ) {
					int id = result.getInt("id");
					String nome = result.getString("nome");
					String endereco = result.getString("endereco");
					
					
					Hotel hotel = new Hotel(id,nome, endereco);
			
					hoteis.add(hotel);
				}
				
				return hoteis;
			}
		}


