package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Cliente;
import utils.ConnectionFactory;

public class ClienteDAOimpDerby implements ClienteDAO {
	private Connection connection;

	public ClienteDAOimpDerby(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void crear_tabla() {
		try {
			String sql = "CREATE TABLE Cliente (idCliente INT, nombre VARCHAR(500), email VARCHAR(150), PRIMARY KEY(idCliente))";
			connection.prepareStatement(sql).execute();
			connection.commit();
			ConnectionFactory.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void agregar(Cliente c) {
		try {
			String sql = "INSERT INTO Cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			
			ps.setInt(1, c.getIdCliente());
			ps.setString(2, c.getNombre());
			ps.setString(3, c.getEmail());
			
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void listarClientes() {
		try {
			String sql = "SELECT c.idCliente, c.nombre, SUM(fp.cantidad * p.valor) AS total_facturado FROM cliente c JOIN factura f ON c.idCliente = f.idCliente JOIN factura_producto fp ON f.idFactura = fp.idFactura JOIN producto p ON fp.idProducto = p.idProducto GROUP BY c.idCliente, c.nombre ORDER BY total_facturado DESC";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				int idCliente = resultSet.getInt("idCliente");
				String nombreCliente = resultSet.getString("nombre");
				float totalFacturado = resultSet.getFloat("total_facturado");
				
				System.out.println("Cliente [idCliente=" + idCliente + ", nombre=" + nombreCliente + ", total facturado=$" + totalFacturado + "]");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
