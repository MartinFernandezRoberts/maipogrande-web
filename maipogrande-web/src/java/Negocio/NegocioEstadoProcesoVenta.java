/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DTO.EstadoProcesoVenta;

/**
 *
 * @author Asus
 */
public class NegocioEstadoProcesoVenta {
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
        this.getCon().setNombreTabla("ESTADO_VENTA");
        this.getCon().setCadenaConexion("oracle.jdbc.driver.OracleDriver");
        this.getCon().setUsuario("maipogrande");
        this.getCon().setPass("123");
    } //Fin configurar
    
    public EstadoProcesoVenta buscarEstadoProcesoVenta(int idEstado)
    {
        EstadoProcesoVenta estadoProcesoVenta = new EstadoProcesoVenta();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " + this.getCon().getNombreTabla()+
                                     " WHERE ID_ESTADO_PV = " +idEstado);
        this.getCon().setEsSelect(true);
        this.getCon().conectar();
        
        try
        {
           if(this.getCon().getDbResultSet().next())
           {
                estadoProcesoVenta.setIdEstadoVenta(this.getCon().getDbResultSet().getInt("ID_ESTADO_PV"));
                estadoProcesoVenta.setDescripcion(this.getCon().getDbResultSet().getString("DESC_ESTADO"));
           }
        }
        catch(Exception ex)
        {
            EstadoProcesoVenta auxEstadoProcesoVenta = new EstadoProcesoVenta();
            return auxEstadoProcesoVenta;
        }
        
        return estadoProcesoVenta;
    }
}
