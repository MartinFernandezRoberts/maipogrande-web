/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DTO.DetalleOrdenCompra;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class NegocioDetalleOrdenCompra {
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
        this.getCon().setNombreTabla("DETALLE_OC");
        this.getCon().setCadenaConexion("oracle.jdbc.driver.OracleDriver");
        this.getCon().setUsuario("maipogrande");
        this.getCon().setPass("123");
    } //Fin configurar
    
        public void insertarDetalleOrdenCompra(DetalleOrdenCompra detalleOrdenCompra)
    {
        this.configurarConexion();
        
        String[] parametros = {"CANTIDAD","PRECIO_UNITARIO", "ID_PRODUCTO", "ID_CABECERA_OC"};
        String[] tipos = {"int","int","int","int"};
        Object[] valores = {
            detalleOrdenCompra.getCantidad(),
            detalleOrdenCompra.getPrecioUnitario(),
            detalleOrdenCompra.getIdProducto(),
            detalleOrdenCompra.getIdCabeceraOrdenCompra()
        };

        this.getCon().ejecutarProcedimiento("SP_INGRESAR_DETALLE_OC", parametros, tipos, valores);
    }
        
    public ArrayList<DetalleOrdenCompra> listarDetallesOrdenCompra(int idCabeceraOrdenCompra) {
        ArrayList<DetalleOrdenCompra> listaDetallesOrdenCompra = new ArrayList<>();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " +
                                       this.getCon().getNombreTabla()+
                                     " WHERE ID_CABECERA_OC = " +idCabeceraOrdenCompra );
        this.getCon().setEsSelect(true);
        this.getCon().conectar();

        try
        {
           while(this.getCon().getDbResultSet().next())
           {
                DetalleOrdenCompra detalleOrdenCompra = new DetalleOrdenCompra();
                detalleOrdenCompra.setIdDetalleOrdenCompra(this.getCon().getDbResultSet().getInt("ID_DETALLE_OC"));
                detalleOrdenCompra.setCantidad(this.getCon().getDbResultSet().getInt("CANTIDAD"));
                detalleOrdenCompra.setPrecioUnitario(this.getCon().getDbResultSet().getInt("PRECIO_UNITARIO"));
                detalleOrdenCompra.setIdProducto(this.getCon().getDbResultSet().getInt("ID_PRODUCTO"));
                detalleOrdenCompra.setIdCabeceraOrdenCompra(this.getCon().getDbResultSet().getInt("ID_CABECERA_OC"));

                listaDetallesOrdenCompra.add(detalleOrdenCompra);
           } //Fin while
        }
        catch(Exception ex)
        {
           JOptionPane.showMessageDialog(null, "Error SQL " + ex.getMessage());
        } //Fin try cargar arraylist

        return listaDetallesOrdenCompra;
    }
}
