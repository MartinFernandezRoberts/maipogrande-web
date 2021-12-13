/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DTO.Productor;

/**
 *
 * @author Asus
 */
public class NegocioProductor {
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
        this.getCon().setNombreTabla("PRODUCTOR");
        this.getCon().setCadenaConexion("oracle.jdbc.driver.OracleDriver");
        this.getCon().setUsuario("maipogrande");
        this.getCon().setPass("123");
    } //Fin configurar
    
    public Productor buscarProductorUsuario(int idUsuario){
        Productor productor = new Productor();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " + this.getCon().getNombreTabla()+
                                    " WHERE ID_USUARIO = " +idUsuario);
        this.getCon().setEsSelect(true);
        this.getCon().conectar();
        try
        {
           if(this.getCon().getDbResultSet().next())
           {
                productor.setRutProductor(this.getCon().getDbResultSet().getInt("RUT"));
                productor.setDvRut(this.getCon().getDbResultSet().getString("DV_RUT"));
                productor.setRazonSocial(this.getCon().getDbResultSet().getString("RAZON_SOCIAL"));
                productor.setDireccion(this.getCon().getDbResultSet().getString("DIRECCION"));
                productor.setGiro(this.getCon().getDbResultSet().getString("GIRO"));
                productor.setIdComuna(this.getCon().getDbResultSet().getInt("ID_COMUNA"));
                productor.setIdUsuario(this.getCon().getDbResultSet().getInt("ID_USUARIO"));
           }
        }
        catch(Exception ex)
        {
            Productor auxProductor = new Productor();
            return auxProductor;
        }
        
        return productor;
    }
}
