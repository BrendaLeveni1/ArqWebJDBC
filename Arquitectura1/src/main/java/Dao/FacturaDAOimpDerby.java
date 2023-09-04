package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Factura;
import utils.ConnectionFactory;

public class FacturaDAOimpDerby implements FacturaDAO {

	private Connection connection;

	public FacturaDAOimpDerby(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void crear_tabla() {
		try {
			Statement stmt = this.connection.createStatement();
			String createFactura = "CREATE TABLE Factura (idFactura INT, idCliente INT, PRIMARY KEY (idFactura), FOREIGN KEY (idCliente) REFERENCES Cliente (idCliente))";
			stmt.executeUpdate(createFactura);
			ConnectionFactory.getInstance().disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void agregar(Factura f) {
		try {
			String createFactura = "INSERT INTO factura(idFactura,idCliente) VALUES(?,?)";
			PreparedStatement ps = connection.prepareStatement(createFactura);

			ps.setInt(1, f.getIdFactura());
			ps.setInt(2, f.getIdCliente());
			
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
