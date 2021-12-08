/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DTO.CabeceraProcesoVenta;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class NegocioCabeceraProcesoVenta {
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
        this.getCon().setNombreTabla("CABECERA_PV");
        this.getCon().setCadenaConexion("oracle.jdbc.driver.OracleDriver");
        this.getCon().setUsuario("maipogrande");
        this.getCon().setPass("123");
    } //Fin configurar
    
    public void insertarCabeceraProcesoVenta(CabeceraProcesoVenta cabeceraProcesoVenta)
    {
        this.configurarConexion();
        
        String[] parametros = {"FECHA_EMISION", "OBS_PV", "RUT_CLIENTE", "ESTADO_PV"};
        String[] tipos = {"date","string","int","int"};
        Object[] valores = {
            cabeceraProcesoVenta.getFechaEmision(),
            cabeceraProcesoVenta.getObservaciones(),
            cabeceraProcesoVenta.getRutCliente(),
            cabeceraProcesoVenta.getIdEstado(),
        };

        this.getCon().ejecutarProcedimiento("SP_INGRESAR_CAB_PV", parametros, tipos, valores);
    }
    
    public CabeceraProcesoVenta buscarCabeceraProcesoVenta(int idCabeceraProcesoVenta)
    {
        CabeceraProcesoVenta cabeceraProcesoVenta = new CabeceraProcesoVenta();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " + this.getCon().getNombreTabla()+
                                     " WHERE ID_CABECERA_PV = " +idCabeceraProcesoVenta);
        this.getCon().setEsSelect(true);
        this.getCon().conectar();
        
        try
        {
           if(this.getCon().getDbResultSet().next())
           {
                cabeceraProcesoVenta.setIdCabeceraVenta(this.getCon().getDbResultSet().getInt("ID_CABECERA_PV"));
                cabeceraProcesoVenta.setIdEmpresaTransporte(this.getCon().getDbResultSet().getInt("EMPRESA_TRANS"));
                cabeceraProcesoVenta.setFechaEmision(this.getCon().getDbResultSet().getDate("FECHA_EMISION"));
                cabeceraProcesoVenta.setObservaciones(this.getCon().getDbResultSet().getString("OBS_PV"));
                cabeceraProcesoVenta.setIdEstado(this.getCon().getDbResultSet().getInt("ESTADO_PV"));
                cabeceraProcesoVenta.setRutCliente(this.getCon().getDbResultSet().getInt("RUT_CLIENTE"));
           }
        }
        catch(Exception ex)
        {
            CabeceraProcesoVenta auxCabeceraProcesoVenta = new CabeceraProcesoVenta();
            return auxCabeceraProcesoVenta;
        }
        
        return cabeceraProcesoVenta;
    }
    
    public ArrayList<CabeceraProcesoVenta> listarProcesosVenta() {
        ArrayList<CabeceraProcesoVenta> listaProcesosVenta = new ArrayList<>();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " +
                                       this.getCon().getNombreTabla());
        this.getCon().setEsSelect(true);
        this.getCon().conectar();

        try
        {
           while(this.getCon().getDbResultSet().next())
           {
                CabeceraProcesoVenta procesoVenta = new CabeceraProcesoVenta();
                procesoVenta.setIdCabeceraVenta(this.getCon().getDbResultSet().getInt("ID_CABECERA_PV"));
                procesoVenta.setIdEmpresaTransporte(this.getCon().getDbResultSet().getInt("EMPRESA_TRANS"));
                procesoVenta.setFechaEmision(this.getCon().getDbResultSet().getDate("FECHA_EMISION"));
                procesoVenta.setObservaciones(this.getCon().getDbResultSet().getString("OBS_PV"));
                procesoVenta.setIdEstado(this.getCon().getDbResultSet().getInt("ESTADO_PV"));
                procesoVenta.setRutCliente(this.getCon().getDbResultSet().getInt("RUT_CLIENTE"));

                listaProcesosVenta.add(procesoVenta);
           } //Fin while
        }
        catch(Exception ex)
        {
           JOptionPane.showMessageDialog(null, "Error SQL " + ex.getMessage());
        } //Fin try cargar arraylist

        return listaProcesosVenta;
    }

    public ArrayList<CabeceraProcesoVenta> listarProcesosVentaCliente(int rutCliente) {
        ArrayList<CabeceraProcesoVenta> listaProcesosVenta = new ArrayList<>();
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
                CabeceraProcesoVenta procesoVenta = new CabeceraProcesoVenta();
                procesoVenta.setIdCabeceraVenta(this.getCon().getDbResultSet().getInt("ID_CABECERA_PV"));
                procesoVenta.setIdEmpresaTransporte(this.getCon().getDbResultSet().getInt("EMPRESA_TRANS"));
                procesoVenta.setFechaEmision(this.getCon().getDbResultSet().getDate("FECHA_EMISION"));
                procesoVenta.setObservaciones(this.getCon().getDbResultSet().getString("OBS_PV"));
                procesoVenta.setIdEstado(this.getCon().getDbResultSet().getInt("ESTADO_PV"));
                procesoVenta.setRutCliente(this.getCon().getDbResultSet().getInt("RUT_CLIENTE"));

                listaProcesosVenta.add(procesoVenta);
           } //Fin while
        }
        catch(Exception ex)
        {
           JOptionPane.showMessageDialog(null, "Error SQL " + ex.getMessage());
        } //Fin try cargar arraylist

        return listaProcesosVenta;
    }
    
    public CabeceraProcesoVenta buscarUltimaInsercion()
    {
        CabeceraProcesoVenta cabeceraProcesoVenta = new CabeceraProcesoVenta();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " + this.getCon().getNombreTabla()+
                                     " WHERE ID_CABECERA_PV = (SELECT MAX(ID_CABECERA_PV) FROM "+this.getCon().getNombreTabla()+")");
        this.getCon().setEsSelect(true);
        this.getCon().conectar();
        
        try
        {
           if(this.getCon().getDbResultSet().next())
           {
                cabeceraProcesoVenta.setIdCabeceraVenta(this.getCon().getDbResultSet().getInt("ID_CABECERA_PV"));
                cabeceraProcesoVenta.setFechaEmision(this.getCon().getDbResultSet().getDate("FECHA_EMISION"));
                cabeceraProcesoVenta.setObservaciones(this.getCon().getDbResultSet().getString("OBS_PV"));
                cabeceraProcesoVenta.setRutCliente(this.getCon().getDbResultSet().getInt("RUT_CLIENTE"));
                cabeceraProcesoVenta.setIdEstado(this.getCon().getDbResultSet().getInt("ESTADO_PV"));
           }
        }
        catch(Exception ex)
        {
            CabeceraProcesoVenta auxCabeceraProcesoVenta = new CabeceraProcesoVenta();
            return auxCabeceraProcesoVenta;
        }
        
        return cabeceraProcesoVenta;
    }
}
