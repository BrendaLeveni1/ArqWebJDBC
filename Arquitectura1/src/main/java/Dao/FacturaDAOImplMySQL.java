package Dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import Modelo.Cliente;
import Modelo.Factura;
import util.ConnectionFactory;

public class FacturaDAOImplMySQL implements FacturaDao {
	private Connection connection;

	public FacturaDAOImplMySQL(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void crear_tabla() {
		try {
			// this.connection.getInstance().
			Statement stmt = this.connection.createStatement();
			String sql = "CREATE TABLE Factura ( idFactura INT, idCliente INT)";
			stmt.executeUpdate(sql);
			ConnectionFactory.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insertar(Factura idFactura, Cliente idCliente) {
		try {
			// this.connection.getInstance().
			Statement stmt = this.connection.createStatement();
			String sql = "INSERT INTO Factura (idFactura,idCliente) VALUES (" + idFactura + "," + idCliente + ");";
			stmt.executeUpdate(sql);
			ConnectionFactory.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Factura> listar() {
		// TODO Auto-generated method stub
		return null;
	}
}
