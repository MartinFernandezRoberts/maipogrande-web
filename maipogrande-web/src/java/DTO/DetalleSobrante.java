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
public class DetalleSobrante {
    private int idSobrante;
    private int idCabeceraSobrante;
    private int idProducto;
    private int cantidad;
    
    /**
     * @return the idSobrante
     */
    public int getIdSobrante() {
        return idSobrante;
    }

    /**
     * @param idSobrante the idSobrante to set
     */
    public void setIdSobrante(int idSobrante) {
        this.idSobrante = idSobrante;
    }

    /**
     * @return the idCabeceraSobrante
     */
    public int getIdCabeceraSobrante() {
        return idCabeceraSobrante;
    }

    /**
     * @param idCabeceraSobrante the idCabeceraSobrante to set
     */
    public void setIdCabeceraSobrante(int idCabeceraSobrante) {
        this.idCabeceraSobrante = idCabeceraSobrante;
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
    
}
