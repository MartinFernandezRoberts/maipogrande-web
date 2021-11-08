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
public class CabeceraProcesoVenta {
    private int idCabeceraVenta;
    private int idEmpresaTransporte;
    private Date fechaEmision;
    private String observaciones;
    private int idEstado;
    private int rutCliente;

    /**
     * @return the idCabeceraVenta
     */
    public int getIdCabeceraVenta() {
        return idCabeceraVenta;
    }

    /**
     * @param idCabeceraVenta the idCabeceraVenta to set
     */
    public void setIdCabeceraVenta(int idCabeceraVenta) {
        this.idCabeceraVenta = idCabeceraVenta;
    }

    /**
     * @return the idEmpresaTransporte
     */
    public int getIdEmpresaTransporte() {
        return idEmpresaTransporte;
    }

    /**
     * @param idEmpresaTransporte the idEmpresaTransporte to set
     */
    public void setIdEmpresaTransporte(int idEmpresaTransporte) {
        this.idEmpresaTransporte = idEmpresaTransporte;
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
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the idEstado
     */
    public int getIdEstado() {
        return idEstado;
    }

    /**
     * @param idEstado the idEstado to set
     */
    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
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
