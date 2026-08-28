package main.java.com.kafusoft.abarroteria.kinal.service;

import javafx.collections.ObservableList;
import main.java.com.kafusoft.abarroteria.kinal.model.Producto;
import main.java.com.kafusoft.abarroteria.kinal.repository.ProductoRepository;

public class DashboardService {
    
    private final ProductoRepository productoRepository;

    public DashboardService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }
    
    public ObservableList<Producto> findProducto(){
        ObservableList<Producto> productos = productoRepository.findAll();
        
        if(productos == null){
            throw new RuntimeException("Sin productos.");
        }
        
        return productos;
    }
    
    public boolean eliminarProducto(String idProducto){
        if(idProducto == null || idProducto.isBlank()){
            throw new IllegalArgumentException("El ID del producto es obligatorio.");
        }
        
        return productoRepository.deleteById(idProducto);
    }
    
}