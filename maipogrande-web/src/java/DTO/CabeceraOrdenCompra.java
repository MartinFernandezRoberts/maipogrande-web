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
public class CabeceraOrdenCompra {
    private int idCabeceraOrdenCompra;
    private Date fechaEmision;
    private int rutCliente;

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
     * @return the fechaEmision
     */
    public Date getFechaEmision() {
        return fechaEmision;
    }

    /**
     * @param fechaEmision the fechaEmision to set
     */
    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * @return the rutCliente
     */
    public int getRutCliente() {
        return rutCliente;
    }

    /**
     * @param rutCliente the rutCliente to set
     */
    public void setRutCliente(int rutCliente) {
        this.rutCliente = rutCliente;
    }
    
}
