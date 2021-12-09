/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DTO.EmpresaTransporte;

/**
 *
 * @author Asus
 */
public class NegocioEmpresaTransporte {
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
        this.getCon().setNombreTabla("EMPRESA_TRANSPORTE");
        this.getCon().setCadenaConexion("oracle.jdbc.driver.OracleDriver");
        this.getCon().setUsuario("maipogrande");
        this.getCon().setPass("123");
    } //Fin configurar
    
    public EmpresaTransporte buscarEmpresaTransporteUsuario(int idUsuario){
        EmpresaTransporte empresaTransporte = new EmpresaTransporte();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " + this.getCon().getNombreTabla()+
                                    " WHERE ID_USUARIO = " +idUsuario);
        this.getCon().setEsSelect(true);
        this.getCon().conectar();
                try
        {
           if(this.getCon().getDbResultSet().next())
           {
                empresaTransporte.setIdEmpresaTransporte(this.getCon().getDbResultSet().getInt("ID_EMPRESA"));
                empresaTransporte.setNombreEmpresa(this.getCon().getDbResultSet().getString("NOMBRE_EMPRESA"));
                empresaTransporte.setIdUsuario(this.getCon().getDbResultSet().getInt("ID_USUARIO"));
           }
        }
        catch(Exception ex)
        {
            EmpresaTransporte auxEmpresaTransporte = new EmpresaTransporte();
            return auxEmpresaTransporte;
        }
        
        return empresaTransporte;
    }
}
