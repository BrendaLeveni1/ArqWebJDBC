package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import Modelo.Factura;
import Modelo.Factura_producto;
import Modelo.Producto;
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
	public void insertar(Factura idFactura, Producto idProducto, int cantidad) {
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

	@Override
	public List<Factura_producto> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
