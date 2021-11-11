/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DTO.Producto;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class NegocioProducto {
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
        this.getCon().setNombreTabla("PRODUCTO");
        this.getCon().setCadenaConexion("oracle.jdbc.driver.OracleDriver");
        this.getCon().setUsuario("maipogrande");
        this.getCon().setPass("123");
    } //Fin configurar
    
    public Producto buscarProducto(int idProducto)
    {
        Producto producto = new Producto();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " + this.getCon().getNombreTabla()+
                                     " WHERE ID_PRODUCTO = " +idProducto );
        this.getCon().setEsSelect(true);
        this.getCon().conectar();
        
        try
        {
           if(this.getCon().getDbResultSet().next())
           {
                producto.setIdProducto(this.getCon().getDbResultSet().getInt("ID_PRODUCTO"));
                producto.setIdCategoria(this.getCon().getDbResultSet().getInt("ID_CATEGORIA"));
                producto.setNombreProducto(this.getCon().getDbResultSet().getString("NOMBRE_PRODUCTO"));
                producto.setPrecio(this.getCon().getDbResultSet().getInt("PRECIO"));
                producto.setIdCalidad(this.getCon().getDbResultSet().getInt("ID_CALIDAD"));
                producto.setPorcentajeMerma(this.getCon().getDbResultSet().getDouble("PORCENTAJE_MERMA"));
           }
        }
        catch(Exception ex)
        {
            Producto auxProducto = new Producto();
            return auxProducto;
        }
        
        return producto;
    }
    
    public ArrayList<Producto> listarProductos() {
        ArrayList<Producto> listaProductos = new ArrayList<>();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " +
                                       this.getCon().getNombreTabla());
        this.getCon().setEsSelect(true);
        this.getCon().conectar();

        try
        {
           while(this.getCon().getDbResultSet().next())
           {
                Producto producto = new Producto();
                producto.setIdProducto(this.getCon().getDbResultSet().getInt("ID_PRODUCTO"));
                producto.setIdCategoria(this.getCon().getDbResultSet().getInt("ID_CATEGORIA"));
                producto.setNombreProducto(this.getCon().getDbResultSet().getString("NOMBRE_PRODUCTO"));
                producto.setPrecio(this.getCon().getDbResultSet().getInt("PRECIO"));
                producto.setIdCalidad(this.getCon().getDbResultSet().getInt("ID_CALIDAD"));
                producto.setPorcentajeMerma(this.getCon().getDbResultSet().getDouble("PORCENTAJE_MERMA"));

                listaProductos.add(producto);
           } //Fin while
        }
        catch(Exception ex)
        {
           JOptionPane.showMessageDialog(null, "Error SQL " + ex.getMessage());
        } //Fin try cargar arraylist

        return listaProductos;
    }
    
    
}
