/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DTO.CabeceraOrdenCompra;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class NegocioCabeceraOrdenCompra {
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
        this.getCon().setNombreTabla("CABECERA_OC");
        this.getCon().setCadenaConexion("oracle.jdbc.driver.OracleDriver");
        this.getCon().setUsuario("maipogrande");
        this.getCon().setPass("123");
    } //Fin configurar
    
    public void insertarCabeceraOrdenCompra(CabeceraOrdenCompra cabeceraOrdenCompra)
    {
        this.configurarConexion();
        
        String[] parametros = {"FECHA_EMISION", "RUT_CLIENTE"};
        String[] tipos = {"date","int"};
        Object[] valores = {
            cabeceraOrdenCompra.getFechaEmision(),
            cabeceraOrdenCompra.getRutCliente()
        };

        this.getCon().ejecutarProcedimiento("SP_INGRESAR_CAB_OC", parametros, tipos, valores);
    }
    
    public CabeceraOrdenCompra buscarCabeceraOrdenCompra(int idCabeceraOrdenCompra)
    {
        CabeceraOrdenCompra cabeceraOrdenCompra = new CabeceraOrdenCompra();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " + this.getCon().getNombreTabla()+
                                     " WHERE ID_CABECERA_OC = " +idCabeceraOrdenCompra);
        this.getCon().setEsSelect(true);
        this.getCon().conectar();
        
        try
        {
           if(this.getCon().getDbResultSet().next())
           {
                cabeceraOrdenCompra.setIdCabeceraOrdenCompra(this.getCon().getDbResultSet().getInt("ID_CABECERA_OC"));
                cabeceraOrdenCompra.setFechaEmision(this.getCon().getDbResultSet().getDate("FECHA_EMISION"));
                cabeceraOrdenCompra.setRutCliente(this.getCon().getDbResultSet().getInt("RUT_CLIENTE"));
           }
        }
        catch(Exception ex)
        {
            CabeceraOrdenCompra auxCabeceraProcesoVenta = new CabeceraOrdenCompra();
            return auxCabeceraProcesoVenta;
        }
        
        return cabeceraOrdenCompra;
    }
    
    public ArrayList<CabeceraOrdenCompra> listarOrdenesCompraCliente(int rutCliente) {
        ArrayList<CabeceraOrdenCompra> listaOrdenesCompra = new ArrayList<>();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " +
                                       this.getCon().getNombreTabla() + 
                                    " WHERE RUT_CLIENTE="+rutCliente);
        this.getCon().setEsSelect(true);
        this.getCon().conectar();

        try
        {
           while(this.getCon().getDbResultSet().next())
           {
                CabeceraOrdenCompra ordenCompra = new CabeceraOrdenCompra();
                ordenCompra.setIdCabeceraOrdenCompra(this.getCon().getDbResultSet().getInt("ID_CABECERA_OC"));
                ordenCompra.setFechaEmision(this.getCon().getDbResultSet().getDate("FECHA_EMISION"));
                ordenCompra.setRutCliente(this.getCon().getDbResultSet().getInt("RUT_CLIENTE"));

                listaOrdenesCompra.add(ordenCompra);
           } //Fin while
        }
        catch(Exception ex)
        {
           JOptionPane.showMessageDialog(null, "Error SQL " + ex.getMessage());
        } //Fin try cargar arraylist

        return listaOrdenesCompra;
    }
    
    public CabeceraOrdenCompra buscarUltimaInsercion()
    {
        CabeceraOrdenCompra cabeceraOrdenCompra = new CabeceraOrdenCompra();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " + this.getCon().getNombreTabla()+
                                     " WHERE ID_CABECERA_OC = (SELECT MAX(ID_CABECERA_OC) FROM "+this.getCon().getNombreTabla()+")");
        this.getCon().setEsSelect(true);
        this.getCon().conectar();
        
        try
        {
           if(this.getCon().getDbResultSet().next())
           {
                cabeceraOrdenCompra.setIdCabeceraOrdenCompra(this.getCon().getDbResultSet().getInt("ID_CABECERA_OC"));
                cabeceraOrdenCompra.setFechaEmision(this.getCon().getDbResultSet().getDate("FECHA_EMISION"));
                cabeceraOrdenCompra.setRutCliente(this.getCon().getDbResultSet().getInt("RUT_CLIENTE"));
           }
        }
        catch(Exception ex)
        {
            CabeceraOrdenCompra auxCabeceraOrdenCompra = new CabeceraOrdenCompra();
            return auxCabeceraOrdenCompra;
        }
        
        return cabeceraOrdenCompra;
    }
}
