package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.sql.ResultSet;

import Modelo.Producto;
import util.ConnectionFactory;

public class ProductoDAOImplMySQL implements ProductoDao {
	private Connection connection;

	public ProductoDAOImplMySQL(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void crear_tabla() {
		try {
			// this.connection.getInstance().
			Statement stmt = this.connection.createStatement();
			String sql = "CREATE TABLE Producto ( idProducto INT, valor DOUBLE, nombre VARCHAR(255))";
			stmt.executeUpdate(sql);
			ConnectionFactory.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertar(int idProducto, double valor, String nombre) {
		try {
			// this.connection.getInstance().
			Statement stmt = this.connection.createStatement();
			String sql = "INSERT INTO producto (idProducto, valor, nombre) VALUES (" + idProducto + "," + valor + ","
					+ nombre + ");";
			stmt.executeUpdate(sql);
			ConnectionFactory.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
