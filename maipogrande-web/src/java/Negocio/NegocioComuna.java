/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DTO.Comuna;

/**
 *
 * @author Asus
 */
public class NegocioComuna {
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
        this.getCon().setNombreTabla("COMUNA");
        this.getCon().setCadenaConexion("oracle.jdbc.driver.OracleDriver");
        this.getCon().setUsuario("maipogrande");
        this.getCon().setPass("123");
    } //Fin configurar
    
    public Comuna buscarComuna(int idComuna){
        Comuna comuna = new Comuna();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " + this.getCon().getNombreTabla()+
                                    " WHERE ID = " +idComuna);
        this.getCon().setEsSelect(true);
        this.getCon().conectar();
                try
        {
           if(this.getCon().getDbResultSet().next())
           {
                comuna.setIdComuna(this.getCon().getDbResultSet().getInt("ID"));
                comuna.setNombreComuna(this.getCon().getDbResultSet().getString("NOMBRE_COMUNA"));
                comuna.setIdProvincia(this.getCon().getDbResultSet().getInt("ID_PROVINCIA"));
           }
        }
        catch(Exception ex)
        {
            Comuna auxComuna = new Comuna();
            return auxComuna;
        }
        
        return comuna;
    }
}
