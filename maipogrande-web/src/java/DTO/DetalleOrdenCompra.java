/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author Asus
 */
public class DetalleOrdenCompra {
    private int idCabeceraOrdenCompra;
    private int idDetalleOrdenCompra;
    private int idProducto;
    private int cantidad;
    private int precioUnitario;

    /**
     * @return the idCabeceraOrdenCompra
     */
    public int getIdCabeceraOrdenCompra() {
        return idCabeceraOrdenCompra;
    }

    /**
     * @param idCabeceraOrdenCompra the idCabeceraOrdenCompra to set
     */
    public void setIdCabeceraOrdenCompra(int idCabeceraOrdenCompra) {
        this.idCabeceraOrdenCompra = idCabeceraOrdenCompra;
    }

    /**
     * @return the idDetalleOrdenCompra
     */
    public int getIdDetalleOrdenCompra() {
        return idDetalleOrdenCompra;
    }

    /**
     * @param idDetalleOrdenCompra the idDetalleOrdenCompra to set
     */
    public void setIdDetalleOrdenCompra(int idDetalleOrdenCompra) {
        this.idDetalleOrdenCompra = idDetalleOrdenCompra;
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
