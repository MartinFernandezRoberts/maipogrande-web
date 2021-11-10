/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DTO.DetallePostulacion;

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
}
