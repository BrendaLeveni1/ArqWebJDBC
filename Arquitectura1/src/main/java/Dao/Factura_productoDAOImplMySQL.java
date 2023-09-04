package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import util.ConnectionFactory;

public class Factura_productoDAOImplMySQL implements Factura_productoDao {
	private Connection connection;

	public Factura_productoDAOImplMySQL(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void crear_tabla() {
		try {
			// this.connection.getInstance().
			Statement stmt = this.connection.createStatement();
			String sql = "CREATE TABLE Factura_producto ( idFactura INT, idProducto INT,  cantidad INT)";
			stmt.executeUpdate(sql);
			ConnectionFactory.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertar(int idFactura, int idProducto, int cantidad) {
		try {
			// this.connection.getInstance().
			Statement stmt = this.connection.createStatement();
			String sql = "INSERT INTO Factura_producto (idFactura, idProducto, cantidad) VALUES (" + idFactura + ","
					+ idProducto + "," + cantidad + ");";
			stmt.executeUpdate(sql);
			ConnectionFactory.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Retorne el producto que más recaudó
	// cantidad de productos vendidos multiplicado por su valor
	@Override
	public void mayorRecaudacion() {
		try {
			// this.connection.getInstance().

			String sql = "SELECT nombre, MAX(valor * cantidad) AS recaudacion_maxima FROM producto GROUP BY nombre recaudacion_maxima DESC LIMIT 1";
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet resultado = ps.executeQuery();

			while (resultado.next()) {
				String nombreProducto = resultado.getString("nombre");
				float recaudacionMaxima = resultado.getFloat("recaudacion_maxima");

				System.out.println("El producto que más recaudó es: " + nombreProducto);
				System.out.println("Recaudación máxima: $" + recaudacionMaxima);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
