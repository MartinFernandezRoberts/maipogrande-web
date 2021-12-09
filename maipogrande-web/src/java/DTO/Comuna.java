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
public class Comuna {
    private int idComuna;
    private String nombreComuna;
    private int idProvincia;

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
     * @return the nombreComuna
     */
    public String getNombreComuna() {
        return nombreComuna;
    }

    /**
     * @param nombreComuna the nombreComuna to set
     */
    public void setNombreComuna(String nombreComuna) {
        this.nombreComuna = nombreComuna;
    }

    /**
     * @return the idProvincia
     */
    public int getIdProvincia() {
        return idProvincia;
    }

    /**
     * @param idProvincia the idProvincia to set
     */
    public void setIdProvincia(int idProvincia) {
        this.idProvincia = idProvincia;
    }
    
}
