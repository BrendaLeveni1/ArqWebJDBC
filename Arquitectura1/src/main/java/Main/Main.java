package Main;

import Dao.DAOFactory;
import Modelo.Cliente;
import Modelo.Factura;
import Modelo.Factura_producto;
import Modelo.Producto;
import util.ConnectionFactory;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Main {

	public static void main(String[] args) {

		// TIPO DE BASE DE DATOS
		String mysqlDb = ConnectionFactory.MYSQL;
		String derbyDb = ConnectionFactory.DERBY;

		/////// CREACION DE TABLAS ///////////

		DAOFactory.getClienteDao(ConnectionFactory.DERBY).crear_tabla();
		DAOFactory.getFacturaDao(ConnectionFactory.DERBY).crear_tabla();
		DAOFactory.getFactura_productoDao(ConnectionFactory.DERBY).crear_tabla();
		DAOFactory.getProductoDao(ConnectionFactory.DERBY).crear_tabla();

		DAOFactory.getClienteDao(ConnectionFactory.MYSQL).crear_tabla();
		DAOFactory.getFacturaDao(ConnectionFactory.MYSQL).crear_tabla();
		DAOFactory.getFactura_productoDao(ConnectionFactory.MYSQL).crear_tabla();
		DAOFactory.getProductoDao(ConnectionFactory.MYSQL).crear_tabla();

		// Elijo tipo de base de datos
		String dbType = mysqlDb;
		/* String dbType= derbyDb; */

		// Cargar los datos
		cargarCSVs(dbType);

		// Producto que mas recaudó
		DAOFactory.getFactura_productoDao(dbType).mayorRecaudacion();

		// Clientes ordenados por facturación
		System.out.println("Clientes con mayor factuacion: implementación MySQL");
		DAOFactory.getClienteDao(dbType).listar();

		System.out.println("Clientes con mayor factuacion: implementación Derby");
		DAOFactory.getClienteDao(derbyDb).listar();

	}

///////////////// //CARGA DE DATOS///////////////////////
	
	//llamada a los metodos de cargar
	private static void cargarCSVs(String dbType) {
		cargarCSVProductos(dbType);
		cargarCSVClientes(dbType);
		cargarCSVFacturas(dbType);
		cargarCSVFactura_producto(dbType);
	}
	

	/////////////////// CSV Producto ////////////////////

	public static void cargarCSVProductos(String dbType) {
		CSVParser parser = null;

		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/datos/productos.csv"));
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		for (CSVRecord row : parser) {
			Integer id = Integer.parseInt(row.get("idProducto"));
			Double valor = Double.parseDouble(row.get("valor"));
			String nombre = (row.get("nombre"));
			Producto p = new Producto(id, nombre, valor);
			Dao.DAOFactory.getProductoDao(dbType).insertar(p.getIdProducto(), p.getValor(), p.getNombre());
		}
	}

	///////////////////////// CSV Cliente /////////////////////
	public static void cargarCSVClientes(String dbType) {// ver
		CSVParser parser = null;

		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/datos/clientes.csv"));
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		for (CSVRecord row : parser) {
			Integer id = Integer.parseInt(row.get("idCliente"));
			String nombre = (row.get("nombre"));
			String email = (row.get("email"));
			Cliente c = new Cliente(id, nombre, email);
			Dao.DAOFactory.getClienteDao(dbType).insertar(c.getIdCliente(), c.getNombre(), c.getEmail());
		}
	}

	////////////////// CSV Factura_producto ///////////////
	public static void cargarCSVFactura_producto(String dbType) {// ver
		CSVParser parser = null;

		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/datos/facturas-productos.csv"));
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		for (CSVRecord row : parser) {
			Integer idF = Integer.parseInt(row.get("idFactura"));
			Integer idC = Integer.parseInt(row.get("idCliente"));
			Integer cantidad = Integer.parseInt(row.get("cantidad"));
			Factura_producto fp = new Factura_producto(idF, idC, cantidad);
			Dao.DAOFactory.getFactura_productoDao(dbType).insertar(fp.getIdFactura(), fp.getIdProducto(),
					fp.getCantidad());
		}

	}

	/////////////////// CSV Factura /////////////////
	@SuppressWarnings("deprecation")
	public static void cargarCSVFacturas(String dbType) {
		CSVParser parser = null;

		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/datos/facturas.csv"));
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		for (CSVRecord row : parser) {
			Integer idF = Integer.parseInt(row.get("idFactura"));
			Integer idC = Integer.parseInt(row.get("idCliente"));
			Factura f = new Factura(idF, idC);
			Dao.DAOFactory.getFacturaDao(dbType).insertar(f.getIdFactura(), f.getIdCliente());

		}

	}

}
