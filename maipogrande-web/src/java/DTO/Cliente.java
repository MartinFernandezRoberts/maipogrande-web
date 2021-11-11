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
public class Cliente {
    private int rut;
    private String dvRut;
    private String razonSocial;
    private String direccion;
    private String giro;
    private int idComuna;
    private int idUsuario;
    private int idTipo;
    /**
     * @return the rut
     */
    public int getRut() {
        return rut;
    }

    /**
     * @param rut the rut to set
     */
    public void setRut(int rut) {
        this.rut = rut;
    }

    /**
     * @return the dvRut
     */
    public String getDvRut() {
        return dvRut;
    }

    /**
     * @param dvRut the dvRut to set
     */
    public void setDvRut(String dvRut) {
        this.dvRut = dvRut;
    }

    /**
     * @return the razonSocial
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @param razonSocial the razonSocial to set
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the giro
     */
    public String getGiro() {
        return giro;
    }

    /**
     * @param giro the giro to set
     */
    public void setGiro(String giro) {
        this.giro = giro;
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
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the idTipo
     */
    public int getIdTipo() {
        return idTipo;
    }

    /**
     * @param idTipo the idTipo to set
     */
    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }
    
}
