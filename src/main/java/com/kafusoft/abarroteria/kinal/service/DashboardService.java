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
        
        if(productoRepository.findAll() == null){
            
            throw new RuntimeException("Sin productos.");
        }else{
        
        return productoRepository.findAll();
        }
        
    }
    
}
