package Dao;


public interface ClienteDao {
	public void crear_tabla();

	public void insertar(int idCliente, String nombre, String email);

	public void listar();
}
