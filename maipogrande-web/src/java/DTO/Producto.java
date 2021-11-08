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
public class Producto {
    private int idProducto;
    private int idCategoria;
    private String nombreProducto;
    private int precio;
    private int idCalidad;
    private double porcentajeMerma;

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
     * @return the idCategoria
     */
    public int getIdCategoria() {
        return idCategoria;
    }

    /**
     * @param idCategoria the idCategoria to set
     */
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * @return the nombreProducto
     */
    public String getNombreProducto() {
        return nombreProducto;
    }

    /**
     * @param nombreProducto the nombreProducto to set
     */
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
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
     * @return the idCalidad
     */
    public int getIdCalidad() {
        return idCalidad;
    }

    /**
     * @param idCalidad the idCalidad to set
     */
    public void setIdCalidad(int idCalidad) {
        this.idCalidad = idCalidad;
    }

    /**
     * @return the porcentajeMerma
     */
    public double getPorcentajeMerma() {
        return porcentajeMerma;
    }

    /**
     * @param porcentajeMerma the porcentajeMerma to set
     */
    public void setPorcentajeMerma(double porcentajeMerma) {
        this.porcentajeMerma = porcentajeMerma;
    }

}
