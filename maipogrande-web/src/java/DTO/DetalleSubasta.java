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
public class DetalleSubasta {
    private int idDetalleSubasta;
    private int idEmpresaTransporte;
    private Date fechaEstimadaEntrega;
    private String capacidadCarga;
    private String refrigeracion;
    private String pesoMaximo;
    private String extras;
    private int precio;
    private int idCabeceraSubasta;

    /**
     * @return the idDetalleSubasta
     */
    public int getIdDetalleSubasta() {
        return idDetalleSubasta;
    }

    /**
     * @param idDetalleSubasta the idDetalleSubasta to set
     */
    public void setIdDetalleSubasta(int idDetalleSubasta) {
        this.idDetalleSubasta = idDetalleSubasta;
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
     * @return the fechaEstimadaEntrega
     */
    public Date getFechaEstimadaEntrega() {
        return fechaEstimadaEntrega;
    }

    /**
     * @param fechaEstimadaEntrega the fechaEstimadaEntrega to set
     */
    public void setFechaEstimadaEntrega(Date fechaEstimadaEntrega) {
        this.fechaEstimadaEntrega = fechaEstimadaEntrega;
    }

    /**
     * @return the capacidadCarga
     */
    public String getCapacidadCarga() {
        return capacidadCarga;
    }

    /**
     * @param capacidadCarga the capacidadCarga to set
     */
    public void setCapacidadCarga(String capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    /**
     * @return the refrigeracion
     */
    public String getRefrigeracion() {
        return refrigeracion;
    }

    /**
     * @param refrigeracion the refrigeracion to set
     */
    public void setRefrigeracion(String refrigeracion) {
        this.refrigeracion = refrigeracion;
    }

    /**
     * @return the pesoMaximo
     */
    public String getPesoMaximo() {
        return pesoMaximo;
    }

    /**
     * @param pesoMaximo the pesoMaximo to set
     */
    public void setPesoMaximo(String pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
    }

    /**
     * @return the extras
     */
    public String getExtras() {
        return extras;
    }

    /**
     * @param extras the extras to set
     */
    public void setExtras(String extras) {
        this.extras = extras;
    }

    /**
     * @return the precio
     */
    public int getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(int precio) {
        this.precio = precio;
    }

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
    
    
}
