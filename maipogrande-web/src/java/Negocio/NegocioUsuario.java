/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DTO.Usuario;
import java.util.ArrayList;
import javax.swing.JOptionPane;



/**
 *
 * @author Asus
 */
public class NegocioUsuario {
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
        this.getCon().setNombreTabla("USUARIO");
        this.getCon().setCadenaConexion("oracle.jdbc.driver.OracleDriver");
        this.getCon().setUsuario("maipogrande");
        this.getCon().setPass("123");
    } //Fin configurar
    
    public Usuario iniciarSesion(String nombreUsuario, String password){
        Usuario usuario = new Usuario();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " +
                                       this.getCon().getNombreTabla()
                                       + " WHERE NOMBRE_USUARIO = '" + nombreUsuario +"'"
                                               + " AND PASSWORD = '"+password +"'" );
        this.getCon().setEsSelect(true);
        this.getCon().conectar();

        try
        {
           if (this.getCon().getDbResultSet().next())
           {             
              usuario.setIdUsuario(Integer.parseInt(this.getCon().getDbResultSet().getString("ID_USUARIO")));
              usuario.setNombreUsuario(this.getCon().getDbResultSet().getString("NOMBRE_USUARIO"));
              usuario.setPassword(this.getCon().getDbResultSet().getString("PASSWORD"));
              // usuario.setFechaCreacion(this.getCon().getDbResultSet().getString("FECHA_CREACION"));
              usuario.setIdPerfil(Integer.parseInt(this.getCon().getDbResultSet().getString("ID_PERFIL")));
              usuario.setIdEstadoCuenta(Integer.parseInt(this.getCon().getDbResultSet().getString("ID_ESTD_CTA")));
           } //Fin if
           else
           {
               Usuario auxUsuario = new Usuario();
               auxUsuario.setIdUsuario(0);
               return auxUsuario;
           }
        }
        catch(Exception ex)
        {
           JOptionPane.showMessageDialog(null, "Error SQL " + ex.getMessage());
        } //Fin try cargar arraylist

        return usuario;
    }
    
}
