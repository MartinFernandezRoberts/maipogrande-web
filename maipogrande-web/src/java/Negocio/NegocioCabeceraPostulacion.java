/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DTO.CabeceraPostulacion;

/**
 *
 * @author Asus
 */
public class NegocioCabeceraPostulacion {
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
        this.getCon().setNombreTabla("CABECERA_POSTULACION");
        this.getCon().setCadenaConexion("oracle.jdbc.driver.OracleDriver");
        this.getCon().setUsuario("maipogrande");
        this.getCon().setPass("123");
    } //Fin configurar
    
    public void insertarCabeceraPostulacion(CabeceraPostulacion cabeceraPostulacion)
    {
        this.configurarConexion();
        
        String[] parametros = {"FECHA_EMISION", "RUT_PRODUCTOR", "CABECERA_PV"};
        String[] tipos = {"date","int","int"};
        Object[] valores = {
            cabeceraPostulacion.getFechaEmision(),
            cabeceraPostulacion.getRutProductor(),
            cabeceraPostulacion.getIdCabeceraPV()
        };

        this.getCon().ejecutarProcedimiento("SP_INGRESAR_CAB_POSTULACION", parametros, tipos, valores);
    }
    
    public CabeceraPostulacion buscarUltimaInsercion()
    {
        CabeceraPostulacion cabeceraPostulacion = new CabeceraPostulacion();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " + this.getCon().getNombreTabla()+
                                     " WHERE ID_CABECERA_POSTULACION = (SELECT MAX(ID_CABECERA_POSTULACION) FROM "+this.getCon().getNombreTabla()+")");
        this.getCon().setEsSelect(true);
        this.getCon().conectar();
        
        try
        {
           if(this.getCon().getDbResultSet().next())
           {
                cabeceraPostulacion.setIdCabeceraPostulacion(this.getCon().getDbResultSet().getInt("ID_CABECERA_POSTULACION"));
                cabeceraPostulacion.setFechaEmision(this.getCon().getDbResultSet().getDate("FECHA_EMISION"));
                cabeceraPostulacion.setRutProductor(this.getCon().getDbResultSet().getInt("RUT_PRODUCTOR"));
                cabeceraPostulacion.setIdCabeceraPV(this.getCon().getDbResultSet().getInt("CABECERA_PV"));
           }
        }
        catch(Exception ex)
        {
            CabeceraPostulacion auxCabeceraPostulacion = new CabeceraPostulacion();
            return auxCabeceraPostulacion;
        }
        
        return cabeceraPostulacion;
    }
}
