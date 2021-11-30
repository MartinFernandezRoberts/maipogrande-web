/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DTO.TipoCliente;

/**
 *
 * @author Ulfhednar
 */
public class NegocioTipoCliente {
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
        this.getCon().setNombreTabla("TIPO_CLIENTE");
        this.getCon().setCadenaConexion("oracle.jdbc.driver.OracleDriver");
        this.getCon().setUsuario("maipogrande");
        this.getCon().setPass("123");
    } //Fin configurar
    
    public TipoCliente buscarTipoCliente(int idTipo)
    {
        TipoCliente tipoCliente = new TipoCliente();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " + this.getCon().getNombreTabla()+
                                     " WHERE ID_TIPO = " +idTipo);
        this.getCon().setEsSelect(true);
        this.getCon().conectar();
        
        try
        {
           if(this.getCon().getDbResultSet().next())
           {
                tipoCliente.setIdTipo(this.getCon().getDbResultSet().getInt("ID_TIPO"));
                tipoCliente.setDescTipo(this.getCon().getDbResultSet().getString("DESC_TIPO"));
           }
        }
        catch(Exception ex)
        {
            TipoCliente auxTipoCliente = new TipoCliente();
            return auxTipoCliente;
        }
        
        return tipoCliente;
    }
}
