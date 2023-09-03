package Dao;

import java.sql.Connection;

import util.ConnectionFactory;

public class DAOFactory {

	public DAOFactory() {
	}

	public static ClienteDao getClienteDao(String type) {
		if (type.equals("mysql")) {
			Connection connection = ConnectionFactory.getInstance().connect(ConnectionFactory.MYSQL);
			return new ClienteDAOImplMySQL(connection);
		} else if (type.equals("derby")) {
			Connection connection = ConnectionFactory.getInstance().connect(ConnectionFactory.DERBY);
			return new ClienteDAOImplMySQL(connection);
		} else {
			throw new IllegalArgumentException("Tipo de DAO no v�lido: " + type);
		}
	}

	// otros m�todos para obtener instancias de DAOs

	public static FacturaDao getFacturaDao(String type) {
		if (type.equals("mysql")) {
			Connection connection = ConnectionFactory.getInstance().connect(ConnectionFactory.MYSQL);
			return new FacturaDAOImplMySQL(connection);
		} else if (type.equals("derby")) {
			Connection connection = ConnectionFactory.getInstance().connect(ConnectionFactory.DERBY);
			return new FacturaDAOImplMySQL(connection);
		} else {
			throw new IllegalArgumentException("Tipo de DAO no v�lido: " + type);
		}
	}

	public static Factura_productoDao getFactura_productoDao(String type) {
		if (type.equals("mysql")) {
			Connection connection = ConnectionFactory.getInstance().connect(ConnectionFactory.MYSQL);
			return new Factura_productoDAOImplMySQL(connection);
		} else if (type.equals("derby")) {
			Connection connection = ConnectionFactory.getInstance().connect(ConnectionFactory.DERBY);
			return new Factura_productoDAOImplMySQL(connection);
		} else {
			throw new IllegalArgumentException("Tipo de DAO no v�lido: " + type);
		}
	}

	public static ProductoDao getProductoDao(String type) {
		if (type.equals("mysql")) {
			Connection connection = ConnectionFactory.getInstance().connect(ConnectionFactory.MYSQL);
			return new ProductoDAOImplMySQL(connection);
		} else if (type.equals("derby")) {
			Connection connection = ConnectionFactory.getInstance().connect(ConnectionFactory.DERBY);
			return new ProductoDAOImplMySQL(connection);
		} else {
			throw new IllegalArgumentException("Tipo de DAO no v�lido: " + type);
		}
	}

}
