/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DTO.CabeceraSubasta;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class NegocioCabeceraSubasta {
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
        this.getCon().setNombreTabla("CABECERA_SUBASTA");
        this.getCon().setCadenaConexion("oracle.jdbc.driver.OracleDriver");
        this.getCon().setUsuario("maipogrande");
        this.getCon().setPass("123");
    } //Fin configurar
    
    public void insertarCabeceraSubasta(CabeceraSubasta cabeceraSubasta)
    {
        this.configurarConexion();
        this.getCon().setCadenaSQL("INSERT INTO " + this.getCon().getNombreTabla()+
                                     " (ID_CABECERA_SUBASTA,FECHA_LIMITE_ENTREGA,ID_COMUNA,ID_CABECERA_PV,ID_ESTADO_SUBASTA) "
                                             + "VALUES ("+
                                    cabeceraSubasta.getIdCabeceraSubasta()+ ",'"+
                                    cabeceraSubasta.getFechaLimiteEntrega()+ "',"+ 
                                    cabeceraSubasta.getIdComuna()+","+
                                    cabeceraSubasta.getIdCabeceraProcesoVenta()+","+
                                    cabeceraSubasta.getIdEstadoSubasta()+");");
        this.getCon().setEsSelect(false);
        this.getCon().conectar();
    }
    
    public CabeceraSubasta buscarCabeceraSubasta(int idCabeceraSubasta)
    {
        CabeceraSubasta cabeceraSubasta = new CabeceraSubasta();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " + this.getCon().getNombreTabla()+
                                     " WHERE ID_CABECERA_SUBASTA = " +idCabeceraSubasta );
        this.getCon().setEsSelect(true);
        this.getCon().conectar();
        
        try
        {
           if(this.getCon().getDbResultSet().next())
           {
                cabeceraSubasta.setIdCabeceraSubasta(this.getCon().getDbResultSet().getInt("ID_CABECERA_SUBASTA"));
                cabeceraSubasta.setFechaLimiteEntrega(this.getCon().getDbResultSet().getDate("FECHA_LIMITE_ENTREGA"));
                cabeceraSubasta.setIdComuna(this.getCon().getDbResultSet().getInt("ID_COMUNA"));
                cabeceraSubasta.setIdCabeceraProcesoVenta(this.getCon().getDbResultSet().getInt("ID_CABECERA_PV"));
                cabeceraSubasta.setIdEstadoSubasta(this.getCon().getDbResultSet().getInt("ID_ESTADO_SUBASTA"));
           }
        }
        catch(Exception ex)
        {
            CabeceraSubasta auxCabeceraSubasta = new CabeceraSubasta();
            return auxCabeceraSubasta;
        }
        
        return cabeceraSubasta;
    }
    
    
    public CabeceraSubasta buscarCabeceraSubastaDeProcesoVenta(int idProcesoVenta)
    {
        CabeceraSubasta cabeceraSubasta = new CabeceraSubasta();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " + this.getCon().getNombreTabla()+
                                     " WHERE ID_CABECERA_PV = " +idProcesoVenta );
        this.getCon().setEsSelect(true);
        this.getCon().conectar();
        
        try
        {
           if(this.getCon().getDbResultSet().next())
           {
                cabeceraSubasta.setIdCabeceraSubasta(this.getCon().getDbResultSet().getInt("ID_CABECERA_SUBASTA"));
                cabeceraSubasta.setFechaLimiteEntrega(this.getCon().getDbResultSet().getDate("FECHA_LIMITE_ENTREGA"));
                cabeceraSubasta.setIdComuna(this.getCon().getDbResultSet().getInt("ID_COMUNA"));
                cabeceraSubasta.setIdCabeceraProcesoVenta(this.getCon().getDbResultSet().getInt("ID_CABECERA_PV"));
                cabeceraSubasta.setIdEstadoSubasta(this.getCon().getDbResultSet().getInt("ID_ESTADO_SUBASTA"));
           }
        }
        catch(Exception ex)
        {
            CabeceraSubasta auxCabeceraSubasta = new CabeceraSubasta();
            return auxCabeceraSubasta;
        }
        
        return cabeceraSubasta;
    }
    
    public ArrayList<CabeceraSubasta> listarSubastasTransporte() {
        ArrayList<CabeceraSubasta> listaSubastasTransporte = new ArrayList<>();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " +
                                       this.getCon().getNombreTabla());
        this.getCon().setEsSelect(true);
        this.getCon().conectar();

        try
        {
           while(this.getCon().getDbResultSet().next())
           {
                CabeceraSubasta cabeceraSubasta = new CabeceraSubasta();
                cabeceraSubasta.setIdCabeceraSubasta(this.getCon().getDbResultSet().getInt("ID_CABECERA_SUBASTA"));
                cabeceraSubasta.setFechaLimiteEntrega(this.getCon().getDbResultSet().getDate("FECHA_LIMITE_ENTREGA"));
                cabeceraSubasta.setIdComuna(this.getCon().getDbResultSet().getInt("ID_COMUNA"));
                cabeceraSubasta.setIdCabeceraProcesoVenta(this.getCon().getDbResultSet().getInt("ID_CABECERA_PV"));
                cabeceraSubasta.setIdEstadoSubasta(this.getCon().getDbResultSet().getInt("ID_ESTADO_SUBASTA"));

                listaSubastasTransporte.add(cabeceraSubasta);
           } //Fin while
        }
        catch(Exception ex)
        {
           JOptionPane.showMessageDialog(null, "Error SQL " + ex.getMessage());
        } //Fin try cargar arraylist

        return listaSubastasTransporte;
    }
}
