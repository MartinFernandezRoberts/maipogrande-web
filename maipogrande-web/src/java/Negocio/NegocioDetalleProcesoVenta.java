/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DTO.DetalleProcesoVenta;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class NegocioDetalleProcesoVenta {
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
        this.getCon().setNombreTabla("DETALLE_PV");
        this.getCon().setCadenaConexion("oracle.jdbc.driver.OracleDriver");
        this.getCon().setUsuario("maipogrande");
        this.getCon().setPass("123");
    } //Fin configurar
    
    public void insertarDetalleProcesoVenta(DetalleProcesoVenta detalleProcesoVenta)
    {
        this.configurarConexion();
        
        String[] parametros = {"ID_PRODUCTO","CANTIDAD", "PRECIO_UNITARIO", "ID_CABECERA_PV"};
        String[] tipos = {"int","int","int","int"};
        Object[] valores = {
            detalleProcesoVenta.getIdProducto(),
            detalleProcesoVenta.getCantidad(),
            detalleProcesoVenta.getPrecioUnitario(),
            detalleProcesoVenta.getIdCabeceraVenta()
        };

        this.getCon().ejecutarProcedimiento("SP_INGRESAR_DETALLE_PV", parametros, tipos, valores);
    }
    
    public ArrayList<DetalleProcesoVenta> listarDetallesProcesoVenta(int idCabeceraProcesoVenta) {
        ArrayList<DetalleProcesoVenta> listaDetallesProcesoVenta = new ArrayList<>();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " +
                                       this.getCon().getNombreTabla()+
                                     " WHERE ID_CABECERA_PV = " +idCabeceraProcesoVenta );
        this.getCon().setEsSelect(true);
        this.getCon().conectar();

        try
        {
           while(this.getCon().getDbResultSet().next())
           {
                DetalleProcesoVenta producto = new DetalleProcesoVenta();
                producto.setIdDetalleVenta(this.getCon().getDbResultSet().getInt("ID_DETALLE_PV"));
                producto.setIdProducto(this.getCon().getDbResultSet().getInt("ID_PRODUCTO"));
                producto.setCantidad(this.getCon().getDbResultSet().getInt("CANTIDAD"));
                producto.setPrecioUnitario(this.getCon().getDbResultSet().getInt("PRECIO_UNITARIO"));
                producto.setIdCabeceraVenta(this.getCon().getDbResultSet().getInt("ID_CABECERA_PV"));

                listaDetallesProcesoVenta.add(producto);
           } //Fin while
        }
        catch(Exception ex)
        {
           JOptionPane.showMessageDialog(null, "Error SQL " + ex.getMessage());
        } //Fin try cargar arraylist

        return listaDetallesProcesoVenta;
    }
}
