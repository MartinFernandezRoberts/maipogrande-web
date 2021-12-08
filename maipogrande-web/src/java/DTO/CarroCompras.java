/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import DTO.Producto;
import DTO.ProductoCarro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Asus
 */
public class CarroCompras {
    
    private List<ProductoCarro> productos = new ArrayList<>();
    

    public void agregarAlCarro(Producto producto, int cantidad){
        ProductoCarro productoCarro = new ProductoCarro();
        productoCarro.setProducto(producto);
        productoCarro.setCantidad(cantidad);
        
        getProductos().add(productoCarro);
    }
    
    public void eliminarDelCarro(ProductoCarro productoCarro){     
        getProductos().remove(productoCarro);
    }

    /**
     * @return the productos
     */
    public List<ProductoCarro> getProductos() {
        return productos;
    }

    /**
     * @param productos the productos to set
     */
    public void setProductos(List<ProductoCarro> productos) {
        this.productos = productos;
    }
    
}
