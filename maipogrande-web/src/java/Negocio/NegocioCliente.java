/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DTO.Cliente;

/**
 *
 * @author Asus
 */
public class NegocioCliente {
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
        this.getCon().setNombreTabla("CLIENTE");
        this.getCon().setCadenaConexion("oracle.jdbc.driver.OracleDriver");
        this.getCon().setUsuario("maipogrande");
        this.getCon().setPass("123");
    } //Fin configurar
    
    public Cliente buscarClienteUsuario(int idUsuario){
        Cliente cliente = new Cliente();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " + this.getCon().getNombreTabla()+
                                    " WHERE ID_USUARIO = " +idUsuario);
        this.getCon().setEsSelect(true);
        this.getCon().conectar();
                try
        {
           if(this.getCon().getDbResultSet().next())
           {
                cliente.setRut(this.getCon().getDbResultSet().getInt("RUT"));
                cliente.setDvRut(this.getCon().getDbResultSet().getString("DV_RUT"));
                cliente.setRazonSocial(this.getCon().getDbResultSet().getString("RAZON_SOCIAL"));
                cliente.setDireccion(this.getCon().getDbResultSet().getString("DIRECCION"));
                cliente.setGiro(this.getCon().getDbResultSet().getString("GIRO"));
                cliente.setIdComuna(this.getCon().getDbResultSet().getInt("ID_COMUNA"));
                cliente.setIdUsuario(this.getCon().getDbResultSet().getInt("ID_USUARIO"));
                cliente.setIdTipo(this.getCon().getDbResultSet().getInt("ID_TIPO"));
           }
        }
        catch(Exception ex)
        {
            Cliente auxCliente = new Cliente();
            return auxCliente;
        }
        
        return cliente;
    }
    
    public Cliente buscarCliente(int rutCliente)
    {
        Cliente cliente = new Cliente();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " + this.getCon().getNombreTabla()+
                                     " WHERE RUT = " +rutCliente);
        this.getCon().setEsSelect(true);
        this.getCon().conectar();
        
        try
        {
           if(this.getCon().getDbResultSet().next())
           {
                cliente.setRut(this.getCon().getDbResultSet().getInt("RUT"));
                cliente.setDvRut(this.getCon().getDbResultSet().getString("DV_RUT"));
                cliente.setRazonSocial(this.getCon().getDbResultSet().getString("RAZON_SOCIAL"));
                cliente.setDireccion(this.getCon().getDbResultSet().getString("DIRECCION"));
                cliente.setGiro(this.getCon().getDbResultSet().getString("GIRO"));
                cliente.setIdComuna(this.getCon().getDbResultSet().getInt("ID_COMUNA"));
                cliente.setIdUsuario(this.getCon().getDbResultSet().getInt("ID_USUARIO"));
                cliente.setIdTipo(this.getCon().getDbResultSet().getInt("ID_TIPO"));
           }
        }
        catch(Exception ex)
        {
            Cliente auxCliente = new Cliente();
            return auxCliente;
        }
        
        return cliente;
    }
}
