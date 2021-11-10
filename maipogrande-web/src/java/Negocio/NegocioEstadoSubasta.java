/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DTO.EstadoSubasta;

/**
 *
 * @author Asus
 */
public class NegocioEstadoSubasta {
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
        this.getCon().setNombreTabla("ESTADO_SUBASTA");
        this.getCon().setCadenaConexion("oracle.jdbc.driver.OracleDriver");
        this.getCon().setUsuario("maipogrande");
        this.getCon().setPass("123");
    } //Fin configurar
    
    public EstadoSubasta buscarEstadoProcesoVenta(int idEstado)
    {
        EstadoSubasta estadoSubasta = new EstadoSubasta();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " + this.getCon().getNombreTabla()+
                                     " WHERE ID_ESTADO_SUBASTA = " +idEstado);
        this.getCon().setEsSelect(true);
        this.getCon().conectar();
        
        try
        {
           if(this.getCon().getDbResultSet().next())
           {
                estadoSubasta.setIdEstadoSubasta(this.getCon().getDbResultSet().getInt("ID_ESTADO_SUBASTA"));
                estadoSubasta.setDescripcion(this.getCon().getDbResultSet().getString("DESC_ESTADO"));
           }
        }
        catch(Exception ex)
        {
            EstadoSubasta auxEstadoSubasta = new EstadoSubasta();
            return auxEstadoSubasta;
        }
        
        return estadoSubasta;
    }
}
