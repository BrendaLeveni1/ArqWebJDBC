package Modelo;

public class Factura {

	private Factura idFactura;
	private Cliente idCliente;

	public Factura(Factura idFactura, Cliente idCliente) {
		this.idFactura = idFactura;
		this.idCliente = idCliente;
	}

	public Factura getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Factura idFactura) {
		this.idFactura = idFactura;
	}

	public Cliente getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Cliente idCliente) {
		this.idCliente = idCliente;
	}

	public String toString() {
		return null;// hacer

	}

}
