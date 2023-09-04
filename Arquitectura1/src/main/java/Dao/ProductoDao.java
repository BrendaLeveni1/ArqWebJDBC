package Dao;

import java.util.List;
import Modelo.Producto;

public interface ProductoDao {
	public void crear_tabla();

	public void insertar(int idProducto, double valor, String nombre);

}
