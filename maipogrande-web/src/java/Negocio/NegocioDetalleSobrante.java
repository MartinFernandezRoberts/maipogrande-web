/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DTO.DetalleProcesoVenta;
import DTO.DetalleSobrante;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class NegocioDetalleSobrante {
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
        this.getCon().setNombreTabla("DETALLE_SOBRANTE");
        this.getCon().setCadenaConexion("oracle.jdbc.driver.OracleDriver");
        this.getCon().setUsuario("maipogrande");
        this.getCon().setPass("123");
    } //Fin configurar
    
    
    public ArrayList<DetalleSobrante> listarDetallesSobrante() {
        ArrayList<DetalleSobrante> listaDetallesSobrante = new ArrayList<>();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " +
                                       this.getCon().getNombreTabla());
        this.getCon().setEsSelect(true);
        this.getCon().conectar();

        try
        {
           while(this.getCon().getDbResultSet().next())
           {
                DetalleSobrante detalleSobrante = new DetalleSobrante();
                detalleSobrante.setIdSobrante(this.getCon().getDbResultSet().getInt("ID_SOBRANTE"));
                detalleSobrante.setIdCabeceraSobrante(this.getCon().getDbResultSet().getInt("ID_CAB_SOBRANTE"));
                detalleSobrante.setIdProducto(this.getCon().getDbResultSet().getInt("ID_PRODUCTO"));
                detalleSobrante.setCantidad(this.getCon().getDbResultSet().getInt("CANTIDAD"));
                

                listaDetallesSobrante.add(detalleSobrante);
           } //Fin while
        }
        catch(Exception ex)
        {
           JOptionPane.showMessageDialog(null, "Error SQL " + ex.getMessage());
        } //Fin try cargar arraylist

        return listaDetallesSobrante;
    }
}
