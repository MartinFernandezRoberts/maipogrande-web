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
public class EstadoProcesoVenta {
    private int idEstadoVenta;
    private String descripcion;
    /**
     * @return the idEstadoVenta
     */
    public int getIdEstadoVenta() {
        return idEstadoVenta;
    }

    /**
     * @param idEstadoVenta the idEstadoVenta to set
     */
    public void setIdEstadoVenta(int idEstadoVenta) {
        this.idEstadoVenta = idEstadoVenta;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
