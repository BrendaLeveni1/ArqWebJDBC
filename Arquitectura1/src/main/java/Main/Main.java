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
		
        
	}   
     //cargaDatos(ConnectionFactory.MYSQL);
     //cargaDatos(ConnectionFactory.DERBY);
        ////////////////////////////////////////////////////////////////////////////////////////
        //CARGA DE DATOS
        
    
        //////////CSV Producto
        
        public static void cargarCSVProductos() {
        	CSVParser parser = null;
        	
        	try {
        		 parser = CSVFormat.DEFAULT.withHeader().parse(new
        				FileReader("src/datos/productos.csv"));}
        	
        		catch (IOException e){
        				e.printStackTrace();
        			}
 
        	for(CSVRecord row: parser) {
        		Integer id = Integer.parseInt(row.get("idProducto"));
        		Double valor = Double.parseDouble(row.get("valor"));
        		String nombre = (row.get("nombre")); 
        		Producto p= new Producto ( id,nombre,valor);
        		Dao.DAOFactory.getProductoDao(ConnectionFactory.MYSQL).insertar(p.getIdProducto(), p.getValor(), p.getNombre());
        		//?		Dao.DAOFactory.getProductoDao(ConnectionFactory.DERBY).insertar(p);///deberia poner esta linea para la coneccion con Derby?
        	}   
        }
        //////////CSV Cliente
        public static void cargarCSVClientes() {//ver
        	CSVParser parser = null;
        	
        	try {
        		 parser = CSVFormat.DEFAULT.withHeader().parse(new
        				FileReader("src/datos/clientes.csv"));}
        	
        		catch (IOException e){
        				e.printStackTrace();
        			}
 
        	for(CSVRecord row: parser) {
        		Integer id = Integer.parseInt(row.get("idCliente"));
        		String nombre = (row.get("nombre")); 
        		String email = (row.get("email")); 
        		Cliente c= new Cliente ( id,nombre,email);
        		Dao.DAOFactory.getClienteDao(ConnectionFactory.MYSQL).insertar(c.getIdCliente(), c.getNombre(), c.getEmail());
        		//?		Dao.DAOFactory.getClienteDao(ConnectionFactory.DERBY).insertar(c);//ver
        	}   
        }
        
        //////////CVS Factura_producto
        public static void cargarCSVFactura_producto() {//ver
        	CSVParser parser= null;
        	
        	try {
        		 parser = CSVFormat.DEFAULT.withHeader().parse(new
        				FileReader("src/datos/facturas-productos.csv"));}
        	
        		catch (IOException e){
        				e.printStackTrace();
        			}
 
        	for(CSVRecord row: parser) {
        		Integer idF = Integer.parseInt(row.get("idFactura"));
        		Integer idC = Integer.parseInt(row.get("idCliente"));
        		Integer cantidad = Integer.parseInt(row.get("cantidad"));
        		Factura_producto fp = new Factura_producto (idF, idC, cantidad);//// VER NO CREO QUE SEA ASI 
        		Dao.DAOFactory.getFactura_productoDao(ConnectionFactory.MYSQL).insertar(fp.getIdFactura(), fp.getIdProducto(), fp.getCantidad());
        		//?		Dao.DAOFactory.getFactura_productoDao(ConnectionFactory.DERBY).insertar(fp);//ver
        	}   
        }
        ///CVS Factura
        public static void cargarCSVFactura() {//ver
        	CSVParser parser= null;
        	
        	try {
        		 parser = CSVFormat.DEFAULT.withHeader().parse(new
        				FileReader("src/datos/facturas.csv"));}
        	
        		catch (IOException e){
        				e.printStackTrace();
        			}
 
        	for(CSVRecord row: parser) {
        		Integer idF = Integer.parseInt(row.get("idFactura"));
        		Integer idC = Integer.parseInt(row.get("idCliente"));
        		Factura f = new Factura (idF, idC);//// VER NO CREO QUE SEA ASI 
        		Dao.DAOFactory.getFacturaDao(ConnectionFactory.MYSQL).insertar(f.getIdFactura(), f.getIdCliente());
        		//?		Dao.DAOFactory.getFacturaDao(ConnectionFactory.DERBY).insertar(f);//ver
        	}   
        }
        
        }

	// aca irian las llamadas a las consultas que tenemos en el punto 3 y 4
	

