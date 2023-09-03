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

	//Retorne el producto que más recaudó
	//cantidad de productos vendidos multiplicado por su valor
	@Override
	public List<Producto> listar() {
		try {
			// this.connection.getInstance().
			Statement stmt = this.connection.createStatement();
			String sql = "SELECT nombre, MAX(valor * cantidad) AS recaudacion_maxima FROM producto GROUP BY nombre recaudacion_maxima DESC LIMIT 1";
			ResultSet resultado = stmt.executeUpdate(sql);
			
			//no se si esto va acá o en el main
			//si no va acá debería retornar algo?
            if (resultado.next()) {
                String nombreProducto = resultado.getString("nombre_producto");
                double recaudacionTotal = resultado.getDouble("recaudacion_total");
                
                System.out.println("El producto que más recaudó es: " + nombreProducto);
                System.out.println("Recaudación total: $" + recaudacionTotal);
            } else {
                System.out.println("No se encontraron productos vendidos.");
            }
            
			ConnectionFactory.getInstance().disconnect();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
