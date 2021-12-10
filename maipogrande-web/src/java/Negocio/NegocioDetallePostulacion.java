/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DTO.DetallePostulacion;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class NegocioDetallePostulacion {
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
        this.getCon().setNombreTabla("DETALLE_POSTULACION");
        this.getCon().setCadenaConexion("oracle.jdbc.driver.OracleDriver");
        this.getCon().setUsuario("maipogrande");
        this.getCon().setPass("123");
    } //Fin configurar
    
    public void insertarDetallePostulacion(DetallePostulacion detallePostulacion)
    {
        this.configurarConexion();
        
        String[] parametros = {"ID_CABECERA_POSTULACION","ID_PRODUCTO", "CANTIDAD", "PRECIO_UNITARIO"};
        String[] tipos = {"int","int","int","int"};
        Object[] valores = {
            detallePostulacion.getIdCabeceraPostulacion(),
            detallePostulacion.getIdProducto(),
            detallePostulacion.getCantidad(),
            detallePostulacion.getPrecioUnitario()
        };

        this.getCon().ejecutarProcedimiento("SP_INGRESAR_DET_POSTULACION", parametros, tipos, valores);
    }
    
    public ArrayList<DetallePostulacion> listarDetallesPostulacion(int idCabeceraPostulacion) {
        ArrayList<DetallePostulacion> listaDetallesPostulacion = new ArrayList<>();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " +
                                       this.getCon().getNombreTabla()+
                                     " WHERE ID_CABECERA_POSTULACION = " +idCabeceraPostulacion );
        this.getCon().setEsSelect(true);
        this.getCon().conectar();

        try
        {
           while(this.getCon().getDbResultSet().next())
           {
                DetallePostulacion detallePostulacion = new DetallePostulacion();
                detallePostulacion.setIdDetallePostulacion(this.getCon().getDbResultSet().getInt("ID_DETALLE_POSTULACION"));
                detallePostulacion.setIdCabeceraPostulacion(this.getCon().getDbResultSet().getInt("ID_CABECERA_POSTULACION"));
                detallePostulacion.setIdProducto(this.getCon().getDbResultSet().getInt("ID_PRODUCTO"));
                detallePostulacion.setCantidad(this.getCon().getDbResultSet().getInt("CANTIDAD"));
                detallePostulacion.setPrecioUnitario(this.getCon().getDbResultSet().getInt("PRECIO_UNITARIO"));

                listaDetallesPostulacion.add(detallePostulacion);
           } //Fin while
        }
        catch(Exception ex)
        {
           JOptionPane.showMessageDialog(null, "Error SQL " + ex.getMessage());
        } //Fin try cargar arraylist

        return listaDetallesPostulacion;
    }
}
