package Dao;

import java.util.List;

import Modelo.Factura;
import Modelo.Factura_producto;
import Modelo.Producto;

public interface Factura_productoDao {
	public void crear_tabla();

//preguntar
	public void insertar(Factura idFactura, Producto idProducto, int cantidad);

	public List<Factura_producto> listar();
}
