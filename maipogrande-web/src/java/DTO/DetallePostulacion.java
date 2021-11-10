/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Asus
 */
public class DetallePostulacion {
    private int idDetallePostulacion;
    private int idCabeceraPostulacion;
    private int idProducto;
    private int cantidad;
    private int precioUnitario;

    /**
     * @return the idDetallePostulacion
     */
    public int getIdDetallePostulacion() {
        return idDetallePostulacion;
    }

    /**
     * @param idDetallePostulacion the idDetallePostulacion to set
     */
    public void setIdDetallePostulacion(int idDetallePostulacion) {
        this.idDetallePostulacion = idDetallePostulacion;
    }

    /**
     * @return the idCabeceraPostulacion
     */
    public int getIdCabeceraPostulacion() {
        return idCabeceraPostulacion;
    }

    /**
     * @param idCabeceraPostulacion the idCabeceraPostulacion to set
     */
    public void setIdCabeceraPostulacion(int idCabeceraPostulacion) {
        this.idCabeceraPostulacion = idCabeceraPostulacion;
    }

    /**
     * @return the idProducto
     */
    public int getIdProducto() {
        return idProducto;
    }

    /**
     * @param idProducto the idProducto to set
     */
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the precioUnitario
     */
    public int getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * @param precioUnitario the precioUnitario to set
     */
    public void setPrecioUnitario(int precioUnitario) {
        this.precioUnitario = precioUnitario;
    }


}
