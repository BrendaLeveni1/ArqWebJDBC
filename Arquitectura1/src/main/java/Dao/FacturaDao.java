package Dao;

import java.util.List;

import Modelo.Cliente;
import Modelo.Factura;

public interface FacturaDao {
	public void crear_tabla();

	public void insertar(Factura idFactura, Cliente idCliente);

	public List<Factura> listar();
}
