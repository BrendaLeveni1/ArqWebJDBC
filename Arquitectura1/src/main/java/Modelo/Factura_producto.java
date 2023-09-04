package Modelo;

public class Factura_producto {

	private int idFactura;
	private int idProducto;
	private int cantidad;

	public Factura_producto(int idFactura, int idProducto, int cantidad) {
		this.idFactura = idFactura;
		this.idProducto = idProducto;
		this.cantidad = cantidad;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "Factura nro: "+ idFactura + " Producto id: " + idProducto + " cantidad: "+ cantidad + ";";
	}

}
