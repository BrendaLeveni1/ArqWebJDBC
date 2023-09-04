package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Modelo.Cliente;
import util.ConnectionFactory;

public class ClienteDAOimpDerby implements ClienteDao {
	private Connection connection;

	public ClienteDAOimpDerby(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void crear_tabla() {
		try {
			// this.connection.getInstance().
			Statement stmt = this.connection.createStatement();
			String sql = "CREATE TABLE Cliente ( idCliente INT,nombre VARCHAR(255), email VARCHAR(255))";
			stmt.executeUpdate(sql);
			ConnectionFactory.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertar(int idCliente, String nombre, String email) {
		try {
			// this.connection.getInstance().
			Statement stmt = this.connection.createStatement();
			String sql = "INSERT INTO Cliente (idCliente, nombre,email) VALUES (" + idCliente + "," + nombre + ","
					+ email + ");";
			stmt.executeUpdate(sql);
			ConnectionFactory.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//lista de clientes, ordenada por a cuál se le facturó más.
	@Override
	public void listar() {
		try {			
			String sql = "SELECT c.nombre AS nombre_cliente, SUM(f.total) AS total_facturado FROM clientes c LEFT JOIN facturas f ON c.id = f.cliente_id GROUP BY c.id, c.nombre ORDER BY total_facturado DESC";
			PreparedStatement stm = connection.prepareStatement(sql);
			ResultSet resultado = stm.executeQuery();

			while (resultado.next()) {
				int idCliente = resultado.getInt("idCliente");
				String nombreCliente = resultado.getString("nombre");
				float totalFacturado = resultado.getFloat("total_facturado");
				
				System.out.println("Cliente [idCliente=" + idCliente + ", nombre=" + nombreCliente + ", total facturado=$" + totalFacturado + "]");
			
			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
}
}