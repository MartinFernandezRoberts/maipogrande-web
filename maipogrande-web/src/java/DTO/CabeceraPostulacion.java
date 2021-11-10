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
public class CabeceraPostulacion {
    private int idCabeceraPostulacion;
    private int rutProductor;
    private Date fechaEmision;
    private int idCabeceraPV;

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
     * @return the rutProductor
     */
    public int getRutProductor() {
        return rutProductor;
    }

    /**
     * @param rutProductor the rutProductor to set
     */
    public void setRutProductor(int rutProductor) {
        this.rutProductor = rutProductor;
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
     * @return the idCabeceraPV
     */
    public int getIdCabeceraPV() {
        return idCabeceraPV;
    }

    /**
     * @param idCabeceraPV the idCabeceraPV to set
     */
    public void setIdCabeceraPV(int idCabeceraPV) {
        this.idCabeceraPV = idCabeceraPV;
    }
    
    
}
