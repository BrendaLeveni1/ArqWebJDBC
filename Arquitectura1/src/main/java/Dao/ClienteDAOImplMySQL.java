package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import Modelo.Cliente;
import util.ConnectionFactory;

public class ClienteDAOImplMySQL implements ClienteDao {
	private Connection connection;

	public ClienteDAOImplMySQL(Connection connection) {
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
	public List<Cliente> listar() {
		try {
			// this.connection.getInstance().
			Statement stmt = this.connection.createStatement();
			String sql = "SELECT c.nombre AS nombre_cliente, SUM(f.total) AS total_facturado " +
	                "FROM clientes c " +
	                "LEFT JOIN facturas f ON c.id = f.cliente_id " +
	                "GROUP BY c.id, c.nombre " +
	                "ORDER BY total_facturado DESC"
			stmt.executeUpdate(sql);
			ConnectionFactory.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
