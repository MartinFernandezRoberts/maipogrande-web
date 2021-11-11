/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Conexion.Conexion;
import DTO.DetalleSubasta;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class NegocioDetalleSubasta {
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
        this.getCon().setNombreTabla("DETALLE_SUBASTA");
        this.getCon().setCadenaConexion("oracle.jdbc.driver.OracleDriver");
        this.getCon().setUsuario("maipogrande");
        this.getCon().setPass("123");
    } //Fin configurar
    
    public void insertarDetalleProcesoVenta(DetalleSubasta detalleSubasta)
    {
        this.configurarConexion();

        String[] parametros = {"FECHA_ESTIMADA_ENTREGA","CAPACIDAD_CARGA", "REFRIGERACION", "PESO_MAX", "EXTRAS", "PRECIO", "ID_CABECERA_SUBASTA", "ID_EMPRESA_TRANS"};
        String[] tipos = {"date","string","string","string","string","int","int","int"};
        Object[] valores = {
            detalleSubasta.getFechaEstimadaEntrega(),
            detalleSubasta.getCapacidadCarga(),
            detalleSubasta.getRefrigeracion(),
            detalleSubasta.getPesoMaximo(),
            detalleSubasta.getExtras(),
            detalleSubasta.getPrecio(),
            detalleSubasta.getIdCabeceraSubasta(),
            detalleSubasta.getIdEmpresaTransporte()
        };

        this.getCon().ejecutarProcedimiento("SP_INGRESAR_DETALLE_SUBASTA", parametros, tipos, valores);

    }
    
    public ArrayList<DetalleSubasta> listarDetallesSubasta(int idCabeceraSubasta) {
        ArrayList<DetalleSubasta> listaDetallesSubasta = new ArrayList<>();
        this.configurarConexion();
        this.getCon().setCadenaSQL("SELECT * FROM " +
                                       this.getCon().getNombreTabla()+
                                     " WHERE ID_CABECERA_SUBASTA = " +idCabeceraSubasta +";" );
        this.getCon().setEsSelect(true);
        this.getCon().conectar();

        try
        {
           while(this.getCon().getDbResultSet().next())
           {
                DetalleSubasta detalleSubasta = new DetalleSubasta();
                detalleSubasta.setIdDetalleSubasta(this.getCon().getDbResultSet().getInt("ID_DETALLE_SUBASTA"));
                detalleSubasta.setFechaEstimadaEntrega(this.getCon().getDbResultSet().getDate("FECHA_ESTIMADA_ENTREGA"));
                detalleSubasta.setCapacidadCarga(this.getCon().getDbResultSet().getString("CAPACIDAD_CARGA"));
                detalleSubasta.setRefrigeracion(this.getCon().getDbResultSet().getString("REFRIGERACION"));
                detalleSubasta.setPesoMaximo(this.getCon().getDbResultSet().getString("PESO_MAX"));
                detalleSubasta.setExtras(this.getCon().getDbResultSet().getString("EXTRAS"));
                detalleSubasta.setPrecio(this.getCon().getDbResultSet().getInt("PRECIO"));
                detalleSubasta.setIdCabeceraSubasta(this.getCon().getDbResultSet().getInt("ID_CABECERA_SUBASTA"));
                detalleSubasta.setIdEmpresaTransporte(this.getCon().getDbResultSet().getInt("ID_EMPRESA_TRANS"));

                listaDetallesSubasta.add(detalleSubasta);
           } //Fin while
        }
        catch(Exception ex)
        {
           JOptionPane.showMessageDialog(null, "Error SQL " + ex.getMessage());
        } //Fin try cargar arraylist

        return listaDetallesSubasta;
    }
}
