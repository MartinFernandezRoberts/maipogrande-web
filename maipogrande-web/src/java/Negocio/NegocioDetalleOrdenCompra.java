/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DTO.DetalleOrdenCompra;

/**
 *
 * @author Asus
 */
public class NegocioDetalleOrdenCompra {
    private Conexion con;

    /**
     * @return the con
     */
    public Conexion getCon() {
        return con;
    }

    /**
     * @param con the con to set
     */
    public void setCon(Conexion con) {
        this.con = con;
    }
    
    public void configurarConexion()
    {
        this.setCon(new Conexion());
        this.getCon().setNombreBaseDeDatos("jdbc:oracle:thin:@localhost:1521:XE");
        this.getCon().setNombreTabla("DETALLE_OC");
        this.getCon().setCadenaConexion("oracle.jdbc.driver.OracleDriver");
        this.getCon().setUsuario("maipogrande");
        this.getCon().setPass("123");
    } //Fin configurar
    
        public void insertarDetalleOrdenCompra(DetalleOrdenCompra detalleOrdenCompra)
    {
        this.configurarConexion();
        this.getCon().setCadenaSQL("INSERT INTO " + this.getCon().getNombreTabla()+
                                     " (ID_DETALLE_OC,CANTIDAD,PRECIO_UNITARIO,ID_PRODUCTO,ID_CABECERA_OC) "
                                             + "VALUES ("+
                                    detalleOrdenCompra.getIdDetalleOrdenCompra()+ ","+
                                    detalleOrdenCompra.getCantidad()+","+                    
                                    detalleOrdenCompra.getPrecioUnitario()+","+
                                    detalleOrdenCompra.getIdProducto()+ ","+ 
                                    detalleOrdenCompra.getIdCabeceraOrdenCompra()+");");
        this.getCon().setEsSelect(false);
        this.getCon().conectar();
    }
}
