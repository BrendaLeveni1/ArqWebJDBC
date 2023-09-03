package Main;

import Dao.DAOFactory;
import util.ConnectionFactory;
import java.io.FileReader;
import java.io.IOException;

import org.apache.commons.csv.CSVRecord;

public class Main {

	public static void main(String[] args) {
		
		/////////////////////////////////////////////////////////////////////////////////////////
		//CREACION DE TABLAS 
		
		 
		DAOFactory.getClienteDao(ConnectionFactory.DERBY).crear_tabla();
        DAOFactory.getFacturaDao(ConnectionFactory.DERBY).crear_tabla();	
        DAOFactory.getFactura_productoDao(ConnectionFactory.DERBY).crear_tabla();	
        DAOFactory.getProductoDao(ConnectionFactory.DERBY).crear_tabla();		

        DAOFactory.getClienteDao(ConnectionFactory.MYSQL).crear_tabla();
        DAOFactory.getFacturaDao(ConnectionFactory.MYSQL).crear_tabla();	
        DAOFactory.getFactura_productoDao(ConnectionFactory.MYSQL).crear_tabla();	
        DAOFactory.getProductoDao(ConnectionFactory.MYSQL).crear_tabla();		
		
        
        
     //cargaDatos(ConnectionFactory.MYSQL);
     //cargaDatos(ConnectionFactory.DERBY);
        ////////////////////////////////////////////////////////////////////////////////////////
        //CARGA DE DATOS
        
    
        //////////CVS Producto
        
        public static void cargarCVSProductos() {
        	CVSParser = null;
        	
        	try {
        		 parser = CSVFormat.DEFAULT.withHeader().parse(new
        				FileReader("src/datos/productos.csv"));}
        	
        		catch (IOException e){
        				e.printStackTrace();
        			}
 
        	for(CSVRecord row: parser) {
        		Integer id = Integer.parseInt(row.get("idProducto"));
        		Double valor = Double.parseDouble(row.get("valor"));
        		Producto p= new Producto ( id,row.get("nombre"),valor);
        		Dao.DAOFactory.getProductoDao(ConnectionFactory.MYSQL).insertar(p);
        		//?		Dao.DAOFactory.getProductoDao(ConnectionFactory.DERBY).insertar(p);///deberia poner esta linea para la coneccion con Derby?
        	}   
        }
        //////////CVS Cliente
        public static void cargarCVSClientes() {//ver
        	CVSParser = null;
        	
        	try {
        		 parser = CSVFormat.DEFAULT.withHeader().parse(new
        				FileReader("src/datos/clientes.csv"));}
        	
        		catch (IOException e){
        				e.printStackTrace();
        			}
 
        	for(CSVRecord row: parser) {
        		Integer id = Integer.parseInt(row.get("idCliente"));
        		String nombre = (row.get("nombre"));//ver no se si deberia ser como los otros 
        		Cliente c= new Cliente ( id,row.get("nombre","email"));
        		Dao.DAOFactory.getClienteDao(ConnectionFactory.MYSQL).insertar(c);
        		//?		Dao.DAOFactory.getClienteDao(ConnectionFactory.DERBY).insertar(c);//ver
        	}   
        }
        
        //////////CVS Factura_producto
        public static void cargarCVSFactura_producto() {//ver
        	CVSParser = null;
        	
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
        		Factura_producto fp = new Factura_producto (row.get(idF,idC,cantidad));//// VER NO CREO QUE SEA ASI 
        		Dao.DAOFactory.getFactura_productoDao(ConnectionFactory.MYSQL).insertar(fp);
        		//?		Dao.DAOFactory.getFactura_productoDao(ConnectionFactory.DERBY).insertar(fp);//ver
        	}   
        }
        ///CVS Factura
        public static void cargarCVSFactura() {//ver
        	CVSParser = null;
        	
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
        		Dao.DAOFactory.getFacturaDao(ConnectionFactory.MYSQL).insertar(f);
        		//?		Dao.DAOFactory.getFacturaDao(ConnectionFactory.DERBY).insertar(f);//ver
        	}   
        }
        
        }

	// aca irian las llamadas a las consultas que tenemos en el punto 3 y 4
	
}
