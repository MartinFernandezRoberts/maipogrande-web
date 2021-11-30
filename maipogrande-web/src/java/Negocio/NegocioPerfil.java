/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DTO.Perfil;

/**
 *
 * @author Ulfhednar
 */
public class NegocioPerfil {
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
        this.getCon().setNombreTabla("PERFIL");
        this.getCon().setCadenaConexion("oracle.jdbc.driver.OracleDriver");
        this.getCon().setUsuario("maipogrande");
        this.getCon().setPass("123");
    } //Fin configurar
    
    public Perfil buscarPerfil(int idPerfil)
    {
        Perfil perfil = new Perfil();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " + this.getCon().getNombreTabla()+
                                     " WHERE ID_PERFIL = " +idPerfil);
        this.getCon().setEsSelect(true);
        this.getCon().conectar();
        
        try
        {
           if(this.getCon().getDbResultSet().next())
           {
                perfil.setIdPerfil(this.getCon().getDbResultSet().getInt("ID_PERFIL"));
                perfil.setDescPerfil(this.getCon().getDbResultSet().getString("DESC_PERFIL"));
           }
        }
        catch(Exception ex)
        {
            Perfil auxPerfil = new Perfil();
            return auxPerfil;
        }
        
        return perfil;
    }
}
