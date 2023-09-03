package Dao;

import java.util.List;
import Modelo.Cliente;

public interface ClienteDao {
	public void crear_tabla();

	public void insertar(int idCliente, String nombre, String email);

	public List<Cliente> listar();
}
