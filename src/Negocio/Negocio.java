/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.*;
import DTO.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Grupo2
 */
public class Negocio 
{
    private Conexion conec1;

    /**
     * @return the conec1
     */
    public Conexion getConec1() {
        return conec1;
    }

    /**
     * @param conec1 the conec1 to set
     */
    public void setConec1(Conexion conec1) {
        this.conec1 = conec1;
    }
    
    /**
     * metodo para configurar la conexion
     */
    
    
    public void configurarConexion()
    {
        this.setConec1(new Conexion());
        this.getConec1().setNombreBaseDeDatos("jdbc:oracle:thin:@localhost:1521:XE");
        this.getConec1().setNombreTabla("cliente");
        this.getConec1().setCadenaConexion("oracle.jdbc.driver.OracleDriver");
        this.getConec1().setUsuario("maipogrande");
        this.getConec1().setPass("123");
    } //Fin configurar
    
    public ArrayList retornaCliente()
    {
       ArrayList auxListaCliente = new ArrayList();
       this.configurarConexion();
       this.getConec1().setCadenaSQL("SELECT * FROM " +
                                      this.getConec1().getNombreTabla());
       this.getConec1().setEsSelect(true);
       this.getConec1().conectar();
       
       try
       {
          while(this.getConec1().getDbResultSet().next())
          {
             Cliente auxCliente = new Cliente();
             auxCliente.setRut(this.getConec1().getDbResultSet().getString("rut"));
             auxCliente.setNombre(this.getConec1().getDbResultSet().getString("nombre"));
             auxListaCliente.add(auxCliente);
          } //Fin while
       }
       catch(Exception ex)
       {
          JOptionPane.showMessageDialog(null, "Error SQL " + ex.getMessage());
       } //Fin try cargar arraylist
       
       return auxListaCliente;
    } //Fin retorna cliente
    
    public void ingresaCliente(Cliente cliente)
    {
       this.configurarConexion();
       this.getConec1().setCadenaSQL("INSERT INTO " + this.getConec1().getNombreTabla()
                                     + " (rut,nombre) VALUES ('" + cliente.getRut() + "','"
                                     + cliente.getNombre() +"');");
       this.getConec1().setEsSelect(false);
       this.getConec1().conectar();
        
    } //Fin ingresa Cliente
    
    public Cliente buscaCliente(String rut)
    {
       Cliente auxCliente = new Cliente();
       this.configurarConexion();
       this.getConec1().setCadenaSQL("SELECT * FROM " +
                                      this.getConec1().getNombreTabla()
                                      + " WHERE rut = '" + rut+"';" );
       this.getConec1().setEsSelect(true);
       this.getConec1().conectar();
       
       try
       {
          if (this.getConec1().getDbResultSet().next())
          {             
             auxCliente.setRut(this.getConec1().getDbResultSet().getString("rut"));
             auxCliente.setNombre(this.getConec1().getDbResultSet().getString("nombre"));  
          } //Fin if
          else
          {
              auxCliente.setRut("");
              auxCliente.setNombre("");
          }
       }
       catch(Exception ex)
       {
          JOptionPane.showMessageDialog(null, "Error SQL " + ex.getMessage());
       } //Fin try cargar arraylist
       
       return auxCliente;
    } //Fin busca cliente

    public void eliminaCliente(String rut)
    {
       this.configurarConexion();
       this.getConec1().setCadenaSQL("DELETE FROM " + this.getConec1().getNombreTabla()
                                     + " WHERE rut = '" + rut + "';");
       this.getConec1().setEsSelect(false);
       this.getConec1().conectar();
        
    } //Fin ELIMINA Cliente
    
    public Cliente posicionaCliente(int fila)
    {
        Cliente auxCliente = new Cliente();
        this.configurarConexion();
        this.getConec1().setCadenaSQL("SELECT * FROM " + this.getConec1().getNombreTabla());
        this.getConec1().setEsSelect(true);
        this.getConec1().conectar();
        
        try
        {
           if (this.getConec1().getDbResultSet().relative(fila))
           {
               auxCliente.setRut(this.getConec1().getDbResultSet().getString("rut"));
               auxCliente.setNombre(this.getConec1().getDbResultSet().getString("nombre"));
           }
           else
           {
              auxCliente.setRut("");
              auxCliente.setNombre("");
           }
        }
        catch(Exception ex)
        {
           auxCliente.setRut("");
           auxCliente.setNombre("");
        } //Fin catch
        return auxCliente;
    
    
    }
    
    
    
    
}
