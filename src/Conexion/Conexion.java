/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;
import java.sql.*;
import javax.swing.JOptionPane;


/**
 *
 * @author Grupo2
 */
public class Conexion 
{
    //Variables de instancia
    private String     nombreBaseDeDatos;
    private String     nombreTabla;
    private String     cadenaConexion;
    private String     cadenaSQL;
    private boolean    esSelect;
    private ResultSet  dbResultSet;
    private Connection dbConnection;
    private String     usuario;
    private String     pass;

    /**
     * @return the nombreBaseDeDatos
     */
    public String getNombreBaseDeDatos() {
        return nombreBaseDeDatos;
    }

    /**
     * @param nombreBaseDeDatos the nombreBaseDeDatos to set
     */
    public void setNombreBaseDeDatos(String nombreBaseDeDatos) {
        this.nombreBaseDeDatos = nombreBaseDeDatos;
    }

    /**
     * @return the nombreTabla
     */
    public String getNombreTabla() {
        return nombreTabla;
    }

    /**
     * @param nombreTabla the nombreTabla to set
     */
    public void setNombreTabla(String nombreTabla) {
        this.nombreTabla = nombreTabla;
    }

    /**
     * @return the cadenaConexion
     */
    public String getCadenaConexion() {
        return cadenaConexion;
    }

    /**
     * @param cadenaConexion the cadenaConexion to set
     */
    public void setCadenaConexion(String cadenaConexion) {
        this.cadenaConexion = cadenaConexion;
    }

    /**
     * @return the cadenaSQL
     */
    public String getCadenaSQL() {
        return cadenaSQL;
    }

    /**
     * @param cadenaSQL the cadenaSQL to set
     */
    public void setCadenaSQL(String cadenaSQL) {
        this.cadenaSQL = cadenaSQL;
    }

    /**
     * @return the esSelect
     */
    public boolean isEsSelect() {
        return esSelect;
    }

    /**
     * @param esSelect the esSelect to set
     */
    public void setEsSelect(boolean esSelect) {
        this.esSelect = esSelect;
    }

    /**
     * @return the dbResultSet
     */
    public ResultSet getDbResultSet() {
        return dbResultSet;
    }

    /**
     * @param dbResultSet the dbResultSet to set
     */
    public void setDbResultSet(ResultSet dbResultSet) {
        this.dbResultSet = dbResultSet;
    }

    /**
     * @return the dbConnection
     */
    public Connection getDbConnection() {
        return dbConnection;
    }

    /**
     * @param dbConnection the dbConnection to set
     */
    public void setDbConnection(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the pass
     */
    public String getPass() {
        return pass;
    }

    /**
     * @param pass the pass to set
     */
    public void setPass(String pass) {
        this.pass = pass;
    }
    
    //Metodos Personales o customer
    
    /**
     * Metodo que se encarga de cerrar la conexion
     */
    
    public void cerrar()
    {
       try
       {
           this.getDbConnection().close();
       }
       catch(Exception ex)
       {
          JOptionPane.showMessageDialog(null, "Error al cerrar la conexion " + ex.getMessage());
       }
    } //Fin cerrar

    /**
     * Metodo que realiza la conexion y ejecuta la instrucciòn
     * SQL; además de realizar las validaciones correspondientes
     */
    
    public void conectar()
    {
        //Se validan las variables de instancia
        
        if (this.getNombreBaseDeDatos().length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Falta nombre base de datos ");
        }
        
        if (this.getNombreTabla().length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Falta nombre de la tabla ");
        }
        
        if (this.getCadenaConexion().length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Falta cadena conexion ");
        }
  
        if (this.getCadenaSQL().length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Falta cadena SQL ");
        }
        
        if (this.getUsuario().length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Falta datos usuario ");
        }

       /* if (this.getPass().length() == 0)
        {
            JOptionPane.showMessageDialog(null, "Falta Pass ");
        }
*/
        //SE instancia la conexion
        
        Statement st = null;
        try
        {
            //Se carga el Driver
            Class.forName(this.getCadenaConexion());
            //Se carga conexion
            this.setDbConnection(DriverManager.getConnection(this.getNombreBaseDeDatos(), this.getUsuario(), this.getPass()));
            st = this.getDbConnection().createStatement();
        }
        catch(Exception ex)
        {
           JOptionPane.showMessageDialog(null, "Error en la conexion " + ex.getMessage());       
        } //Fin try conexion
        
        //Se programa si es Select o no
        
        if (this.isEsSelect())
        {
            //Se carga resultSet
            
            try
            {
                this.setDbResultSet(st.executeQuery(this.getCadenaSQL()));
            }
            catch(Exception ex)
            {
                 JOptionPane.showMessageDialog(null, "Error al cargar el ResultSet " + ex.getMessage());
            } //Fin try carga de resultSet
        }
        else
        {
            // se ejecuta Insert - Update o Delete
            try
            {
               int insertFila = st.executeUpdate(this.getCadenaSQL());
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Error de SQL " + ex.getMessage());
            }
            
            
        }// fin if es select
        
        
    } //Fin conectar
    
  /*  public static void main(String[] args)
    {
        Conexion conec1 = new Conexion();
        conec1.setNombreBaseDeDatos("jdbc:mysql://localhost/prueba");
        conec1.setNombreTabla("cliente");
        conec1.setCadenaConexion("com.mysql.jdbc.Driver");
        conec1.setUsuario("root");
        conec1.setPass("");
        conec1.setCadenaSQL("INSERT INTO " + conec1.getNombreTabla() +
                             " (rut,nombre) VALUES ('1111-2','El super Guru');");  
        conec1.setEsSelect(false);
        conec1.conectar();
        conec1.cerrar();
    }*/  
    
    
    
    
} //Fin Conexion
