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
public class CabeceraSubasta {
    private int idCabeceraSubasta;
    private Date fechaLimiteEntrega;
    private int idComuna;
    private int idCabeceraProcesoVenta;

    /**
     * @return the idCabeceraSubasta
     */
    public int getIdCabeceraSubasta() {
        return idCabeceraSubasta;
    }

    /**
     * @param idCabeceraSubasta the idCabeceraSubasta to set
     */
    public void setIdCabeceraSubasta(int idCabeceraSubasta) {
        this.idCabeceraSubasta = idCabeceraSubasta;
    }

    /**
     * @return the fechaLimiteEntrega
     */
    public Date getFechaLimiteEntrega() {
        return fechaLimiteEntrega;
    }

    /**
     * @param fechaLimiteEntrega the fechaLimiteEntrega to set
     */
    public void setFechaLimiteEntrega(Date fechaLimiteEntrega) {
        this.fechaLimiteEntrega = fechaLimiteEntrega;
    }

    /**
     * @return the idComuna
     */
    public int getIdComuna() {
        return idComuna;
    }

    /**
     * @param idComuna the idComuna to set
     */
    public void setIdComuna(int idComuna) {
        this.idComuna = idComuna;
    }

    /**
     * @return the idCabeceraProcesoVenta
     */
    public int getIdCabeceraProcesoVenta() {
        return idCabeceraProcesoVenta;
    }

    /**
     * @param idCabeceraProcesoVenta the idCabeceraProcesoVenta to set
     */
    public void setIdCabeceraProcesoVenta(int idCabeceraProcesoVenta) {
        this.idCabeceraProcesoVenta = idCabeceraProcesoVenta;
    }
    
    
}
